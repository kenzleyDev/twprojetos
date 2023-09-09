package br.com.treinaweb.twprojetos.web.exceptions;

public class FuncionarioEhLiderDeProjetoException extends RuntimeException{

    public FuncionarioEhLiderDeProjetoException(Long id) {
        super(String.format("Não é possível excluir o Funcionário com ID %s, pois o Funcionário é lider de projeto(s)", id));
    }

}
