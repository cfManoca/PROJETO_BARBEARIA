package ifba.cabaleleiro.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import ifba.cabaleleiro.constants.MensagemErro;
import ifba.cabaleleiro.constants.StatusAgendamento;
import ifba.cabaleleiro.dto.AgendamentoDTO;
import ifba.cabaleleiro.dto.AgendamentoViewDTO;
import ifba.cabaleleiro.entity.AgendamentoEntity;
import ifba.cabaleleiro.exception.AppCabeleleiroException;
import ifba.cabaleleiro.mapper.AgendamentoMapper;
import ifba.cabaleleiro.repository.AgendamentoRepository;
import ifba.cabaleleiro.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final AgendamentoMapper mapper;

    public void criarAgendamento(AgendamentoDTO dto) {
        clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new AppCabeleleiroException(
                MensagemErro.ERRO_CLIENTE_NAO_ENCONTRADO.formatted(dto.getClienteId())));

        boolean conflito = repository.existsByBarbeiroIdAndDataAndHorarioAndStatusNot(
            dto.getBarbeiroId(), dto.getData(), dto.getHorario(), StatusAgendamento.CANCELADO);

        if (conflito) {
            throw new AppCabeleleiroException(MensagemErro.ERRO_HORARIO_INDISPONIVEL);
        }

        repository.save(mapper.toEntity(dto));
    }

    public void cancelarAgendamento(Long id) {
        AgendamentoEntity entity = repository.findById(id)
            .orElseThrow(() -> new AppCabeleleiroException(
                MensagemErro.ERRO_AGENDAMENTO_NAO_ENCONTRADO.formatted(id)));

        entity.setStatus(StatusAgendamento.CANCELADO);
        repository.save(entity);
    }

    public List<AgendamentoViewDTO> buscarViewsPorData(LocalDate data) {
        return repository.findByDataOrderByHorarioAsc(data)
            .stream()
            .map(this::paraView)
            .toList();
    }

    public List<AgendamentoViewDTO> buscarProximosHoje(int limite) {
        return buscarViewsPorData(LocalDate.now())
            .stream()
            .filter(view -> !StatusAgendamento.CANCELADO.equals(view.getStatus()))
            .limit(limite)
            .toList();
    }

    public long contarAgendamentosHoje() {
    return repository.findByDataOrderByHorarioAsc(LocalDate.now())
            .stream()
            .filter(a -> !StatusAgendamento.CANCELADO.equals(a.getStatus()))
            .count();
    }

    private AgendamentoViewDTO paraView(AgendamentoEntity entity) {
        String nomeCliente = clienteRepository.findById(entity.getClienteId())
            .map(c -> c.getNome())
            .orElse("Cliente removido");

        return new AgendamentoViewDTO(
            entity.getId(), nomeCliente, entity.getServico(),
            entity.getHorario(), entity.getStatus(),
            StatusAgendamento.paraCss(entity.getStatus())
        );
    }
}