package ifba.cabaleleiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ifba.cabaleleiro.dto.ClienteDTO;
import ifba.cabaleleiro.exception.AppCabeleleiroException;
import ifba.cabaleleiro.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String listar(@RequestParam(value = "novo", required = false) Boolean novo,
                          @RequestParam(value = "editar", required = false) Long editar,
                          Model model) {
        if (Boolean.TRUE.equals(novo)) {
            model.addAttribute("formCliente", new ClienteDTO());
        } else if (editar != null) {
            model.addAttribute("formCliente", clienteService.buscarCliente(editar));
        }
        model.addAttribute("clientes", clienteService.buscarTodos());
        return "clientes";
    }

    @PostMapping
    public String salvar(@Valid ClienteDTO cliente, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Verifique os dados informados.");
            return "redirect:/clientes";
        }
        try {
            clienteService.criarCliente(cliente);
            redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso.");
        } catch (AppCabeleleiroException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/clientes";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @Valid ClienteDTO cliente, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        cliente.setId(id);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Verifique os dados informados.");
            return "redirect:/clientes";
        }
        try {
            clienteService.atualizarCliente(cliente);
            redirectAttributes.addFlashAttribute("mensagem", "Cliente atualizado com sucesso.");
        } catch (AppCabeleleiroException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clienteService.apagarCliente(id);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso.");
        return "redirect:/clientes";
    }
}