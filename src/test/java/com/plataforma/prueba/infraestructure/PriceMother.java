package com.plataforma.prueba.infraestructure;

import com.plataforma.prueba.application.PriceResponse;
import com.plataforma.prueba.domain.model.Brand;
import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.infraestructure.persistence.entity.BrandJpaEntity;
import com.plataforma.prueba.infraestructure.persistence.entity.PriceJpaEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceMother {


  public static PriceJpaEntity buildEntityMock() {
    PriceJpaEntity entity = new PriceJpaEntity();
    entity.setId(1L);
    entity.setProductId(1001L);

    BrandJpaEntity brand = new BrandJpaEntity();
    brand.setId(2001L);
    brand.setName("Test Brand");
    entity.setBrand(brand);

    entity.setPrice(new BigDecimal(19.99));
    entity.setStartDate(LocalDateTime.of(2023, 11, 1, 0, 0));
    entity.setEndDate(LocalDateTime.of(2023, 11, 30, 23, 59));
    return entity;
  }


  public static Price buildDomainMock() {
    return new Price(
        1L,
        new Brand(2001L, "Test Brand"),
        LocalDateTime.of(2023, 11, 1, 0, 0),
        LocalDateTime.of(2023, 11, 30, 23, 59),
        1L,
        1001L,
        1,
        new BigDecimal("19.99"),
        "EUR"
    );
  }

  public static PriceResponse buildPriceResponseMock() {
    return new PriceResponse(
        1001L,
        2001L,
        1L,
        new BigDecimal("19.99"),
        LocalDateTime.of(2023, 11, 1, 0, 0),
        LocalDateTime.of(2023, 11, 30, 23, 59)
    );
  }

}
