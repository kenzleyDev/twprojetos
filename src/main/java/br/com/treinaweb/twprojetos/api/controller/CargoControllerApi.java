package br.com.treinaweb.twprojetos.api.controller;

import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
