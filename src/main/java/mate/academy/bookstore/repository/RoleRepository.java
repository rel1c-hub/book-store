package mate.academy.bookstore.repository;

import java.util.Set;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> getAllByName(RoleName name);
}
