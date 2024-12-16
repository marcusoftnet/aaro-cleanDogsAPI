package com.aaro.cleanDogAPI.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindDogByNameResponse {
  private List<DogDTO> dogs;
  public Integer getNumberOfResults() {
    return dogs.size();
  }
}
