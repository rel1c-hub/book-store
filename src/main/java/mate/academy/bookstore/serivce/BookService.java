package mate.academy.bookstore.serivce;

import java.util.List;
import mate.academy.bookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> finadAll();
}
