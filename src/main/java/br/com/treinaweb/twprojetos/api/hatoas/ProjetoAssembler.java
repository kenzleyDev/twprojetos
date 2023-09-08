package br.com.treinaweb.twprojetos.api.hatoas;

import br.com.treinaweb.twprojetos.api.controller.ClienteControllerApi;
import br.com.treinaweb.twprojetos.api.controller.FuncionarioControllerApi;
import br.com.treinaweb.twprojetos.api.controller.ProjetoControllerApi;
import br.com.treinaweb.twprojetos.entities.Projeto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        Long clienteId = resource.getContent().getCliente().getId();
        Long liderId = resource.getContent().getLider().getId();
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(ProjetoControllerApi.class).buscarPorId(id))
                .withSelfRel()
                .withType("GET");

        Link editarLink = linkTo(methodOn(ProjetoControllerApi.class).atualizar(null, id))
                .withSelfRel()
                .withType("PUT");

        Link liderLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarPorId(liderId))
                .withRel("lider")
                .withType("GET");

        Link clienteLink = linkTo(methodOn(ClienteControllerApi.class).buscarPorId(clienteId))
                .withRel("cliente")
                .withType("GET");

        Link excluirLink = linkTo(methodOn(ProjetoControllerApi.class).excluirPorId(id))
                .withSelfRel()
                .withType("DELETE");

        Link equipeLink = linkTo(methodOn(ProjetoControllerApi.class).buscarEquipe(id))
                .withRel("equipe")
                        .withType("GET");

        Link atualizarEquipeLink = linkTo(methodOn(ProjetoControllerApi.class).atualizarEquipe(id, null))
                .withRel("equipe")
                .withType("PATCH");

        resource.add(selfLink,liderLink, clienteLink, editarLink, excluirLink, equipeLink, atualizarEquipeLink);

    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {

        Link selfLink = linkTo(methodOn(ProjetoControllerApi.class).buscarTodos(null))
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink);
    }
}
