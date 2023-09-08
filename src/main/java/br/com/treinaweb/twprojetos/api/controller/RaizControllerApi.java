package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.api.hatoas.RaizModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class RaizControllerApi {

    @GetMapping
    public RaizModel raiz() {

        RaizModel raizModel = new RaizModel();
        Link cargosLink = linkTo(methodOn(CargoControllerApi.class).buscarTodos(null))
                .withRel("cargos")
                .withType("GET");
        raizModel.add(cargosLink);
        return raizModel;
    }
}
