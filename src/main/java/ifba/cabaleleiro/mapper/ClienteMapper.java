package ifba.cabaleleiro.mapper;

import org.mapstruct.Mapper;


import ifba.cabaleleiro.dto.ClienteDTO;
import ifba.cabaleleiro.entity.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    
    ClienteEntity toEntity(ClienteDTO dto);

    
    ClienteDTO toDto(ClienteEntity entity);

}
