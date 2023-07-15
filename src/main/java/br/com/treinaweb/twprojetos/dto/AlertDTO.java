package br.com.treinaweb.twprojetos.dto;

public class AlertDTO {

    private String mensagem;

    private String classeCSS;

    public AlertDTO(String mensagem, String classeCSS) {
        this.mensagem = mensagem;
        this.classeCSS = classeCSS;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getClasseCSS() {
        return classeCSS;
    }

    public void setClasseCSS(String classeCSS) {
        this.classeCSS = classeCSS;
    }
}
