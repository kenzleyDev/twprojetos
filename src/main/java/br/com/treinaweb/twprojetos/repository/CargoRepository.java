package br.com.treinaweb.twprojetos.repository;

import br.com.treinaweb.twprojetos.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
