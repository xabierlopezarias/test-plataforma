package com.plataforma.prueba.shared.infrastructure;

public record RestResponse<T>(String error_details, String error_message, T result, String timestamp) {
}
