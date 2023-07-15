package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.dto.AlterarSenhaDTO;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.utils.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UsuarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("usuario/perfil");

        Funcionario usuario = funcionarioRepository.findByEmail(principal.getName()).get();
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("alterarSenhaForm", new AlterarSenhaDTO());
        return modelAndView;
    }

    @PostMapping("/alterar-senha")
    public String alterarSenha(AlterarSenhaDTO form, Principal principal, RedirectAttributes attr) {
        Funcionario usuario = funcionarioRepository.findByEmail(principal.getName()).get();

        if(SenhaUtils.matches(form.getSenhaAtual(), usuario.getSenha())) {
            usuario.setSenha(SenhaUtils.encode(form.getNovaSenha()));
            funcionarioRepository.save(usuario);

            attr.addFlashAttribute("alert",new AlertDTO("Senha alterada com sucesso!", "alert-warning"));
        } else {
            attr.addFlashAttribute("alert",new AlertDTO("Senha atual incorreta!", "alert-danger"));
        }

        return "redirect:/perfil";
    }
}
