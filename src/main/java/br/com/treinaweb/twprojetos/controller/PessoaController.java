package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.enums.UF;
import br.com.treinaweb.twprojetos.validators.PessoaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {FuncionarioController.class, ClienteController.class})
public class PessoaController {

    @Autowired
    private PessoaValidator pessoaValidator;

    @InitBinder(value = {"funcionario", "cliente"})
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(pessoaValidator);
    }

    @ModelAttribute("ufs")
    public UF[] getUfs() {
        return UF.values();
    }
}
