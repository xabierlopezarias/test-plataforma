package com.plataforma.prueba.application;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import com.plataforma.prueba.domain.exception.PriceNotFoundException;
import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.domain.repository.PriceRepository;
import com.plataforma.prueba.infraestructure.PriceMother;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceSearchTest {

  @Mock
  private PriceRepository repository;

  @InjectMocks
  private PriceSearch priceSearch;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void search_WhenPriceExists_ShouldReturnPriceResponse() {
    // Given
    Long brandId = 1L;
    Long productId = 100L;
    LocalDateTime date = LocalDateTime.of(2023, 8, 15, 10, 0);

    Price price = PriceMother.buildDomainMock();
    PriceResponse expectedResponse = PriceMother.buildPriceResponseMock();
    when(repository.find(brandId, productId, date)).thenReturn(Optional.of(price));
    // When
    PriceResponse actualResponse = priceSearch.search(brandId, productId, date);
    // Then
    assertEquals(expectedResponse, actualResponse);
    verify(repository, times(1)).find(brandId, productId, date);
  }

  @Test
  void search_WhenPriceDoesNotExist_ShouldThrowException() {
    // Given
    Long brandId = 1L;
    Long productId = 100L;
    LocalDateTime date = LocalDateTime.of(2023, 8, 15, 10, 0);

    // When
    when(repository.find(brandId, productId, date)).thenReturn(Optional.empty());

    // Then
    PriceNotFoundException exception = assertThrows(
        PriceNotFoundException.class,
        () -> priceSearch.search(brandId, productId, date)
    );

    assertEquals("No price found for product 100 in brand 1 at date 2023-08-15T10:00", exception.getMessage());
    verify(repository, times(1)).find(brandId, productId, date);
  }
}
