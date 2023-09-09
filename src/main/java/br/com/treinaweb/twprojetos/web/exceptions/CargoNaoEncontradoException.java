package br.com.treinaweb.twprojetos.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CargoNaoEncontradoException extends EntityNotFoundException {

    public CargoNaoEncontradoException(Long id) {
        super(String.format("Cargo com o id %s não encontrado", id));
    }
}
