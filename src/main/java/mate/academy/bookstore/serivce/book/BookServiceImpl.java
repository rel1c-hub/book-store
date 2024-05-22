package mate.academy.bookstore.serivce.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        if (book.getCategories().contains(null)) {
            throw new IllegalArgumentException("Category is null");
        }
        return bookMapper.toDto(bookRepository.save(book));
    }

    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find book by id: " + id));
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Can't update book id: " + id);
        }
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Can't delete book by id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
}
