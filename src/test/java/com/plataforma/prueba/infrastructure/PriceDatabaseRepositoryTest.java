package com.plataforma.prueba.infrastructure;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.infrastructure.persistence.entity.PriceJpaEntity;
import com.plataforma.prueba.infrastructure.persistence.mapper.PriceMapper;
import com.plataforma.prueba.infrastructure.persistence.repository.PriceH2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;

class PriceDatabaseRepositoryTest {

  @Mock
  private PriceH2Repository priceH2Repository;

  @Mock
  private PriceMapper priceMapper;

  @InjectMocks
  private PriceDatabaseRepository priceDatabaseRepository;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFind_Successful() {
    // given
    Long brandId = 1L;
    Long productId = 100L;
    LocalDateTime date = LocalDateTime.now();

    PriceJpaEntity mockEntity = PriceMother.buildEntityMock();
    Optional<Price> mockPrice = Optional.of(PriceMother.buildDomainMock());

    // when
    when(priceH2Repository.findPriceByBrandProductDateAndPriorityDESC(brandId, productId, date))
        .thenReturn(Optional.of(mockEntity));
    when(priceMapper.toPriceDto(mockEntity)).thenReturn(mockPrice.get());

    Optional<Price> result = priceDatabaseRepository.find(brandId, productId, date);

    // then
    assertEquals(mockPrice, result);
    verify(priceH2Repository).findPriceByBrandProductDateAndPriorityDESC(brandId, productId, date);
    verify(priceMapper).toPriceDto(mockEntity);
  }

  @Test
  void testFind_ThrowsExceptionWhenNotFound() {
    // given
    Long brandId = 2L;
    Long productId = 200L;
    LocalDateTime date = LocalDateTime.now();

    when(priceH2Repository.findPriceByBrandProductDateAndPriorityDESC(brandId, productId, date))
        .thenReturn(Optional.empty());

    // when
    Optional<Price> result = priceDatabaseRepository.find(brandId, productId, date);


    //then
    assertTrue(result.isEmpty());
    verify(priceH2Repository).findPriceByBrandProductDateAndPriorityDESC(brandId, productId, date);
    verifyNoInteractions(priceMapper);
  }
}
