package br.com.treinaweb.twprojetos.repository;

import br.com.treinaweb.twprojetos.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
