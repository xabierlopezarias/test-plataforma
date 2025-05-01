package com.plataforma.prueba.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.plataforma.prueba.domain.exception.InvalidPriceException;
import com.plataforma.prueba.domain.exception.InvalidDateException;
import com.plataforma.prueba.domain.exception.InvalidDateRangeException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PriceTest {

    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDateTime tomorrow = now.plusDays(1);
    private final LocalDateTime yesterday = now.minusDays(1);
    private final Brand sampleBrand = new Brand(1L, "Test Brand");

    @Test
    void shouldCreateValidPrice() {
        // given
        Long id = 1L;
        Brand brand = sampleBrand;
        LocalDateTime startDate = yesterday;
        LocalDateTime endDate = tomorrow;
        Long priceList = 2L;
        Long productId = 1L;
        Integer priority = 1;
        BigDecimal price = BigDecimal.TEN;
        String currency = "EUR";

        // when
        Price validPrice = new Price(id, brand, startDate, endDate, priceList, productId, priority, price, currency);

        // then
        assertNotNull(validPrice);
        assertEquals(id, validPrice.getId());
        assertEquals(brand, validPrice.getBrand());
        assertEquals(startDate, validPrice.getStartDate());
        assertEquals(endDate, validPrice.getEndDate());
        assertEquals(priceList, validPrice.getPriceList());
        assertEquals(productId, validPrice.getProductId());
        assertEquals(priority, validPrice.getPriority());
        assertEquals(price, validPrice.getPrice());
        assertEquals(currency, validPrice.getCurrency());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        BigDecimal nullPrice = null;

        InvalidPriceException exception = assertThrows(InvalidPriceException.class, () -> 
            new Price(1L, sampleBrand, yesterday, tomorrow, 1L, 1L, 1, nullPrice, "EUR")
        );
        
        assertEquals("El precio no puede ser nulo o negativo", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        BigDecimal negativePrice = BigDecimal.valueOf(-10);

        InvalidPriceException exception = assertThrows(InvalidPriceException.class, () -> 
            new Price(1L, sampleBrand, yesterday, tomorrow, 1L, 1L, 1, negativePrice, "EUR")
        );
        
        assertEquals("El precio no puede ser nulo o negativo", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStartDateIsNull() {
        LocalDateTime nullStartDate = null;

        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> 
            new Price(1L, sampleBrand, nullStartDate, tomorrow, 1L, 1L, 1, BigDecimal.TEN, "EUR")
        );
        
        assertEquals("Las fechas de inicio y fin son obligatorias", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsNull() {
        LocalDateTime nullEndDate = null;

        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> 
            new Price(1L, sampleBrand, yesterday, nullEndDate, 1L, 1L, 1, BigDecimal.TEN, "EUR")
        );
        
        assertEquals("Las fechas de inicio y fin son obligatorias", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {
        LocalDateTime startDate = tomorrow;
        LocalDateTime endDate = yesterday;

        InvalidDateRangeException exception = assertThrows(InvalidDateRangeException.class, () -> 
            new Price(1L, sampleBrand, startDate, endDate, 1L, 1L, 1, BigDecimal.TEN, "EUR")
        );
        
        assertEquals("La fecha de fin no puede ser anterior a la fecha de inicio", exception.getMessage());
    }
}
