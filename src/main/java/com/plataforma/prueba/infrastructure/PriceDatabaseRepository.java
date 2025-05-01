package com.plataforma.prueba.infrastructure;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.domain.repository.PriceRepository;
import com.plataforma.prueba.infrastructure.persistence.mapper.PriceMapper;
import com.plataforma.prueba.infrastructure.persistence.repository.PriceH2Repository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceDatabaseRepository implements PriceRepository {

  private final PriceH2Repository priceH2Repository;
  private final PriceMapper priceMapper;

  @Autowired
  public PriceDatabaseRepository(PriceH2Repository priceH2Repository, PriceMapper priceMapper) {
    this.priceH2Repository = priceH2Repository;
    this.priceMapper = priceMapper;
  }

  @Override
  public Optional<Price> find(Long brandId, Long productId, LocalDateTime date) {
    return priceH2Repository.findPriceByBrandProductDateAndPriorityDESC(brandId, productId, date).map(priceMapper::toPriceDto);
  }


}
