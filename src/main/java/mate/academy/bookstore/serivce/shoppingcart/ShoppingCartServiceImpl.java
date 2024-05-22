package mate.academy.bookstore.serivce.shoppingcart;

import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cartitem.CartItemResponseDto;
import mate.academy.bookstore.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.CartItemMapper;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.ShoppingCartRepository;
import mate.academy.bookstore.serivce.cartitem.CartItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemService cartItemService;

    @Override
    public ShoppingCartResponseDto getByUserId(Long userId) {
        return shoppingCartMapper.toDto(getShoppingCartForUser(userId));
    }

    @Override
    public CartItemResponseDto addToCart(CreateCartItemRequestDto requestDto, Long userId) {
        ShoppingCart shoppingCart = getShoppingCartForUser(userId);
        CartItem cartItem = cartItemService.addToCart(requestDto, shoppingCart);
        shoppingCart.getCartItems().add(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public CartItemResponseDto updateCart(UpdateCartItemRequestDto requestDto,
                                          Long cartItemId, Long userId) {
        ShoppingCart shoppingCart = getShoppingCartForUser(userId);
        CartItem cartItem = cartItemService.update(cartItemId, requestDto);
        shoppingCartRepository.save(shoppingCart);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId, Long userId) {
        ShoppingCart shoppingCart = getShoppingCartForUser(userId);
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't delete item with id: " + cartItemId));
        cartItemService.delete(cartItem.getId());
    }

    @Override
    public void createShoppingCartForUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setCartItems(new HashSet<>());
        shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart getShoppingCartForUser(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cannot get shopping cart for user: " + userId));
    }
}
