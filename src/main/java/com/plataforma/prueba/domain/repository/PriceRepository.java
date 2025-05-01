package com.plataforma.prueba.domain.repository;

import com.plataforma.prueba.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> find(Long brandId, Long productId, LocalDateTime date);
}
