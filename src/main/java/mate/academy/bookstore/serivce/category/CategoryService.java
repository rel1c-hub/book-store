package mate.academy.bookstore.serivce.category;

import java.util.List;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id, Pageable pageable);

    CategoryResponseDto updateById(Long id, CreateCategoryRequestDto requestDto);

    void deleteById(Long id);
}
