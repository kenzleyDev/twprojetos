package br.com.treinaweb.twprojetos.web.exceptions;

public class ClientePossuiProjetosException extends RuntimeException{

    public ClientePossuiProjetosException(Long id) {
        super(String.format("Cliente com o ID %s possui projetos relacionados", id));
    }
}
