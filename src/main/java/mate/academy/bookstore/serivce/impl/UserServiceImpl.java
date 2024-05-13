package mate.academy.bookstore.serivce.impl;

import static mate.academy.bookstore.model.Role.RoleName.USER;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.UserRepository;
import mate.academy.bookstore.serivce.RoleService;
import mate.academy.bookstore.serivce.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("User with email " + requestDto.getEmail()
                    + " already exists");
        }
        User user = userMapper.toModel(requestDto);
        user.setEmail(user.getEmail());
        user.setRoles(roleService.getAllByName(USER));
        return userMapper.toDto(userRepository.save(user));
    }
}
