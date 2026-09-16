package ifba.cabaleleiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ifba.cabaleleiro.dto.BarbeiroDTO;
import ifba.cabaleleiro.exception.AppCabeleleiroException;
import ifba.cabaleleiro.service.BarbeiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/barbeiros")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @GetMapping
    public String listar(@RequestParam(value = "novo", required = false) Boolean novo,
                          @RequestParam(value = "editar", required = false) Long editar,
                          Model model) {
        if (Boolean.TRUE.equals(novo)) {
            model.addAttribute("formBarbeiro", new BarbeiroDTO());
        } else if (editar != null) {
            model.addAttribute("formBarbeiro", barbeiroService.buscarBarbeiro(editar));
        }
        model.addAttribute("barbeiros", barbeiroService.buscarTodos());
        return "barbeiros";
    }

    @PostMapping
    public String salvar(@Valid BarbeiroDTO barbeiro, BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("erro", "Verifique os dados informados.");
            model.addAttribute("formBarbeiro", barbeiro);
            model.addAttribute("barbeiros", barbeiroService.buscarTodos());
            return "barbeiros";
        }
        barbeiroService.criarBarbeiro(barbeiro);
        redirectAttributes.addFlashAttribute("mensagem", "Barbeiro cadastrado com sucesso.");
        return "redirect:/barbeiros";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @Valid BarbeiroDTO barbeiro, BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes) {
        barbeiro.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("erro", "Verifique os dados informados.");
            model.addAttribute("formBarbeiro", barbeiro);
            model.addAttribute("barbeiros", barbeiroService.buscarTodos());
            return "barbeiros";
        }
        barbeiroService.atualizarBarbeiro(barbeiro);
        redirectAttributes.addFlashAttribute("mensagem", "Barbeiro atualizado com sucesso.");
        return "redirect:/barbeiros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            barbeiroService.apagarBarbeiro(id);
            redirectAttributes.addFlashAttribute("mensagem", "Barbeiro excluído com sucesso.");
        } catch (AppCabeleleiroException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/barbeiros";
    }
}