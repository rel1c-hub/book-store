package mate.academy.bookstore.dto.cartitem;

import java.util.Set;

public class CartItemResponseDto {
    private Long id;
    private Long userId;
    private Set<CartItemResponseDto> cartItems;
}
