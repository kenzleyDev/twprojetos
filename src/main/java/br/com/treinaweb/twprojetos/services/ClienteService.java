package br.com.treinaweb.twprojetos.services;

import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.exceptions.ClienteNaoEncontradoException;
import br.com.treinaweb.twprojetos.exceptions.ClientePossuiProjetosException;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente, Long id) {
        buscarPorId(id);
        return clienteRepository.save(cliente);
    }

    public void excluirPorId(Long id) {
        Cliente clienteEncontrado = buscarPorId(id);

        if(projetoRepository.findByCliente(clienteEncontrado).isEmpty()) {
            clienteRepository.delete(clienteEncontrado);
        } else {
            throw new ClientePossuiProjetosException(id);
        }
    }
}
