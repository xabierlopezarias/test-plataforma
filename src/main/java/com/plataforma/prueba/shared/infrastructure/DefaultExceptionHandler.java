package com.plataforma.prueba.shared.infrastructure;

import com.plataforma.prueba.domain.exception.InvalidDateException;
import com.plataforma.prueba.domain.exception.InvalidDateRangeException;
import com.plataforma.prueba.domain.exception.InvalidPriceException;
import com.plataforma.prueba.domain.exception.PriceNotFoundException;
import com.plataforma.prueba.shared.domain.DomainError;
import com.plataforma.prueba.shared.infrastructure.dates.DateUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundExceptions(PriceNotFoundException ex) {
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidPriceException.class, InvalidDateException.class, InvalidDateRangeException.class})
    protected ResponseEntity<Object> handleDomainValidationExceptions(DomainError ex) {
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception exception, HttpStatus statusCode) {
        String errorCode = (exception instanceof HttpMessageNotReadableException) 
                ? ((HttpMessageNotReadableException)exception).getCause().getClass().getSimpleName() 
                : exception.getClass().getSimpleName();
                
        String errorMessage = exception.getMessage();

        RestResponse<String> restResponse = new RestResponse<>(errorCode, errorMessage, "", DateUtils.getNowISO6801());

        return new ResponseEntity<>(restResponse, statusCode);
    }
}