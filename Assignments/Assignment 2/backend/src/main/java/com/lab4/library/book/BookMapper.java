package com.lab4.library.book;

import com.lab4.library.book.model.Book;
import com.lab4.library.book.model.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
        BookDTO toDto(Book book);

        Book fromDto(BookDTO bookDTO);
    }
