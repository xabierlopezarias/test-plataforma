package com.plataforma.prueba.application;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.shared.domain.bus.query.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(Long productId, Long brandId, Long priceList, BigDecimal price, LocalDateTime startDate, LocalDateTime endDate) implements Response {

  public static PriceResponse fromAggregate(Price price) {
    return new PriceResponse(price.getProductId(),price.getBrand().id(),price.getPriceList(),price.getPrice(),price.getStartDate(),price.getEndDate());
  }
}
