package com.aaro.cleanDogAPI.repository;

import com.aaro.cleanDogAPI.entities.Dog;

import java.util.List;
import java.util.Optional;

public interface DogRepository {
  Dog save(Dog dogToSave);
  List<Dog> findAll();
  Optional<Dog> findById(Long id);
  void deleteById(long id);
}

