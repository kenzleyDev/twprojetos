package br.com.treinaweb.twprojetos.services;

import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.exceptions.FuncionarioEhLiderDeProjetoException;
import br.com.treinaweb.twprojetos.exceptions.FuncionarioNaoEncontradoException;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.repository.ProjetoRepository;
import br.com.treinaweb.twprojetos.utils.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    public List<Funcionario> buscarLideres() {
        return funcionarioRepository.findByCargoNome("Gerente");
    }

    public List<Funcionario> buscarEquipe() {
        return funcionarioRepository.findByCargoNomeNot("Gerente");
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
    }

    public Funcionario cadastrar(Funcionario funcionario) {

        String senhaEncriptada = SenhaUtils.encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncriptada);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario, Long id) {
        Funcionario funcionarioEncontrado = buscarPorId(id);
        funcionario.setSenha(funcionarioEncontrado.getSenha());

        return funcionarioRepository.save(funcionario);
    }

    public void excluirPorId(Long id) {
        Funcionario funcionarioEncontrado = buscarPorId(id);

        if(projetoRepository.findByLider(funcionarioEncontrado).isEmpty()) {
            if(!funcionarioEncontrado.getProjetos().isEmpty()) {
                for (Projeto projeto : funcionarioEncontrado.getProjetos()) {
                    projeto.getEquipe().remove(funcionarioEncontrado);
                    projetoRepository.save(projeto);
                }
            }
            funcionarioRepository.delete(funcionarioEncontrado);
        } else {
            throw new FuncionarioEhLiderDeProjetoException(id);
        }
    }
}
