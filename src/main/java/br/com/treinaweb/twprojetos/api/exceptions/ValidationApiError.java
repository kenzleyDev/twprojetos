package br.com.treinaweb.twprojetos.api.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationApiError extends ApiErro{

private List<CampoErro> erros;

    public ValidationApiError() {}

    public ValidationApiError(int erro, String status, LocalDateTime timestamp, String messagem, List<CampoErro> erros) {
        super(erro, status, timestamp, messagem);
        this.erros = erros;
    }

    public List<CampoErro> getErros() {
        return erros;
    }

    public void setErros(List<CampoErro> erros) {
        this.erros = erros;
    }
}
