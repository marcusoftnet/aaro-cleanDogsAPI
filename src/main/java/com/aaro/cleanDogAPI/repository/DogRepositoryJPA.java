package com.aaro.cleanDogAPI.repository;

import com.aaro.cleanDogAPI.entities.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepositoryJPA extends JpaRepository<Dog, Long> {
}

