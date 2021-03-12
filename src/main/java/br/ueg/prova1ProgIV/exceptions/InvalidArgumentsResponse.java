package br.ueg.prova1ProgIV.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;

public class InvalidArgumentsResponse extends ResponseEntity<Map<String, Map<String, String>>> {

    private final WebExchangeBindException exception;

    public InvalidArgumentsResponse(WebExchangeBindException e) {
        super(e.getStatus());
        this.exception = e;
    }

    @Override
    public Map<String, Map<String, String>> getBody() {
        final HashMap<String, String> errorsMap = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        final Map<String, Map<String, String>> map = new HashMap<>();
        map.put("errors", errorsMap);
        return map;
    }
}
