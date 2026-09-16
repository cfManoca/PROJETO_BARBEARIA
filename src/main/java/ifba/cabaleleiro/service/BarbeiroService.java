package ifba.cabaleleiro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ifba.cabaleleiro.dto.BarbeiroDTO;
import ifba.cabaleleiro.mapper.BarbeiroMapper;
import ifba.cabaleleiro.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository repository;
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
        try {
            repository.deleteById(id);
        } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
            // já foi excluído antes, então ignora
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