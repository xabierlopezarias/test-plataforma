package com.plataforma.prueba.domain.exception;

import com.plataforma.prueba.shared.domain.DomainError;

public final class InvalidDateException extends DomainError {
    public InvalidDateException(String message) {
        super("invalid_date_error", message);
    }
}
