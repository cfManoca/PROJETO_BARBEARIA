package ifba.cabaleleiro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ifba.cabaleleiro.constants.MensagemErro;
import ifba.cabaleleiro.constants.StatusAgendamento;
import ifba.cabaleleiro.dto.ClienteDTO;
import ifba.cabaleleiro.exception.AppCabeleleiroException;
import ifba.cabaleleiro.mapper.ClienteMapper;
import ifba.cabaleleiro.repository.AgendamentoRepository;
import ifba.cabaleleiro.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final AgendamentoRepository agendamentoRepository;
    private final ClienteMapper mapper;

    public void criarCliente(ClienteDTO dto) {
        validarEmailDuplicado(dto);
        repository.save(mapper.toEntity(dto));
    }

    public void atualizarCliente(ClienteDTO dto) {
        if (repository.existsById(dto.getId())) {
            validarEmailDuplicado(dto);
            repository.save(mapper.toEntity(dto));
        }
    }

    public void apagarCliente(Long id) {
        if (repository.existsById(id)) {
            boolean possuiAgendamento = agendamentoRepository
                .existsByClienteIdAndStatusNot(id, StatusAgendamento.CANCELADO);

            if (possuiAgendamento) {
                throw new AppCabeleleiroException(MensagemErro.ERRO_CLIENTE_POSSUI_AGENDAMENTO);
            }

            try {
                repository.deleteById(id);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
                // já foi excluído por outra requisição (ex.: duplo clique) — ignora
            }
        }
    }

    public ClienteDTO buscarCliente(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public List<ClienteDTO> buscarTodos() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    private void validarEmailDuplicado(ClienteDTO dto) {
        repository.findByEmail(dto.getEmail()).ifPresent(existente -> {
            if (!existente.getId().equals(dto.getId())) {
                throw new AppCabeleleiroException(MensagemErro.ERRO_EMAIL_DUPLICADO);
            }
        });
    }
}