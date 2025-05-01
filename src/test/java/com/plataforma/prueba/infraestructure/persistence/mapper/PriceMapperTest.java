package com.plataforma.prueba.infraestructure.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.infraestructure.PriceMother;
import com.plataforma.prueba.infraestructure.persistence.entity.PriceJpaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;

class PriceMapperTest {

  private PriceMapper priceMapper;

  @BeforeEach
  void setUp() {
    priceMapper = Mappers.getMapper(PriceMapper.class);
  }

  @Test
  void testToPriceDto() {
    PriceJpaEntity entity = PriceMother.buildEntityMock();

    Price domain = priceMapper.toPriceDto(entity);

    assertNotNull(domain);
    assertEquals(entity.getId(), domain.id());
    assertEquals(entity.getProductId(), domain.productId());
    assertEquals(entity.getBrand().getId(), domain.brand().id());
    assertEquals(entity.getBrand().getName(), domain.brand().name());
    assertEquals(entity.getPrice(), domain.price());
    assertEquals(entity.getStartDate(), domain.startDate());
    assertEquals(entity.getEndDate(), domain.endDate());
  }



}

