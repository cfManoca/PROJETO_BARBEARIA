package ifba.cabaleleiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ifba.cabaleleiro.service.AgendamentoService;
import ifba.cabaleleiro.service.BarbeiroService;
import ifba.cabaleleiro.service.ClienteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("qtdAgendamentosHoje", agendamentoService.contarAgendamentosHoje());
        model.addAttribute("qtdClientes", clienteService.buscarTodos().size());
        model.addAttribute("qtdBarbeiros", barbeiroService.buscarTodos().size());
        model.addAttribute("proximosAgendamentos", agendamentoService.buscarProximosHoje(5));
        return "index";
    }
}