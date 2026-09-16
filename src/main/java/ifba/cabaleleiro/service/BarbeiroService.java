package ifba.cabaleleiro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ifba.cabaleleiro.constants.MensagemErro;
import ifba.cabaleleiro.constants.StatusAgendamento;
import ifba.cabaleleiro.dto.BarbeiroDTO;
import ifba.cabaleleiro.exception.AppCabeleleiroException;
import ifba.cabaleleiro.mapper.BarbeiroMapper;
import ifba.cabaleleiro.repository.AgendamentoRepository;
import ifba.cabaleleiro.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository repository;
    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroMapper mapper;

    public void criarBarbeiro(BarbeiroDTO dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void atualizarBarbeiro(BarbeiroDTO dto) {
        if (repository.existsById(dto.getId())) {
            repository.save(mapper.toEntity(dto));
        }
    }

    public void apagarBarbeiro(Long id) {
        if (repository.existsById(id)) {
            boolean possuiAgendamento = agendamentoRepository
                .existsByBarbeiroIdAndStatusNot(id, StatusAgendamento.CANCELADO);

            if (possuiAgendamento) {
                throw new AppCabeleleiroException(MensagemErro.ERRO_BARBEIRO_POSSUI_AGENDAMENTO);
            }

            try {
                repository.deleteById(id);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
                // já foi excluído por outra requisição (ex.: duplo clique) — ignora
            }
        }
    }

    public BarbeiroDTO buscarBarbeiro(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public List<BarbeiroDTO> buscarTodos() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}