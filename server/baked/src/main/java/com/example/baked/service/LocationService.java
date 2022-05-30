package com.example.baked.service;

import com.example.baked.model.Location;
import com.example.baked.repo.LocationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
  private final LocationRepo locationRepo;

  public Location saveLocation(Location location) {
    return locationRepo.save(location);
  }
}
