package com.lab4.library.report;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;
import com.lab4.library.book.model.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lab4.library.report.ReportType.PDF;

@Service
public class PdfReportService implements ReportService {
    @Override
    public String export(List<Book> books) throws IOException {
        try {
            PDDocument doc = new PDDocument ();
            PDPage page = new PDPage ();
            doc.addPage ( page );
            PDPageContentStream stream = new PDPageContentStream(doc,page);
            stream.setFont( PDType1Font.TIMES_ROMAN, 12);
            stream.beginText();
            stream.setLeading(14.5f);
            stream.newLineAtOffset(25, 700);
            stream.showText("Out of stock: ");
            stream.newLine();
            stream.newLine();

            for(Book book: books) {
                stream.showText("ID: " + book.getId());
                stream.newLine();
                stream.showText("Title: " + book.getTitle());
                stream.newLine();
                stream.showText("Author: " + book.getAuthor());
                stream.newLine();
                stream.showText("Genre: " + book.getGenre());
                stream.newLine();
                stream.showText("Price: " + book.getPrice());
                stream.newLine();
                stream.showText("***************************************");
                stream.newLine();
            }

            stream.endText();
            stream.close();

            doc.save ( "OutOfStockBooks.pdf" );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return "PDF report created";
    }


    @Override
    public ReportType getType() {
        return PDF;
    }
}
