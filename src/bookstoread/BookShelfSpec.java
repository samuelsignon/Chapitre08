package bookstoread;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BookShelfSpec {

    @Test
    public void shelfEmptyWhenNoBookAdded() {
        BookShelf shelf = new BookShelf();

        List<String> books = shelf.books();

        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        BookShelf shelf = new BookShelf();

        shelf.add();

        List<String> books = shelf.books();

        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    public void booksReturnedFromBookShelfIsImmutableForClient() {
        BookShelf shelf = new BookShelf();

        shelf.add("Effective Java", "Code Complete");

        List<String> books = shelf.books();

        try {
            books.add("The Mythical Man-Month");
            fail("Should not be able to add book to books");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
}