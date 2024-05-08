package mate.academy.bookstore.mapper;

import mate.academy.bookstore.dto.UserDto;
import mate.academy.bookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UserDto userDto);
}
