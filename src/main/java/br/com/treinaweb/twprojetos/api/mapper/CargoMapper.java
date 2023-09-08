package br.com.treinaweb.twprojetos.api.mapper;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.entities.Cargo;
import org.springframework.stereotype.Component;

@Component
public class CargoMapper {

    public Cargo mapToEntity(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();
        cargo.setNome(cargoDTO.getNome());
        return cargo;
    }
}
