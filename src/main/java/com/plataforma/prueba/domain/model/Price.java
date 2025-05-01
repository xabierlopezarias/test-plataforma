package com.plataforma.prueba.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
    Long id,
    Brand brand,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Long priceList,
    Long productId,
    Integer priority,
    BigDecimal price,
    String currency
) {
}

