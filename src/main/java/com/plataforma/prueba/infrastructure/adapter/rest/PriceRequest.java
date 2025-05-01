package com.plataforma.prueba.infrastructure.adapter.rest;


import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


public record PriceRequest(
    @NotEmpty Long brandId,
    @NotEmpty Long productId,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date
) {}
