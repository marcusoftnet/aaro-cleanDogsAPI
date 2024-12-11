package com.aaro.cleanDogAPI.service;

import com.aaro.cleanDogAPI.entities.Dog;
import com.aaro.cleanDogAPI.service.dto.GetDogByIdRequest;
import com.aaro.cleanDogAPI.service.dto.GetDogByIdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetDogByIdTests {

  @Test
  public void getExistingDogWorks() {
    // arrange
    var existingDogs = new ArrayList<Dog>();
    existingDogs.add(new Dog(1L, "Dogs name", "Dogs breed", 10));
    InMemoryDogRepository repository = new InMemoryDogRepository(existingDogs);
    DogService sut = new DogService(repository);

    var request = new GetDogByIdRequest(1L);

    // act
    GetDogByIdResponse response = sut.getDogById(request);

    // assert
    assertNotNull(response);
    assertEquals(1L, response.dog().id());
    assertEquals("Dogs name", response.dog().name());
    assertEquals("Dogs breed", response.dog().breed());
    assertEquals(10, response.dog().tailLengthCm());

    var expectedId = repository.nextId() - 1;
  }

  @Test
  public void getNonExistingDogFails() {
    // arrange
    InMemoryDogRepository repository = new InMemoryDogRepository(new ArrayList<Dog>());
    DogService sut = new DogService(repository);

    var request = new GetDogByIdRequest(1L);

    // act
    IllegalArgumentException exception = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
      sut.getDogById(request);
    });

    // assert
    assertNotNull(exception);
    assertEquals( "Dog not found", exception.getMessage());
  }
}
