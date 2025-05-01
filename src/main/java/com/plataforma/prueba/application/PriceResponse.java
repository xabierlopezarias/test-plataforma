package com.plataforma.prueba.application;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.shared.domain.bus.query.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(Long productId, Long brandId, Long priceList, BigDecimal price, String currency, LocalDateTime startDate, LocalDateTime endDate) implements Response {

  public static PriceResponse fromAggregate(Price price) {
    return new PriceResponse(price.productId(),price.brand().id(),price.priceList(),price.price(),price.currency(),price.startDate(),price.endDate());
  }
}
