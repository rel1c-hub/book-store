package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.model.Order;
import mate.academy.bookstore.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems.book")
    List<Order> findAllByUser(Pageable pageable, User user);
}
