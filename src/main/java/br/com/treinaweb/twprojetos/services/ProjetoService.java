package br.com.treinaweb.twprojetos.services;

import br.com.treinaweb.twprojetos.api.dto.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.mapper.ProjetoMapper;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.exceptions.ProjetoNaoEncontradoException;
import br.com.treinaweb.twprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoMapper projetoMapper;

    @Autowired
    private FuncionarioService funcionarioService;

    public Page<Projeto> buscarTodos(Pageable pagination) {
        return projetoRepository.findAll(pagination);
    }
    public List<Projeto> buscarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    public Projeto cadastrar(ProjetoDTO projetoDTO) {
        return projetoRepository.save(projetoMapper.toEntity(projetoDTO));
    }
    public Projeto cadastrar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }
    public Projeto atualizar(ProjetoDTO projetoDTO, Long id) {
        buscarPorId(id);
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        projeto.setId(id);
        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(Projeto projeto, Long id) {
        buscarPorId(id);
        return projetoRepository.save(projeto);
    }

    public void excluirPorId(Long id) {
        Projeto projetoencontrado = buscarPorId(id);
        projetoRepository.delete(projetoencontrado);
    }

    public List<Funcionario> atualizarEquipe(EquipeDTO equipeDTO, Long id) {
        Projeto projeto = buscarPorId(id);

        List<Funcionario> equipe = new ArrayList<>();

        equipeDTO.getEquipeId().forEach(funcionarioId -> {
            Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId);
            equipe.add(funcionario);
        });

        projeto.setEquipe(equipe);
        projetoRepository.save(projeto);
        return equipe;
    }
}
