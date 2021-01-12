package br.com.brewers.cervapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    @Override
    public String getMessage() {
        return "Objeto não existe no banco de dados!";
    }

    @Override
    public String getReason() {
        return "ID inválido";
    }
}
