package br.com.treinaweb.twprojetos.api.hatoas;

import br.com.treinaweb.twprojetos.api.controller.ClienteControllerApi;
import br.com.treinaweb.twprojetos.entities.Cliente;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteAssembler implements SimpleRepresentationModelAssembler<Cliente> {
    @Override
    public void addLinks(EntityModel<Cliente> resource) {
        Long id = resource.getContent().getId();;
        Link selfLink = linkTo(methodOn(ClienteControllerApi.class).buscarPorId(id))
                .withSelfRel()
                .withType("GET");

        Link projetosLink = linkTo(methodOn(ClienteControllerApi.class).buscarProjetos(id))
                .withRel("projetos")
                        .withType("GET");

        resource.add(selfLink, projetosLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cliente>> resources) {
        Link selfLink = linkTo(methodOn(ClienteControllerApi.class).buscarTodos(null))
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink);
    }
}
