package br.com.treinaweb.twprojetos.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa extends Entidade {

    @NotNull
    @Size(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String nome;

    @NotNull
    @Size(min = 14, max = 14)
    @CPF
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @NotNull
    @Size(min = 15, max = 15)
    @Pattern(regexp = "^(?:(?:\\+|00)?55\\s?)?(?:\\(?0?[1-9]{2}\\)?\\s?)?(?:9[2-9]\\d{3}|\\d{4})[-.\\s]?\\d{4}$", message = "Deve estar no formato (99) 99999-9999")
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotNull
    @Size(min = 10, max = 80)
    @Email
    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @NotNull
    @Past
    @Column(name = "data_nascimento", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id_fk", nullable = false)
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
