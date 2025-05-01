package com.plataforma.prueba.application;

import com.plataforma.prueba.shared.domain.bus.query.Query;
import java.time.LocalDateTime;


public record PriceQuery(Long brandId,Long productId,LocalDateTime date) implements Query {

}
