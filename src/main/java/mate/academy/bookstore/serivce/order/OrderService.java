package mate.academy.bookstore.serivce.order;

import java.util.List;
import mate.academy.bookstore.dto.order.CreateOrderRequestDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.UpdateOrderRequestDto;
import mate.academy.bookstore.model.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto saveOrder(CreateOrderRequestDto requestDto, User user);

    List<OrderResponseDto> getAllOrders(Pageable pageable, User user);

    void updateOrder(UpdateOrderRequestDto requestDto, Long id);
}
