package ifba.cabaleleiro.mapper;
import ifba.cabaleleiro.dto.AgendamentoDTO;
import ifba.cabaleleiro.entity.AgendamentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {
    
    AgendamentoEntity toDEntity(AgendamentoDTO dto);
    AgendamentoDTO tDto(AgendamentoEntity entity);
}