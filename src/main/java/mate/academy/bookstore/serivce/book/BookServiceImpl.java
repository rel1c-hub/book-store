package mate.academy.bookstore.serivce.book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.repository.CategoryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        Set<Category> getCategories = book.getCategories();
        Set<Long> categoryIds = new HashSet<>();
        List<Category> categoryList = categoryRepository.findAllById(categoryIds);
        for (Category category : getCategories) {
            categoryIds.add(category.getId());
        }
        if (categoryIds.size() != categoryList.size()) {
            Set<Long> newCategoryIds = getCategories.stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            categoryIds.removeAll(newCategoryIds);
            throw new EntityNotFoundException("Invalid category ids: " + categoryIds);
        }
        book.setCategories(new HashSet<>(categoryList));
        return bookMapper.toDto(bookRepository.save(book));
    }

    public List<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find book by id: " + id));
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoryId(id, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
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
