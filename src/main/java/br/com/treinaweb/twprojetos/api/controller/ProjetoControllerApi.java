package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.api.dto.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.hatoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.api.hatoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoControllerApi {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private PagedResourcesAssembler<Projeto> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Projeto>> buscarTodos(Pageable pagination) {
        Page<Projeto> projetos = projetoService.buscarTodos(pagination);
        return pagedResourcesAssembler.toModel(projetos, projetoAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Projeto> buscarPorId(@PathVariable Long id) {
        Projeto projeto = projetoService.buscarPorId(id);
        return projetoAssembler.toModel(projeto);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Projeto> cadastrar(@RequestBody @Valid ProjetoDTO projetoDTO) {
        Projeto projeto = projetoService.cadastrar(projetoDTO);
        return projetoAssembler.toModel(projeto);
    }

    @PutMapping("/{id}")
    public EntityModel<Projeto> atualizar(@RequestBody @Valid ProjetoDTO projetoDTO, @PathVariable Long id) {
        Projeto projeto = projetoService.atualizar(projetoDTO, id);
        return projetoAssembler.toModel(projeto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        projetoService.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/equipe")
    public CollectionModel<EntityModel<Funcionario>> buscarEquipe(@PathVariable Long id) {
        List<Funcionario> equipe = projetoService.buscarPorId(id).getEquipe();
        return funcionarioAssembler.toCollectionModel(equipe);
    }

    @PatchMapping("/{id}/equipe")
    public CollectionModel<EntityModel<Funcionario>> atualizarEquipe(@PathVariable Long id, @RequestBody @Valid EquipeDTO equipeDTO) {

        List<Funcionario> equipe = projetoService.atualizarEquipe(equipeDTO, id);
        return funcionarioAssembler.toCollectionModel(equipe);

    }
}
