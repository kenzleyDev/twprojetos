package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.api.docs.CargoControllerApiDoc;
import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.api.hatoas.CargoAssembler;
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.services.CargoService;
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

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControllerApi implements CargoControllerApiDoc {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoAssembler cargoAssembler;

    @Autowired
    private PagedResourcesAssembler<Cargo> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cargo>> buscarTodos(Pageable paginacao) {
        Page<Cargo> cargoList = cargoService.buscarTodos(paginacao);
        return pagedResourcesAssembler.toModel(cargoList, cargoAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cargo> buscarPorId(@PathVariable long id) {
        Cargo cargo = cargoService.buscarPorId(id);
        return cargoAssembler.toModel(cargo);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Cargo> cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
        Cargo cadastrar = cargoService.cadastrar(cargoDTO);
        return cargoAssembler.toModel(cadastrar);
    }

    @PutMapping("/{id}")
    public EntityModel<Cargo> atualizar(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        Cargo atualizar = cargoService.atualizar(cargoDTO, id);
        return cargoAssembler.toModel(atualizar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        cargoService.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
