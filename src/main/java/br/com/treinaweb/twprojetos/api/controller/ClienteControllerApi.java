package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.api.hatoas.ClienteAssembler;
import br.com.treinaweb.twprojetos.api.hatoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.ClienteService;
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
@RequestMapping("/api/v1/clientes")
public class ClienteControllerApi {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteAssembler clienteAssembler;

    @Autowired
    private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cliente>> buscarTodos(Pageable pagination) {
        Page<Cliente> clientes = clienteService.buscarTodos(pagination);
        return pagedResourcesAssembler.toModel(clientes, clienteAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return clienteAssembler.toModel(cliente);
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> buscarProjetos(@PathVariable Long id) {
        List<Projeto> projetos = clienteService.buscarPorId(id).getProjetos();

        return projetoAssembler.toCollectionModel(projetos);

    }

}
