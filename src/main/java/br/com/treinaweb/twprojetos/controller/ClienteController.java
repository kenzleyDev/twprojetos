package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.validators.ClienteValidator;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @InitBinder("cliente")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ClienteValidator(clienteRepository));
    }

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cliente/home");
        modelAndView.addObject("clientes", clienteRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/detalhes");
        modelAndView.addObject("cliente", clienteRepository.getOne(id));

        return modelAndView;

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {

        ModelAndView modelAndView = new ModelAndView("cliente/formulario");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String salvar(@Valid Cliente cliente, BindingResult resultado, RedirectAttributes attr){

        if(resultado.hasErrors()) {
            return "cliente/formulario";
        }

        clienteRepository.save(cliente);
        attr.addFlashAttribute("alert",new AlertDTO("Cliente cadastrado com sucesso!", "alert-warning"));
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("cliente/formulario");
        Optional<Cliente> clienteId = clienteRepository.findById(id);
        modelAndView.addObject("cliente", clienteId.get());

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@Valid Cliente cliente, BindingResult resultado, RedirectAttributes attr){

        if(resultado.hasErrors()) {
            return "cliente/formulario";
        }

        clienteRepository.save(cliente);
        attr.addFlashAttribute("alert",new AlertDTO("Cliente editado com sucesso!", "alert-warning"));
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        clienteRepository.deleteById(id);
        attr.addFlashAttribute("alert",new AlertDTO("Cliente excluído com sucesso!", "alert-success"));
        return "redirect:/clientes";
    }
}
