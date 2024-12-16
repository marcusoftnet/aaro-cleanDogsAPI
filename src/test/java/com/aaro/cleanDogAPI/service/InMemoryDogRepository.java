package com.aaro.cleanDogAPI.service;

import com.aaro.cleanDogAPI.entities.Dog;
import com.aaro.cleanDogAPI.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDogRepository implements DogRepository {
  private final ArrayList<Dog> dogs = new ArrayList<>();

  public InMemoryDogRepository(ArrayList<Dog> dogs) {
    this.dogs.addAll(dogs);
  }

  public Long nextId() { return (long) dogs.size() + 1; }

  @Override
  public Dog save(Dog dogToSave) {
    dogToSave.setId(this.nextId());
    this.dogs.add(dogToSave);
    return dogToSave;
  }

  @Override
  public List<Dog> findAll() {
    return dogs;
  }

  @Override
  public Optional<Dog> findById(Long id) {
    return dogs.stream().filter(dog -> dog.getId().equals(id)).findFirst();
  }

  @Override
  public void deleteById(long id) {
    dogs.removeIf(dog -> dog.getId().equals(id));
  }
}

