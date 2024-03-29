package br.com.treinaweb.twprojetos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Relation(collectionRelation = "projetos")
public class Projeto extends Entidade {

    @NotNull
    @Size(min = 3, max = 255)

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotNull
    @PastOrPresent
    @Column(name = "data_inicio", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicio;
    @PastOrPresent
    @Column(name = "data_fim")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFim;

    @JsonIgnore
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id_fk", nullable = false)
    private Cliente cliente;

    @JsonIgnore
    @Valid
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lider_id_fk", nullable = false)
    private Funcionario lider;

    @NotNull
    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal orcamento;

    @NotNull
    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal gastos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "projeto_funcionario",
            joinColumns = @JoinColumn(name = "projeto_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id_fk")
    )
    private List<Funcionario> equipe;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getLider() {
        return lider;
    }

    public void setLider(Funcionario lider) {
        this.lider = lider;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public BigDecimal getGastos() {
        return gastos;
    }

    public void setGastos(BigDecimal gastos) {
        this.gastos = gastos;
    }

    public List<Funcionario> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Funcionario> equipe) {
        this.equipe = equipe;
    }
}
