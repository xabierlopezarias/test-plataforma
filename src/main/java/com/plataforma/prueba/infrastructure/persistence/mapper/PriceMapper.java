package com.plataforma.prueba.infrastructure.persistence.mapper;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.infrastructure.persistence.entity.PriceJpaEntity;
import org.mapstruct.Mapper;


import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface PriceMapper {

  Price toPriceDto(PriceJpaEntity entity);

}

