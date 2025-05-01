package com.plataforma.prueba.infraestructure.persistence.mapper;

import com.plataforma.prueba.domain.model.Price;
import com.plataforma.prueba.infraestructure.persistence.entity.PriceJpaEntity;
import org.mapstruct.Mapper;


import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface PriceMapper {

  Price toPriceDto(PriceJpaEntity entity);

}

