package com.lab4.library.report;

import com.lab4.library.TestCreationFactory;
import com.lab4.library.book.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static com.lab4.library.report.ReportType.CSV;
import static com.lab4.library.report.ReportType.PDF;

@SpringBootTest
class ReportServiceFactoryTest {

    @Autowired
    private ReportServiceFactory reportServiceFactory;

    @Test
    void export() throws IOException {
        List<Book> books = TestCreationFactory.listOf( Book.class);
        ReportService csvReportService = reportServiceFactory.getReportService(CSV);
        Assertions.assertEquals("CSV report created", csvReportService.export(books));

        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
        Assertions.assertEquals("PDF report created", pdfReportService.export(books));
    }
}