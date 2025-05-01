package com.plataforma.prueba.domain.exception;

import com.plataforma.prueba.shared.domain.DomainError;

public final class InvalidPriceException extends DomainError {
    public InvalidPriceException(String message) {
        super("invalid_price_error", message);
    }
}
