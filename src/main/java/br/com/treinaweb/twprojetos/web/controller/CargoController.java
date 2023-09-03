package br.com.treinaweb.twprojetos.web.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.exceptions.CargoPossuiFuncionariosException;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
import br.com.treinaweb.twprojetos.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cargo/home");

        modelAndView.addObject("cargos", cargoService.buscarTodos());

        return modelAndView;
    }
    @GetMapping({"/cadastrar"})
    public ModelAndView cadastrar() {

        ModelAndView modelAndView = new ModelAndView("cargo/formulario");
        modelAndView.addObject("cargo", new Cargo());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String salvar(@Valid Cargo cargo, BindingResult resultado, RedirectAttributes attr){

        if(resultado.hasErrors()) {
            return "cargo/formulario";
        }

        cargoService.cadastrar(cargo);
        attr.addFlashAttribute("alert",new AlertDTO("Cargo cadastrado com sucesso!", "alert-warning"));
        return "redirect:/cargos";
    }

    @PostMapping("/{id}/editar")
    public String editar(Cargo cargo, RedirectAttributes attr){
        cargoService.atualizar(cargo, cargo.getId());
        attr.addFlashAttribute("alert",new AlertDTO("Cargo editado com sucesso!", "alert-success"));
        return "redirect:/cargos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("cargo/formulario");
        Cargo cargoId = cargoService.buscarPorId(id);
        modelAndView.addObject("cargo", cargoId);

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        try {
            cargoService.excluirPorId(id);
            attrs.addFlashAttribute("alert", new AlertDTO("Cargo excluído com sucesso", "alert-success"));
        } catch (CargoPossuiFuncionariosException e) {
            attrs.addFlashAttribute("alert", new AlertDTO("Cargo não pode ser excluído, pois possui funcionario(s) relacionados", "alert-danger"));
        }

        return "redirect:/cargos";
    }
}
