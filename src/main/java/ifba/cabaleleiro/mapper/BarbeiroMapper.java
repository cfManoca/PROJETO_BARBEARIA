package ifba.cabaleleiro.mapper;
import ifba.cabaleleiro.dto.BarbeiroDTO;
import ifba.cabaleleiro.entity.BarbeiroEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  BarbeiroMapper {
    
    BarbeiroEntity toDEntity(BarbeiroDTO dto);
    BarbeiroDTO tDto(BarbeiroEntity entity);
}
