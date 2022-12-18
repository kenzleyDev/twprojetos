package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.UF;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("funcionario/home");
        modelAndView.addObject("funcionarios", funcionarioRepository.findAll());
        return modelAndView;

    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("funcionario/detalhes");
        modelAndView.addObject("funcionario", funcionarioRepository.getOne(id));
        return modelAndView;

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {

        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
        modelAndView.addObject("cargos", cargoRepository.findAll());
        modelAndView.addObject("funcionario", new Funcionario());
        modelAndView.addObject("ufs", UF.values());
        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        Optional<Funcionario> funcionarioId = funcionarioRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
        modelAndView.addObject("ufs", UF.values());
        modelAndView.addObject("funcionario", funcionarioId.get());
        modelAndView.addObject("cargos", cargoRepository.findAll());


        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/funcionarios";
    }
}
