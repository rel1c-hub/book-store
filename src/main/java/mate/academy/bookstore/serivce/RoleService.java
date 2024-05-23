package mate.academy.bookstore.serivce;

import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.Role.RoleName;

public interface RoleService {
    Role getByName(RoleName name);
}
