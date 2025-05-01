package com.plataforma.prueba.domain.exception;

import com.plataforma.prueba.shared.domain.DomainError;

public final class InvalidDateRangeException extends DomainError {
    public InvalidDateRangeException(String message) {
        super("invalid_date_range_error", message);
    }
}
