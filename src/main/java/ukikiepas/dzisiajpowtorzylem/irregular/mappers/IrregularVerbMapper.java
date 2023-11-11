package ukikiepas.dzisiajpowtorzylem.irregular.mappers;

import org.mapstruct.Mapper;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerb;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerbDto;

@Mapper(componentModel = "spring")
public interface IrregularVerbMapper {

    IrregularVerbDto verbToDto(IrregularVerb verb);

}
