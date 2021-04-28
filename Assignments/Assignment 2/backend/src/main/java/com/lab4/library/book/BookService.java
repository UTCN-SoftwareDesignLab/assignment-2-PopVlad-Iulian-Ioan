package com.lab4.library.book;

import com.lab4.library.book.model.Book;
import com.lab4.library.book.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException ("Item not found: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map( bookMapper::toDto)
                .collect( Collectors.toList());
    }

    public BookDTO create(BookDTO book) {
        return bookMapper.toDto( bookRepository.save(
                bookMapper.fromDto(book)
        ));
    }

    public BookDTO edit(Long id, BookDTO book) {
        Book actBook = findById(id);
        actBook.setTitle (book.getTitle ());
        actBook.setAuthor (book.getAuthor ());
        actBook.setGenre ( book.getGenre () );
        actBook.setPrice ( book.getPrice () );
        actBook.setQuantity ( book.getQuantity () );
        return bookMapper.toDto(
                bookRepository.save(actBook)
        );
    }

    public List<BookDTO> search(BookDTO bookDTO) {
        List<Book> books = bookRepository.findAllByTitleContainingAndAuthorContainingAndGenreContaining ( bookDTO.getTitle (),bookDTO.getAuthor (),bookDTO.getGenre () );
        List<BookDTO>bookDTOS=new ArrayList<> ();
        for (Book book:books
             ) {
            bookDTOS.add ( bookMapper.toDto ( book ) );
        }
        return bookDTOS;

    }
    public List<Book> booksOutOfStock(){
        return bookRepository.getBooksOutOfStock();
    }

    public BookDTO get(Long id) {
        return bookMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}

