package com.plataforma.prueba.application;

import com.plataforma.prueba.domain.exception.PriceNotFoundException;
import com.plataforma.prueba.domain.repository.PriceRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class PriceSearch {

  private final PriceRepository repository;

  public PriceSearch(PriceRepository repository) {this.repository = repository;}

  public PriceResponse search(Long brandId, Long productId, LocalDateTime date) {
    return repository.find(brandId, productId, date).map(PriceResponse::fromAggregate)
        .orElseThrow(() -> new PriceNotFoundException("No price found for product %d in brand %d at date %s".formatted(productId,brandId,date)
    ));
  }

}
