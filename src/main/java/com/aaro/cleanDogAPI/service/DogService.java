package com.aaro.cleanDogAPI.service;

import com.aaro.cleanDogAPI.service.dto.*;
import com.aaro.cleanDogAPI.entities.Dog;
import com.aaro.cleanDogAPI.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DogService {
  private final DogRepository repository;

  public CreateDogResponse createNewDog(CreateDogRequest request) {
    // validations
    if(nameExists(request.name())) {
      var message = String.format("There's already a dog named '%s' in the database", request.name());
      throw new IllegalArgumentException(message);
    }

    if(request.tailLength() <= 0) {
      throw new IllegalArgumentException("Tail length need to be above zero. Please!");
    }
    // Request is valid - let's store it
    // Map to entity from request
    Dog dogToSave = new Dog(null, request.name(), request.breed(), request.tailLength());

    // perform save
    Dog savedDog = repository.save(dogToSave);

    // Map to response and return
    return new CreateDogResponse(DogDTO.fromDog(savedDog));
  }

  private boolean nameExists(String name) {
    return repository.findAll().stream().anyMatch(d -> d.getName().equals(name));
  }

  public GetDogByIdResponse getDogById(GetDogByIdRequest request) {
    Dog dog =  repository.findById(request.id()).orElseThrow(() -> new IllegalArgumentException("Dog not found"));
    return new GetDogByIdResponse(DogDTO.fromDog(dog));
  }

  public DeleteDogByIdResponse deleteDogById(DeleteDogByIdRequest request) {
    repository.deleteById(request.id());
    return new DeleteDogByIdResponse("Dog was deleted");
  }

  public FindDogByNameResponse findDogByName(FindDogByNameRequest request) {
    List<DogDTO> dogs = repository
      .findAll()
      .stream()
      .filter(dog -> nameStartsWith(request, dog))
      .map(DogDTO::fromDog)
      .toList();

    return new FindDogByNameResponse(dogs);
  }

  private static boolean nameStartsWith(FindDogByNameRequest request, Dog dog) {
    return dog.getName().toLowerCase().startsWith(request.name().toLowerCase());
  }
}
