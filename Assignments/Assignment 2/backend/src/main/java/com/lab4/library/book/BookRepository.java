package com.lab4.library.book;

import com.lab4.library.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAllByTitleContainingAndAuthorContainingAndGenreContaining(String title, String author,String genre);
    @Query("SELECT book from Book book where book.quantity = 0")
    List<Book> getBooksOutOfStock();
}
