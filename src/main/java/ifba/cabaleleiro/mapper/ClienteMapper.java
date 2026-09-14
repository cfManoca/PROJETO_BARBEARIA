package ifba.cabaleleiro.mapper;
import ifba.cabaleleiro.dto.ClienteDTO;
import ifba.cabaleleiro.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    
    ClienteEntity toEntity(ClienteDTO dto);
    ClienteDTO toDto(ClienteEntity entity);
}
