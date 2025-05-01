package com.plataforma.prueba.domain.model;

import com.plataforma.prueba.domain.exception.InvalidDateException;
import com.plataforma.prueba.domain.exception.InvalidDateRangeException;
import com.plataforma.prueba.domain.exception.InvalidPriceException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Price {

  private final Long id;
  private final Brand brand;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;
  private final Long priceList;
  private final Long productId;
  private final Integer priority;
  private final BigDecimal price;
  private final String currency;

  public Price(Long id, Brand brand, LocalDateTime startDate, LocalDateTime endDate,
      Long priceList, Long productId, Integer priority, BigDecimal price, String currency) {

    if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
      throw new InvalidPriceException("El precio no puede ser nulo o negativo");
    }
    if (startDate == null || endDate == null) {
      throw new InvalidDateException("Las fechas de inicio y fin son obligatorias");
    }
    if (endDate.isBefore(startDate)) {
      throw new InvalidDateRangeException("La fecha de fin no puede ser anterior a la fecha de inicio");
    }

    this.id = id;
    this.brand = brand;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceList = priceList;
    this.productId = productId;
    this.priority = priority;
    this.price = price;
    this.currency = currency;
  }
}
