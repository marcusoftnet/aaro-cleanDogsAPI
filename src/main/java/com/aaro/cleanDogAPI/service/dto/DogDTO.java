package com.aaro.cleanDogAPI.service.dto;

import com.aaro.cleanDogAPI.entities.Dog;

public record DogDTO(Long id, String name, String breed, int tailLengthCm) {
  public static DogDTO fromDog(Dog dogEntity) {
    return new DogDTO(
      dogEntity.getId(),
      dogEntity.getName(),
      dogEntity.getBreedName(),
      dogEntity.getTailLengthCm()
    );
  }
}
