package br.com.treinaweb.twprojetos.services;

import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.exceptions.CargoNaoEncontradoException;
import br.com.treinaweb.twprojetos.exceptions.CargoPossuiFuncionariosException;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public List<Cargo> buscarTodos() {
        return cargoRepository.findAll();
    }

    public Cargo buscarPorId(Long id) {
        Cargo cargoEncontrado = cargoRepository.findById(id).
                orElseThrow(() -> new CargoNaoEncontradoException(id));

        return cargoEncontrado;
    }

    public Cargo cadastrar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public Cargo atualizar(Cargo cargo, Long id) {
        buscarPorId(id);
        return cargoRepository.save(cargo);
    }

    public void excluirPorId(Long id) {
        Cargo cargoEncontrado = buscarPorId(id);

        if(funcionarioRepository.findByCargo(cargoEncontrado).isEmpty()) {
            cargoRepository.delete(cargoEncontrado);
        } else {
            throw new CargoPossuiFuncionariosException(id);
        }
    }


}
