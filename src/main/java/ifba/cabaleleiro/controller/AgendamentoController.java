package ifba.cabaleleiro.controller;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ifba.cabaleleiro.constants.AgendamentoConstantes;
import ifba.cabaleleiro.dto.AgendamentoDTO;
import ifba.cabaleleiro.exception.AppCabeleleiroException;
import ifba.cabaleleiro.service.AgendamentoService;
import ifba.cabaleleiro.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;

    @GetMapping
    public String tela(Model model) {
        model.addAttribute("clientes", clienteService.buscarTodos());
        model.addAttribute("horariosDisponiveis", AgendamentoConstantes.HORARIOS_DISPONIVEIS);
        model.addAttribute("agendamentosDoDia", agendamentoService.buscarViewsPorData(LocalDate.now()));
        return "agendamento";
    }

    @PostMapping
    public String salvar(@Valid AgendamentoDTO agendamento, BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            carregarTela(model);
            model.addAttribute("erro", "Verifique os dados informados.");
            return "agendamento";
        }
        try {
            agendamentoService.criarAgendamento(agendamento);
            redirectAttributes.addFlashAttribute("mensagem", "Agendamento realizado com sucesso.");
            return "redirect:/agendamento";
        } catch (AppCabeleleiroException e) {
            carregarTela(model);
            model.addAttribute("erro", e.getMessage());
            return "agendamento";
        }
    }

    @PostMapping("/{id}")
    public String cancelar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            agendamentoService.cancelarAgendamento(id);
            redirectAttributes.addFlashAttribute("mensagem", "Agendamento cancelado.");
        } catch (AppCabeleleiroException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/agendamento";
    }

    private void carregarTela(Model model) {
        model.addAttribute("clientes", clienteService.buscarTodos());
        model.addAttribute("horariosDisponiveis", AgendamentoConstantes.HORARIOS_DISPONIVEIS);
        model.addAttribute("agendamentosDoDia", agendamentoService.buscarViewsPorData(LocalDate.now()));
    }
}