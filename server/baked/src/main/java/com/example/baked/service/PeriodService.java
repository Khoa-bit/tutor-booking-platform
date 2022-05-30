package com.example.baked.service;

import com.example.baked.model.Period;
import com.example.baked.repo.PeriodRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PeriodService {
  private final PeriodRepo periodRepo;

  public Period savePeriod(Period period) {
    log.info("Saving new Period {} to the database", period);
    return periodRepo.findByHash(period.getHash()).orElseGet(() -> periodRepo.save(period));
  }
}
