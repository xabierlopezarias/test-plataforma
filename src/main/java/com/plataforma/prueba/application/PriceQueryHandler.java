package com.plataforma.prueba.application;

import com.plataforma.prueba.domain.exception.PriceNotFoundException;
import com.plataforma.prueba.shared.domain.bus.query.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class PriceQueryHandler implements QueryHandler<PriceQuery, PriceResponse> {

  private final PriceSearch priceSearch;

  public PriceQueryHandler(PriceSearch priceSearch) {
    this.priceSearch = priceSearch;
  }

  @Override
  public PriceResponse handle(PriceQuery query) throws PriceNotFoundException {

    return priceSearch.search(query.brandId(), query.productId(), query.date());
  }

}
