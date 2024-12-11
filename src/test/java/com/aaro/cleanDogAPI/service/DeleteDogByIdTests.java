package com.aaro.cleanDogAPI.service;

import com.aaro.cleanDogAPI.entities.Dog;
import com.aaro.cleanDogAPI.service.dto.DeleteDogByIdRequest;
import com.aaro.cleanDogAPI.service.dto.DeleteDogByIdResponse;
import com.aaro.cleanDogAPI.service.dto.GetDogByIdRequest;
import com.aaro.cleanDogAPI.service.dto.GetDogByIdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteDogByIdTests {

  @Test
  public void deleteExistingDogWorks() {
    // arrange
    var existingDogs = new ArrayList<Dog>();
    existingDogs.add(new Dog(1L, "Dogs name", "Dogs breed", 10));
    InMemoryDogRepository repository = new InMemoryDogRepository(existingDogs);
    DogService sut = new DogService(repository);

    var request = new DeleteDogByIdRequest(1L);

    // act
    DeleteDogByIdResponse response = sut.deleteDogById(request);

    // assert
    assertNotNull(response);
    assertEquals("Dog was deleted", response.message());
  }
}
