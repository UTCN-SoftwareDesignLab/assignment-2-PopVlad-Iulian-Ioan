package com.lab4.library.book;

import com.lab4.library.book.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @BeforeEach
    public void beforeEach() {
        bookRepository.deleteAll();
    }
    @Test
    void findAllByTitleContainingAndAuthorContainingAndGenreContaining() {
        String title="The name of the wind";
        String author="Patrick Rothfuss";
        String genre="fantasy";
        Book book1 = Book.builder()
                .author(author)
                .genre(genre)
                .price(50.5)
                .title(title)
                .quantity(0)
                .id(1L).build();
        Book book2 = Book.builder()
                .author("Guy Gabriel Kay")
                .genre("fantasy")
                .price(45.5)
                .title("Under haven")
                .quantity(3)
                .id(2L).build();

        List<Book> books = new ArrayList<> ();
        books.add(book1); books.add(book2);
        bookRepository.saveAll(books);
        List<Book> outOfStockBooks = bookRepository.findAllByTitleContainingAndAuthorContainingAndGenreContaining (title,author,genre);

        Assertions.assertEquals(outOfStockBooks.size(),1);
    }

    @Test
    void getBooksOutOfStock() {
        String title="The name of the wind";
        String author="Patrick Rothfuss";
        String genre="fantasy";
        Book book1 = Book.builder()
                .author(author)
                .genre(genre)
                .price(50.5)
                .title(title)
                .quantity(0)
                .id(1L).build();
        Book book2 = Book.builder()
                .author("Guy Gabriel Kay")
                .genre("fantasy")
                .price(45.5)
                .title("Under haven")
                .quantity(3)
                .id(2L).build();

        List<Book> books = new ArrayList<> ();
        books.add(book1); books.add(book2);
        bookRepository.saveAll(books);
        List<Book> outOfStockBooks = bookRepository.getBooksOutOfStock();

        Assertions.assertEquals(outOfStockBooks.size(),1);
    }
}