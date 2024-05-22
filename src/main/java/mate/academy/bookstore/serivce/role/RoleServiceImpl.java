package mate.academy.bookstore.serivce.role;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.Role.RoleName;
import mate.academy.bookstore.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByName(RoleName name) {
        return roleRepository.findByName(name)
             .orElseThrow(() -> new RuntimeException("cannot fetch role with name" + name));
    }
}
