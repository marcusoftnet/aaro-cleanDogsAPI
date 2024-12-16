package com.aaro.cleanDogAPI.service;

import com.aaro.cleanDogAPI.entities.Dog;
import com.aaro.cleanDogAPI.service.dto.FindDogByNameRequest;
import com.aaro.cleanDogAPI.service.dto.FindDogByNameResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SearchDogsByNameContainingTests {

  @Test
  public void searchDogsByNameExactly() {
    // arrange
    var existingDogs = new ArrayList<Dog>();
    existingDogs.add(new Dog(1L, "Dogs full name", "Irrelevant", 10));
    existingDogs.add(new Dog(2L, "Another name", "Irrelevant", 10));
    InMemoryDogRepository repository = new InMemoryDogRepository(existingDogs);
    DogService sut = new DogService(repository);

    var request = new FindDogByNameRequest("Dogs full name");

    // act
    FindDogByNameResponse response = sut.findDogByName(request);

    // assert
    assertNotNull(response);
    assertEquals(1, response.getNumberOfResults());
    assertEquals("Dogs full name", response.getDogs().getFirst().name());
  }

  @Test
  public void searchDogsByNamesStartingWithCaseInsensitive() {
    // arrange
    var existingDogs = new ArrayList<Dog>();
    existingDogs.add(new Dog(1L, "A name", "Irrelevant", 10));
    existingDogs.add(new Dog(2L, "Another name", "Irrelevant", 10));
    existingDogs.add(new Dog(3L, "But wait - another name", "Irrelevant", 10));
    InMemoryDogRepository repository = new InMemoryDogRepository(existingDogs);
    DogService sut = new DogService(repository);

    var request = new FindDogByNameRequest("a");

    // act
    FindDogByNameResponse response = sut.findDogByName(request);

    // assert
    assertNotNull(response);
    assertEquals(2, response.getNumberOfResults());
    assertEquals("A name", response.getDogs().getFirst().name());
    assertEquals("Another name", response.getDogs().getLast().name());
  }
}
