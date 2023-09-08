package br.com.treinaweb.twprojetos.api.hatoas;

import br.com.treinaweb.twprojetos.api.controller.CargoControllerApi;
import br.com.treinaweb.twprojetos.api.controller.ClienteControllerApi;
import br.com.treinaweb.twprojetos.api.controller.FuncionarioControllerApi;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FuncionarioAssembler implements SimpleRepresentationModelAssembler<Funcionario> {
    @Override
    public void addLinks(EntityModel<Funcionario> resource) {
        Long cargoId = resource.getContent().getCargo().getId();
        Long id = resource.getContent().getId();

        Link cargoLink = linkTo(methodOn(CargoControllerApi.class).buscarPorId(cargoId))
                .withRel("cargo")
                .withType("GET");

        Link selfLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarPorId(id))
                .withSelfRel()
                        .withType("GET");

        resource.add(selfLink,cargoLink);

    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Funcionario>> resources) {
        Link selfLink = linkTo(methodOn(FuncionarioControllerApi.class).buscarTodos(null))
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink);

    }
}
