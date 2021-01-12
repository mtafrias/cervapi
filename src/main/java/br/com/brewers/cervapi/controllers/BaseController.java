package br.com.brewers.cervapi.controllers;

import br.com.brewers.cervapi.exceptions.ExceptionResponse;
import br.com.brewers.cervapi.exceptions.InvalidArgumentsResponse;
import br.com.brewers.cervapi.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
public abstract class BaseController {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<?> handleWebExchangeBindException(WebExchangeBindException e) {
        log.error(e.getMessage());
        return new InvalidArgumentsResponse(e);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        return new ExceptionResponse(e);
    }

}
