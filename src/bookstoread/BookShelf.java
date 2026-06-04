package bookstoread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add() {
        // ne rien faire
    }

    public void add(Book... newBooks) {
        for (Book book : newBooks) {
            books.add(book);
        }
    }

    public List<Book> arrange() {
        return books.stream().sorted().collect(Collectors.toList());
    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }
}