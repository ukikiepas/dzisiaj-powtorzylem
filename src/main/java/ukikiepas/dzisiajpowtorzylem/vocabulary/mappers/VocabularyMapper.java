package ukikiepas.dzisiajpowtorzylem.vocabulary.mappers;

import org.mapstruct.Mapper;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VocabularyMapper {

    VocabularyDto vocabularyToVocabularyDto(Vocabulary vocabulary);
    List<VocabularyDto> vocabulariesToVocabularyDtos(List<Vocabulary> vocabularies);

}