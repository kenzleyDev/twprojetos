package br.com.treinaweb.twprojetos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProjetoNaoEncontradoException extends EntityNotFoundException {

    public ProjetoNaoEncontradoException(Long id) {
        super(String.format("Projeto com o ID %s não encontrado", id));
    }

}
