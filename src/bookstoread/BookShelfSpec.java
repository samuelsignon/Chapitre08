package bookstoread;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    @Test
    void shelfEmptyWhenNoBookAdded() {
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty());
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);

        List<Book> books = shelf.books();

        assertEquals(2, books.size());
    }

    @Test
    void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        shelf.add();

        List<Book> books = shelf.books();

        assertTrue(books.isEmpty());
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {
        shelf.add(effectiveJava, codeComplete);

        List<Book> books = shelf.books();

        assertThrows(UnsupportedOperationException.class, () -> {
            books.add(mythicalManMonth);
        });
    }

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

        List<Book> books = shelf.books();

        assertEquals(
                asList(effectiveJava, codeComplete, mythicalManMonth),
                books
        );
    }

    @Test
    void bookshelfArrangedByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
        assertEquals(asList(mythicalManMonth, effectiveJava, codeComplete), books, () -> "Books in a bookshelf are arranged in descending order of book title");
    }

}