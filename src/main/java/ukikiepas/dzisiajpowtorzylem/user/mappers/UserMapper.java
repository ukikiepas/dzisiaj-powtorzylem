package ukikiepas.dzisiajpowtorzylem.user.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ukikiepas.dzisiajpowtorzylem.user.models.User;
import ukikiepas.dzisiajpowtorzylem.user.models.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToDto(User user);

    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "city", source = "city")
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "enabled",ignore = true)
    @Mapping(target = "nonLocked", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isPublicAccount", source = "isPublicAccount")
    @Mapping(target = "image", ignore = true)
    void updateUserFromDto (UserDto userDto, @MappingTarget User user);

}
