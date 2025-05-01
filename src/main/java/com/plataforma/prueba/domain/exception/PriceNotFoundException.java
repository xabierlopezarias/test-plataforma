package com.plataforma.prueba.domain.exception;

import com.plataforma.prueba.shared.domain.DomainError;


public final class PriceNotFoundException extends DomainError {
    public PriceNotFoundException(String message) {
        super("price_not_exist", message);
    }
} 
