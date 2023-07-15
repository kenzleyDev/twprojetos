package br.com.treinaweb.twprojetos.controller;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
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
    CargoRepository cargoRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cargo/home");

        modelAndView.addObject("cargos", cargoRepository.findAll());

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

        cargoRepository.save(cargo);
        attr.addFlashAttribute("alert",new AlertDTO("Cargo cadastrado com sucesso!", "alert-warning"));
        return "redirect:/cargos";
    }

    @PostMapping("/{id}/editar")
    public String editar(Cargo cargo, RedirectAttributes attr){
        cargoRepository.save(cargo);
        attr.addFlashAttribute("alert",new AlertDTO("Cargo editado com sucesso!", "alert-success"));
        return "redirect:/cargos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("cargo/formulario");
        Optional<Cargo> cargoId = cargoRepository.findById(id);
        modelAndView.addObject("cargo", cargoId.get());

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cargoRepository.deleteById(id);
        return "redirect:/cargos";
    }
}
