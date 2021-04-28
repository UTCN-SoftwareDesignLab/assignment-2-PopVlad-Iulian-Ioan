package com.lab4.library.report;

import com.lab4.library.book.model.Book;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ReportService {
    String export(List<Book> books) throws IOException;

    ReportType getType();
}
