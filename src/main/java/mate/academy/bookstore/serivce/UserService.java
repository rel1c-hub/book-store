package mate.academy.bookstore.serivce;

import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.user.UserResponseDto;

public interface UserService {
    public UserResponseDto register(UserRegistrationRequestDto requestDto);
}
