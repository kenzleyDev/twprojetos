package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControllerApi {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Cargo> buscarTodos() {
        return cargoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Cargo buscarPorId(@PathVariable long id) {
        return cargoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cargo cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
        return cargoService.cadastrar(cargoDTO);
    }

    @PutMapping("/{id}")
    public Cargo atualizar(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        return cargoService.atualizar(cargoDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        cargoService.excluirPorId(id);
    }
}
