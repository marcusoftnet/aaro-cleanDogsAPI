package com.aaro.cleanDogAPI.controller;

import com.aaro.cleanDogAPI.service.DogService;
import com.aaro.cleanDogAPI.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/dogs")
@RequiredArgsConstructor
public class DogController {
  private final DogService dogService;

  @PostMapping
  public ResponseEntity<?> createNewDog(@RequestBody CreateDogRequest request) {
    try {
      CreateDogResponse response = dogService.createNewDog(request);

      return ResponseEntity
        .status(HttpStatus.CREATED)
        .location(createLocationUri(response))
        .body(response);
    } catch (IllegalArgumentException e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetDogByIdResponse> getDogById(@PathVariable Long id) {
    GetDogByIdRequest request = new GetDogByIdRequest(id);
    GetDogByIdResponse response = dogService.getDogById(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<FindDogByNameResponse> findDogs(@RequestParam String name) {
    FindDogByNameRequest request = new FindDogByNameRequest(name);
    FindDogByNameResponse response = dogService.findDogByName(request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteDogByIdResponse> deleteDogById(@PathVariable Long id) {
    DeleteDogByIdRequest request = new DeleteDogByIdRequest(id);
    dogService.deleteDogById(request);

    return ResponseEntity.noContent().build();
  }


  private static URI createLocationUri(CreateDogResponse response) {
    return ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(response.dog().id())
      .toUri();
  }
}
