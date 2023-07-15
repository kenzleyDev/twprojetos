package br.com.treinaweb.twprojetos.validators;

import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class ClienteValidator implements Validator {

    private ClienteRepository clienteRepository;

    public ClienteValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Cliente.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Cliente cliente = (Cliente) o;

        Optional<Cliente> clienteEncontrado = clienteRepository.findByEmail(cliente.getEmail());
        if(clienteEncontrado.isPresent() && !clienteEncontrado.get().equals(cliente)) {
            errors.rejectValue("email", "validacao.cliente.email.existente");
        }

        clienteEncontrado = clienteRepository.findByEmail(cliente.getCpf());
        if(clienteEncontrado.isPresent() && !clienteEncontrado.get().equals(cliente)) {
            errors.rejectValue("cpf", "validacao.cliente.cpf.existente");
        }
    }
}
