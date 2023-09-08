package br.com.treinaweb.twprojetos.api.mapper;

import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.ClienteService;
import br.com.treinaweb.twprojetos.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoMapper {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;

    public Projeto toEntity(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setOrcamento(projetoDTO.getOrcamento());
        projeto.setGastos(projetoDTO.getGastos());
        projeto.setCliente(clienteService.buscarPorId(projetoDTO.getClienteId()));
        projeto.setLider(funcionarioService.buscarPorId(projetoDTO.getLiderId()));

        return projeto;
    }
}
