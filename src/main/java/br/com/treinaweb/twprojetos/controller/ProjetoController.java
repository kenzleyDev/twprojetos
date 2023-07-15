package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepositorio;

    @Autowired
    private FuncionarioRepository funcionarioRepositorio;

    @Autowired
    private ClienteRepository clienteRepositorio;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("projeto/home");

        modelAndView.addObject("projetos", projetoRepositorio.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projeto/detalhes");

        modelAndView.addObject("projeto", projetoRepositorio.getOne(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("projeto/formulario");

        modelAndView.addObject("projeto", new Projeto());
        modelAndView.addObject("clientes", clienteRepositorio.findAll());
        modelAndView.addObject("lideres", funcionarioRepositorio.findByCargoNome("Gerente"));
        modelAndView.addObject("funcionarios", funcionarioRepositorio.findByCargoNomeNot("Gerente"));

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projeto/formulario");

        modelAndView.addObject("projeto", projetoRepositorio.getOne(id));
        modelAndView.addObject("clientes", clienteRepositorio.findAll());
        modelAndView.addObject("lideres", funcionarioRepositorio.findByCargoNome("Gerente"));
        modelAndView.addObject("funcionarios", funcionarioRepositorio.findByCargoNomeNot("Gerente"));

        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(@Valid Projeto projeto, BindingResult resultado, ModelMap modelMap, RedirectAttributes attr) {

        if(resultado.hasErrors()) {
            modelMap.addAttribute("clientes", clienteRepositorio.findAll());
            modelMap.addAttribute("lideres", funcionarioRepositorio.findByCargoNome("Gerente"));
            modelMap.addAttribute("funcionarios", funcionarioRepositorio.findByCargoNomeNot("Gerente"));
            return "projeto/formulario";
        }

        projetoRepositorio.save(projeto);
        if(projeto.getId() == null) {
            attr.addFlashAttribute("alert",new AlertDTO("Projeto cadastrado com sucesso!", "alert-warning"));
        } else {
            attr.addFlashAttribute("alert",new AlertDTO("Projeto editado com sucesso!", "alert-success"));
        }

        return "redirect:/projetos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        projetoRepositorio.deleteById(id);

        attr.addFlashAttribute("alert",new AlertDTO("Projeto excluído com sucesso!", "alert-success"));

        return "redirect:/projetos";
    }
}
