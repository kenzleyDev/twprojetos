package br.com.treinaweb.twprojetos.api.exceptions;

import java.time.LocalDateTime;

public class ApiErro {

    private int erro;
    private String status;
    private LocalDateTime timestamp;

    public ApiErro() {
    }

    public ApiErro(int erro, String status, LocalDateTime timestamp, String messagem) {
        this.erro = erro;
        this.status = status;
        this.timestamp = timestamp;
        this.messagem = messagem;
    }

    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    private String messagem;
}
