package bookstoread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<String> books = new ArrayList<>();

    public List<String> books() {
        return Collections.unmodifiableList(books);
    }

    public void add() {
        // ne rien faire
    }

    public void add(String... newBooks) {
        for (String book : newBooks) {
            books.add(book);
        }
    }

    public List<String> arrange() {
        return books.stream().sorted().collect(Collectors.toList());
    }



}