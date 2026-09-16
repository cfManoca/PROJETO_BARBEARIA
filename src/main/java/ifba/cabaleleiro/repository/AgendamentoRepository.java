package ifba.cabaleleiro.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.cabaleleiro.entity.AgendamentoEntity;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    List<AgendamentoEntity> findByDataOrderByHorarioAsc(LocalDate data);

    boolean existsByBarbeiroIdAndDataAndHorarioAndStatusNot(
        Long barbeiroId, LocalDate data, LocalTime horario, String status);

}