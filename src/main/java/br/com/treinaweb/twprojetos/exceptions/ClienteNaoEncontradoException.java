package br.com.treinaweb.twprojetos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends EntityNotFoundException {
      public ClienteNaoEncontradoException(Long id) {
            super(String.format("Cliente com o id %s não encontrado", id));
        }
}
