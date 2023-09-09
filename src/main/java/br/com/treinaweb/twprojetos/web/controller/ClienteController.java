package br.com.treinaweb.twprojetos.web.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.web.exceptions.ClientePossuiProjetosException;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.services.ClienteService;
import br.com.treinaweb.twprojetos.validators.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteValidator clienteValidator;
    @Autowired
    private ClienteService clienteService;

    @InitBinder("cliente")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(clienteValidator);
    }

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cliente/home");
        modelAndView.addObject("clientes", clienteService.buscarTodos());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/detalhes");
        modelAndView.addObject("cliente", clienteService.buscarPorId(id));

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

        clienteService.cadastrar(cliente);
        attr.addFlashAttribute("alert",new AlertDTO("Cliente cadastrado com sucesso!", "alert-warning"));
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("cliente/formulario");
        Cliente clienteId = clienteService.buscarPorId(id);
        modelAndView.addObject("cliente", clienteId);

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@Valid Cliente cliente, BindingResult resultado, RedirectAttributes attr){

        if(resultado.hasErrors()) {
            return "cliente/formulario";
        }

        clienteService.atualizar(cliente, cliente.getId());
        attr.addFlashAttribute("alert",new AlertDTO("Cliente editado com sucesso!", "alert-warning"));
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        try{
            clienteService.excluirPorId(id);
            attr.addFlashAttribute("alert",new AlertDTO("Cliente excluído com sucesso!", "alert-success"));
        }catch (ClientePossuiProjetosException e) {
            attr.addFlashAttribute("alert",new AlertDTO("Cliente não pode ser excluído, pois possui projeto(s) relacionados!", "alert-danger"));
        }


        return "redirect:/clientes";
    }
}
