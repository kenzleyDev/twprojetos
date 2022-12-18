package br.com.treinaweb.twprojetos.repository;

import br.com.treinaweb.twprojetos.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
