package mate.academy.bookstore.serivce.orderitem;

import java.util.List;
import java.util.Set;
import mate.academy.bookstore.dto.orderitem.OrderItemResponseDto;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.OrderItem;

public interface OrderItemService {
    Set<OrderItem> toOrderItem(Set<CartItem> cartItems);

    void saveOrderItems(Set<OrderItem> orderItem);

    List<OrderItemResponseDto> getAllOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);
}
