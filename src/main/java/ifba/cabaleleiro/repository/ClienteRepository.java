package ifba.cabaleleiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.cabaleleiro.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    
}
