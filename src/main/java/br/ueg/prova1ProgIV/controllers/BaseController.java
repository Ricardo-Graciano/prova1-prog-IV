package br.ueg.prova1ProgIV.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import br.ueg.prova1ProgIV.exceptions.InvalidArgumentsResponse;

public abstract class BaseController {

	@ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<?> handleWebExchangeBindException(WebExchangeBindException e) {
        return new InvalidArgumentsResponse(e);
    }
}
