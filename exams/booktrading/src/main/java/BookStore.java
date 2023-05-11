/*
    полета:
        String name - име на книжарницата.
        Collection<Pair<Book, Integer>> books - книги налични в магазина.
        Collection<Pair<Other, Integer>> others – други артикули налични в магазина.
    методи:
        addBook(Pair<Book, Integer> book) - добавя книга в книжарницата, ако вече има такава книга, то тогава се увеличава нейното количество.
        addOther(Pair<Other, Integer> other) - добавя артикул в книжарницата, ако вече има такъв артикул, то тогава се увеличава неговото количество.
        getBookStorePrice() - връща общата стойност (цена * количество) на всички предмети (books и others), които са налични в магазина.
        getCheapestItem() - връща най-евтиния предмет в книжарницата.
        Map<String, List<Book>> getBooksByAuthor() - връща всички книги, групирани по автор.
        getBook(String isbn) - връща книга с посочения isbn, ако не съществува такава книга, да се хвърли грешка.
        getOther(String barcode) - връща артикул с посочения баркод, ако не съществува такъв, да се хвърли грешка.
*/

import java.util.*;
import java.util.stream.Stream;

import Products.*;

public class BookStore {
    String name;
    Collection<Pair<Book, Integer>> books;
    Collection<Pair<Other, Integer>> others;

    public BookStore(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.others = new ArrayList<>();
    }

    // use stream api
    public void addBook(Pair<Book, Integer> book) {
        if (books.stream().anyMatch(b -> b.getFirst().getIsbn().equals(book.getFirst().getIsbn()))) {
            books.stream().filter(b -> b.getFirst().getIsbn().equals(book.getFirst().getIsbn())).findFirst().get().addToSecond(book.getSecond());
        } else {
            books.add(book);
        }
    }

    // use stream api
    public void addOther(Pair<Other, Integer> other) {
        if (others.stream().anyMatch(o -> o.getFirst().getBarcode().equals(other.getFirst().getBarcode()))) {
            others.stream().filter(o -> o.getFirst().getBarcode().equals(other.getFirst().getBarcode())).findFirst().get().addToSecond(other.getSecond());
        } else {
            others.add(other);
        }
    }

    public double getBookStorePrice() {
        // based one, but doesnt work
//        Stream productStream = Stream.of(books.stream().map(b -> b.getFirst().getPrice() * b.getSecond()), others.stream().map(o -> o.getFirst().getPrice() * o.getSecond()));
//        return (double) productStream.reduce(0.0, (a, b) -> (double) a + (double) b);

            return books.stream().mapToDouble(pair -> pair.getFirst().getPrice() * pair.getSecond()).sum() +
                    others.stream().mapToDouble(pair -> pair.getFirst().getPrice() * pair.getSecond()).sum();
    }

    public <T extends Product> T getCheapestItem() {
        return (T) Stream.of(books.stream().map(b -> b.getFirst()), others.stream().map(o -> o.getFirst()))
                .reduce(Stream.empty(), Stream::concat)
                .min(Comparator.comparingDouble(Product::getPrice))
                .get();
    }

    public Map<String, List<Book>> getBooksByAuthor() {
        Map<String, List<Book>> booksByAuthor = new HashMap<>();

        books.forEach(b -> {
            if (booksByAuthor.containsKey(b.getFirst().getAuthor())) {
                booksByAuthor.get(b.getFirst().getAuthor()).add(b.getFirst());
            } else {
                List<Book> books = new ArrayList<>();
                books.add(b.getFirst());
                booksByAuthor.put(b.getFirst().getAuthor(), books);
            }
        });

        return booksByAuthor;
    }

    public Book getBook(String isbn) {
        return books.stream().filter(b -> b.getFirst().getIsbn().equals(isbn)).findFirst().get().getFirst();
    }

    public Other getOther(String barcode) {
        return others.stream().filter(o -> o.getFirst().getBarcode().equals(barcode)).findFirst().get().getFirst();
    }
}
