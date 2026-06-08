package bookstoread;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookShelfSpec {

    private BookShelf shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeEach
    void init() {
        shelf = new BookShelf();

        effectiveJava = new Book(
                "Effective Java",
                "Joshua Bloch",
                LocalDate.of(2008, Month.MAY, 8)
        );

        codeComplete = new Book(
                "Code Complete",
                "Steve McConnell",
                LocalDate.of(2004, Month.JUNE, 9)
        );

        mythicalManMonth = new Book(
                "The Mythical Man-Month",
                "Frederick Phillips Brooks",
                LocalDate.of(1975, Month.JANUARY, 1)
        );
    }

    // Fonctionnalité 1 : Ajouter des livres

    @Test
    void shelfEmptyWhenNoBookAdded() {
        assertTrue(shelf.books().isEmpty());
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);
        assertEquals(2, shelf.books().size());
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {
        shelf.add(effectiveJava, codeComplete);

        List<Book> books = shelf.books();

        assertThrows(
                UnsupportedOperationException.class,
                () -> books.add(mythicalManMonth)
        );
    }

    // Fonctionnalité 2 : Organiser les livres

    @Test
    void bookshelfArrangedByBookTitle() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        List<Book> books = shelf.arrange();

        assertEquals(
                asList(codeComplete, effectiveJava, mythicalManMonth),
                books
        );
    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        shelf.arrange();

        assertEquals(
                asList(effectiveJava, codeComplete, mythicalManMonth),
                shelf.books()
        );
    }

    @Test
    void bookshelfArrangedByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        Comparator<Book> reversed =
                Comparator.<Book>naturalOrder().reversed();

        List<Book> books = shelf.arrange(reversed);

        assertEquals(
                asList(mythicalManMonth, effectiveJava, codeComplete),
                books
        );
    }

    @Test
    void bookshelfArrangedByPublicationDate() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        List<Book> books = shelf.arrange(
                Comparator.comparing(Book::getPublishedOn)
        );

        assertEquals(
                asList(mythicalManMonth, codeComplete, effectiveJava),
                books
        );
    }

    // Fonctionnalité 3 : Grouper les livres

    @Test
    void groupBooksInsideBookShelfByPublicationYear() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        Map<Year, List<Book>> booksByYear =
                shelf.groupByPublicationYear();

        assertEquals(
                asList(effectiveJava),
                booksByYear.get(Year.of(2008))
        );

        assertEquals(
                asList(codeComplete),
                booksByYear.get(Year.of(2004))
        );

        assertEquals(
                asList(mythicalManMonth),
                booksByYear.get(Year.of(1975))
        );
    }

    @Test
    void groupBooksInsideBookShelfByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        Map<String, List<Book>> booksByAuthor =
                shelf.groupBy(Book::getAuthor);

        assertEquals(
                asList(effectiveJava),
                booksByAuthor.get("Joshua Bloch")
        );

        assertEquals(
                asList(codeComplete),
                booksByAuthor.get("Steve McConnell")
        );

        assertEquals(
                asList(mythicalManMonth),
                booksByAuthor.get("Frederick Phillips Brooks")
        );
    }
}