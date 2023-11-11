package ukikiepas.dzisiajpowtorzylem.security.user.mappers;

import org.mapstruct.Mapper;
import ukikiepas.dzisiajpowtorzylem.security.user.models.User;
import ukikiepas.dzisiajpowtorzylem.security.user.models.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToDto(User user);

}
