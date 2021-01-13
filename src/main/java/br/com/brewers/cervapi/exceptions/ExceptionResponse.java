package br.com.brewers.cervapi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class ExceptionResponse extends ResponseEntity<Map<String, String>> {

    private final ResponseStatusException exception;

    public ExceptionResponse(ResponseStatusException exception) {
        super(exception.getStatus());
        this.exception = exception;
    }

    @Override
    public Map<String, String> getBody() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        map.put("reason", exception.getReason());
        return map;
    }
}
