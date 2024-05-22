package mate.academy.bookstore.serivce.cartitem;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.CartItemMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.repository.CartItemRepository;
import mate.academy.bookstore.serivce.book.BookService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Override
    public CartItem addToCart(CreateCartItemRequestDto requestDto, ShoppingCart shoppingCart) {
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find a book with id " + requestDto.getBookId()));

        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem update(Long id, UpdateCartItemRequestDto requestDto) {
        CartItem cartItem = getCartItemById(id);
        cartItem.setQuantity(requestDto.quantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
