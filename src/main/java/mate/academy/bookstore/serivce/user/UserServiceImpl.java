package mate.academy.bookstore.serivce.user;

import static mate.academy.bookstore.model.Role.RoleName.USER;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.UserRepository;
import mate.academy.bookstore.serivce.role.RoleService;
import mate.academy.bookstore.serivce.shoppingcart.ShoppingCartService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("User with email " + requestDto.getEmail()
                    + " already exists");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setRoles(Collections.singleton(roleService.getByName(USER)));
        User savedUser = userRepository.save(user);
        shoppingCartService.createShoppingCartForUser(user);
        return userMapper.toDto(userRepository.save(savedUser));
    }
}
