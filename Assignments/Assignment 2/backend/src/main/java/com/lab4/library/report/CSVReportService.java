package com.lab4.library.report;

import com.lab4.library.book.BookRepository;
import com.lab4.library.book.BookService;
import com.lab4.library.book.model.Book;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static com.lab4.library.report.ReportType.CSV;

@Service
public class CSVReportService implements ReportService {
    @Override
    public String export(List<Book> books) throws FileNotFoundException {

        File csvOutputFile = new File("OutOfStockBooks.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("\tOUT OF STOCK");
            pw.println("\tID\tTITLE\tAUTHOR\tGENRE\tPRICE");
            books.stream()
                    .map(CSVReportService::createRow )
                    .forEach(pw::println);
            pw.close();
            pw.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        return "CSV report created";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }

    public static String createRow(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t"+book.getId());
        sb.append("\t"+book.getTitle());
        sb.append("\t"+book.getAuthor());
        sb.append("\t"+book.getGenre());
        sb.append("\t"+book.getPrice());

        return sb.toString();
    }
}
