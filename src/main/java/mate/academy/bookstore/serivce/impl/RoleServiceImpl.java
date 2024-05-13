package mate.academy.bookstore.serivce.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.Role.RoleName;
import mate.academy.bookstore.repository.RoleRepository;
import mate.academy.bookstore.serivce.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Set<Role> getAllByName(RoleName name) {
        return roleRepository.getAllByName(name);
    }
}
