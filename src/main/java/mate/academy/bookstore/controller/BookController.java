package mate.academy.bookstore.controller;

import java.util.List;

import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;

public interface BookController {
    public List<Book> getAll();
    public BookDto getBookById(Long id);
    public BookDto createBook(CreateBookRequestDto bookDto);
}
