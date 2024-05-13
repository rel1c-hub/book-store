package mate.academy.bookstore.serivce;

import java.util.Set;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.Role.RoleName;

public interface RoleService {
    Set<Role> getAllByName(RoleName name);
}
