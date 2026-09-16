package ifba.cabaleleiro.mapper;

import ifba.cabaleleiro.dto.AgendamentoDTO;
import ifba.cabaleleiro.entity.AgendamentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    AgendamentoEntity toEntity(AgendamentoDTO dto);
    AgendamentoDTO toDto(AgendamentoEntity entity);
}