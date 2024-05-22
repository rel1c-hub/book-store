package mate.academy.bookstore.serivce.shoppingcart;

import mate.academy.bookstore.dto.cartitem.CartItemResponseDto;
import mate.academy.bookstore.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.model.User;

public interface ShoppingCartService {
    ShoppingCartResponseDto getByUserId(Long userId);

    CartItemResponseDto addToCart(CreateCartItemRequestDto requestDto, Long userId);

    CartItemResponseDto updateCart(
            UpdateCartItemRequestDto requestDto, Long cartItemId, Long userId);

    void deleteCartItem(Long cartItemId, Long userId);

    void createShoppingCartForUser(User user);
}
