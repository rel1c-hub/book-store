package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.serivce.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Get all categories",
            description = "Get a list of all available categories")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID",
            description = "Get a category by ID, if there is one")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}/books")
    @Operation(summary = "Get books by category ID",
            description = "Get books by category ID, if there is one")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return categoryService.getBooksByCategoryId(id, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new category",
            description = "Create a new category with generated ID")
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CreateCategoryRequestDto requestDto
    ) {
        return categoryService.save(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update a category by ID",
            description = "Get and update a category by ID, if there is one")
    public CategoryResponseDto updateCategoryById(
            @PathVariable Long id,
            @RequestBody @Valid CreateCategoryRequestDto requestDto
    ) {
        return categoryService.updateById(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a category by ID",
            description = "Delete a category by ID, if there is one")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
