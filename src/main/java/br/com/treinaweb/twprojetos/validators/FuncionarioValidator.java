package br.com.treinaweb.twprojetos.validators;

import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class FuncionarioValidator implements Validator {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioValidator(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Funcionario.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Funcionario funcionario = (Funcionario) o;

        if(funcionario.getDataAdmissao() != null && funcionario.getDataDemissao() != null) {
            if(funcionario.getDataDemissao().isBefore(funcionario.getDataAdmissao())) {
                errors.rejectValue("dataDemissao", "validacao.funcionario.dataAdmissao.posterior.dataDemissao");
            }
        }

        Optional<Funcionario> funcionarioEncontrado = funcionarioRepository.findByEmail(funcionario.getEmail());

        if(funcionarioEncontrado.isPresent() && !funcionarioEncontrado.get().equals(funcionario)) {
            errors.rejectValue("email", "validacao.funcionario.email.existente");
        }

        funcionarioEncontrado = funcionarioRepository.findByCpf(funcionario.getCpf());

        if(funcionarioEncontrado.isPresent() && !funcionarioEncontrado.get().equals(funcionario)) {
            errors.rejectValue("cpf", "validacao.funcionario.cpf.existente");
        }

    }
}
