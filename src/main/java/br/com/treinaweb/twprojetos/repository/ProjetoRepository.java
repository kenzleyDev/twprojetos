package br.com.treinaweb.twprojetos.repository;

import br.com.treinaweb.twprojetos.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
