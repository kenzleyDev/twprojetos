package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.api.hatoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.api.hatoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioControllerApi {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private PagedResourcesAssembler<Funcionario> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Funcionario>> buscarTodos(Pageable pagination) {
        Page<Funcionario> funcionarios = funcionarioService.buscarTodos(pagination);

        return pagedResourcesAssembler.toModel(funcionarios, funcionarioAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Funcionario> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return funcionarioAssembler.toModel(funcionario);
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> buscarProjetos(@PathVariable Long id){
        List<Projeto> projetos = funcionarioService.buscarPorId(id).getProjetos();
        return projetoAssembler.toCollectionModel(projetos);
    }


}
