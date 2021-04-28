package com.lab4.library.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private long id;
    private String title;
    private String author;
    private String genre;
    private double price;
    private long quantity;
}
