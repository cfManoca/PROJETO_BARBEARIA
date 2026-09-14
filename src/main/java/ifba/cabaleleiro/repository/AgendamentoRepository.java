package ifba.cabaleleiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.cabaleleiro.entity.AgendamentoEntity;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    
}
