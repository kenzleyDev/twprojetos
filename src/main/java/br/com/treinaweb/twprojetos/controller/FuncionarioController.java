package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.utils.SenhaUtils;
import br.com.treinaweb.twprojetos.validators.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @InitBinder("funcionario")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new FuncionarioValidator(funcionarioRepository));
    }

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
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Funcionario funcionario, BindingResult resultado, ModelMap modelMap, RedirectAttributes attr){

        if(resultado.hasErrors()) {
            modelMap.addAttribute("cargos", cargoRepository.findAll());
            return "funcionario/formulario";
        }

        String senhaEncriptada = SenhaUtils.encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncriptada);
        funcionarioRepository.save(funcionario);
        attr.addFlashAttribute("alert",new AlertDTO("Funcionario cadastrado com sucesso!", "alert-warning"));
        return "redirect:/funcionarios";
    }

    @PostMapping("{id}/editar")
    public String editar(@Valid Funcionario funcionario, BindingResult resultado, @PathVariable Long id, ModelMap modelMap, RedirectAttributes attr) {

        if(resultado.hasErrors()) {
            modelMap.addAttribute("cargos", cargoRepository.findAll());
            return "funcionario/formulario";
        }

        String senhaAtual = funcionarioRepository.getOne(id).getSenha();
        funcionario.setSenha(senhaAtual);

        funcionarioRepository.save(funcionario);
        attr.addFlashAttribute("alert",new AlertDTO("Funcionario editado com sucesso!", "alert-warning"));
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        Optional<Funcionario> funcionarioId = funcionarioRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
        modelAndView.addObject("funcionario", funcionarioId.get());
        modelAndView.addObject("cargos", cargoRepository.findAll());


        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        funcionarioRepository.deleteById(id);
        attr.addFlashAttribute("alert",new AlertDTO("Funcionario excluído com sucesso!", "alert-success"));
        return "redirect:/funcionarios";
    }
}
