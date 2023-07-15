package br.com.treinaweb.twprojetos.repository;

import br.com.treinaweb.twprojetos.entities.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @EntityGraph(attributePaths = "endereco")
    List<Cliente> findAll();

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByCpf(String cpf);
}
