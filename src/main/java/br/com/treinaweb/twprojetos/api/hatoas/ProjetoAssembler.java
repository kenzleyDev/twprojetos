package br.com.treinaweb.twprojetos.api.hatoas;

import br.com.treinaweb.twprojetos.entities.Projeto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {

    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {

    }
}
