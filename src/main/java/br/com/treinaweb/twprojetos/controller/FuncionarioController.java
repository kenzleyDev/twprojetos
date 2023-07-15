package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.exceptions.FuncionarioEhLiderDeProjetoException;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.services.CargoService;
import br.com.treinaweb.twprojetos.services.FuncionarioService;
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

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @InitBinder("funcionario")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new FuncionarioValidator(funcionarioRepository));
    }

    @GetMapping
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("funcionario/home");
        modelAndView.addObject("funcionarios", funcionarioService.buscarTodos());
        return modelAndView;

    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("funcionario/detalhes");
        modelAndView.addObject("funcionario", funcionarioService.buscarPorId(id));
        return modelAndView;

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {

        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
        modelAndView.addObject("cargos", cargoService.buscarTodos());
        modelAndView.addObject("funcionario", new Funcionario());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Funcionario funcionario, BindingResult resultado, ModelMap modelMap, RedirectAttributes attr){

        if(resultado.hasErrors()) {
            modelMap.addAttribute("cargos", cargoService.buscarTodos());
            return "funcionario/formulario";
        }

        funcionarioService.cadastrar(funcionario);
        attr.addFlashAttribute("alert",new AlertDTO("Funcionario cadastrado com sucesso!", "alert-warning"));

        return "redirect:/funcionarios";
    }

    @PostMapping("{id}/editar")
    public String editar(@Valid Funcionario funcionario, BindingResult resultado, @PathVariable Long id, ModelMap modelMap, RedirectAttributes attr) {

        if(resultado.hasErrors()) {
            modelMap.addAttribute("cargos", cargoService.buscarTodos());
            return "funcionario/formulario";
        }

        funcionarioService.atualizar(funcionario, funcionario.getId());
        attr.addFlashAttribute("alert",new AlertDTO("Funcionario editado com sucesso!", "alert-warning"));
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        Funcionario funcionarioId = funcionarioService.buscarPorId(id);

        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
        modelAndView.addObject("funcionario", funcionarioId);
        modelAndView.addObject("cargos", cargoService.buscarTodos());


        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        try{
            funcionarioService.excluirPorId(id);
            attr.addFlashAttribute("alert",new AlertDTO("Funcionario excluído com sucesso!", "alert-success"));
        }catch (FuncionarioEhLiderDeProjetoException e) {
            attr.addFlashAttribute("alert",new AlertDTO("Funcionario não pode ser excluído, pois é lider de algum projeto!", "alert-danger"));
        }

        return "redirect:/funcionarios";
    }
}
