package com.plataforma.prueba.infrastructure.adapter.rest;

import com.plataforma.prueba.application.PriceQuery;
import com.plataforma.prueba.application.PriceResponse;
import com.plataforma.prueba.shared.domain.bus.query.QueryBus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices") 
@CrossOrigin("*")
public class PriceController {

  private final QueryBus queryBus;

  public PriceController(QueryBus queryBus) {
    this.queryBus = queryBus;
  }

  @GetMapping
    public PriceResponse getPrice(@Valid PriceRequest priceRequest) {
      return queryBus.ask(new PriceQuery(priceRequest.brandId(), priceRequest.productId(), priceRequest.date()));
    }

}