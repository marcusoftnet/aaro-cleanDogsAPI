package com.aaro.cleanDogAPI.repository;

import com.aaro.cleanDogAPI.entities.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DogRepositoryWrapper implements DogRepository {

  private final DogRepositoryJPA jpaRepository;

  @Override
  public Dog save(Dog dog) {
  return jpaRepository.save(dog);
  }

  @Override
  public List<Dog> findAll() {
    return jpaRepository.findAll();
  }

  @Override
  public Optional<Dog> findById(Long id) {
    return jpaRepository.findById(id);
  }

  @Override
  public void deleteById(long id) { jpaRepository.deleteById(id); }
}
