package br.com.treinaweb.twprojetos.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    @Override
    public String toString() {

        StringBuilder enderecoCompleto = new StringBuilder();
        enderecoCompleto
                .append(logradouro)
                .append(", n° ")
                .append(numero)
                .append(", ")
                .append(complemento)
                .append(" - ")
                .append(bairro)
                .append(". ")
                .append(uf.getDescricao())
                .append(" - ")
                .append(cidade)
                .append(". CEP: ")
                .append(cep);

        return enderecoCompleto.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && uf == endereco.uf && Objects.equals(cidade, endereco.cidade) && Objects.equals(bairro, endereco.bairro) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(cep, endereco.cep) && Objects.equals(numero, endereco.numero) && Objects.equals(complemento, endereco.complemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uf, cidade, bairro, logradouro, cep, numero, complemento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
