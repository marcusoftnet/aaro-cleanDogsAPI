package com.aaro.cleanDogAPI.service;

import com.aaro.cleanDogAPI.service.dto.CreateDogRequest;
import com.aaro.cleanDogAPI.service.dto.CreateDogResponse;
import com.aaro.cleanDogAPI.entities.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CreateNewDogTests {

  @Test
  public void createNewDogWithValidParametersGeneratesEntity() {
    // arrange
    InMemoryDogRepository repository = new InMemoryDogRepository(new ArrayList<Dog>());
    DogService sut = new DogService(repository);
    CreateDogRequest request = new CreateDogRequest("Dogs name", "Dogs breed", 10);

    // act
    CreateDogResponse response = sut.createNewDog(request);

    // assert
    assertNotNull(response);
    var dog = response.dog();
    assertEquals("Dogs name", dog.name());
    assertEquals("Dogs breed", dog.breed());
    assertEquals(10, dog.tailLengthCm());

    var expectedId = repository.nextId() - 1;
    assertEquals(expectedId, dog.id());
  }

  @Test
  public void twoDogsCannotHaveTheSameName() {
    // arrange
    String knownName = "KNOWN NAME";
    var existingDogs = new ArrayList<Dog>();
    existingDogs.add(new Dog(1L, knownName, "Irrelevant", 10));
    InMemoryDogRepository repository = new InMemoryDogRepository(existingDogs);
    DogService sut = new DogService(repository);
    CreateDogRequest request = new CreateDogRequest(knownName, "Irrelevant", 10);

    // act
    IllegalArgumentException exception = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
      sut.createNewDog(request);
    });


    // assert
    assertNotNull(exception);
    var expectedMessage = String.format("There's already a dog named '%s' in the database", knownName);
    assertEquals( expectedMessage, exception.getMessage());
  }

  @Test
  public void tailLengthCannotBeZeroThatIsJustRude() {
    // arrange
    InMemoryDogRepository repository = new InMemoryDogRepository(new ArrayList<Dog>());
    DogService sut = new DogService(repository);
    CreateDogRequest request = new CreateDogRequest("Irrelevant", "Irrelevant", 0);

    // act
    IllegalArgumentException exception = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
      sut.createNewDog(request);
    });

    // assert
    assertNotNull(exception);
    assertEquals( "Tail length need to be above zero. Please!", exception.getMessage());
  }
}

