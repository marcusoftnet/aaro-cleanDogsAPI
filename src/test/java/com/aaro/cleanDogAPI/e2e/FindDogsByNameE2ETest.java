package com.aaro.cleanDogAPI.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindDogsByNameE2ETest {

  static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
    "postgres:16-alpine"
  ).withDatabaseName("testdb")
    .withUsername("testuser")
    .withPassword("testpassword")
    .withInitScript("findDogsInit.sql");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }

  @BeforeAll
  static void beforeAll() {
    postgresContainer.start();
  }

  @AfterAll
  static void afterAll() {
    postgresContainer.stop();
  }

  @LocalServerPort
  private Integer port;

  @BeforeEach
  void setUp() {
    RestAssured.basePath = "/api/v1/";
    RestAssured.port = port;
  }

  @Test
  void testGetExistingDogById() {
    // arrange
    String searchUrl = "dogs?name=B";

    // assert - GET by ID
    when()
        .get(searchUrl)
      .then()
        .log().all()
        .statusCode(200)
        .body(
          "numberOfResults", is(2),
          "dogs.name", hasItem("Bönan Fogelin"),
          "dogs.name", hasItem("Buddy")
        );
  }
}

