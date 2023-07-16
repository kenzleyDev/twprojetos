package br.com.treinaweb.twprojetos.config;

import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.validators.ClienteValidator;
import br.com.treinaweb.twprojetos.validators.FuncionarioValidator;
import br.com.treinaweb.twprojetos.validators.PessoaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Bean
    public ClienteValidator clienteValidator() {
        return new ClienteValidator(clienteRepository);
    }

    @Bean
    public FuncionarioValidator funcionarioValidator() {
        return new FuncionarioValidator(funcionarioRepository);
    }

    @Bean
    public PessoaValidator pessoaValidator() {
        return new PessoaValidator();
    }

}
