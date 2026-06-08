package bookstoread;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... newBooks) {
        books.addAll(Arrays.asList(newBooks));
    }

    // Tri par ordre naturel (par titre)
    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    // Tri selon un critère personnalisé
    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream()
                .sorted(criteria)
                .collect(Collectors.toList());
    }

    // Regroupement par année de publication
    public Map<Year, List<Book>> groupByPublicationYear() {
        return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    // Regroupement générique selon un critère quelconque
    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books.stream().collect(Collectors.groupingBy(fx));
    }
}
