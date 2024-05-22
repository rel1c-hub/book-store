package mate.academy.bookstore.serivce.cartitem;

import mate.academy.bookstore.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;

public interface CartItemService {
    CartItem addToCart(CreateCartItemRequestDto requestDto, ShoppingCart shoppingCart);

    CartItem update(Long id, UpdateCartItemRequestDto requestDto);

    void delete(Long id);
}
