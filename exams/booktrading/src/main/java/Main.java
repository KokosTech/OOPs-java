import Products.Book;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // test bookstore
        BookStore bookstore = new BookStore("bookstore");

        // add books
        bookstore.addBook(new Pair<Book, Integer>(new Book("1234567890123", "J. R. R. Tolkien", "The Lord of the Rings", "Desc", 20.0), 10));
        bookstore.addBook(new Pair<Book, Integer>(new Book("1234567890124", "J. R. R. Tolkien", "The Lord of the Rings LGBTQ Edition", "Desc", 20.0), 10));
        bookstore.addBook(new Pair<Book, Integer>(new Book("1234567890123", "J. R. R. Tolkien", "The Lord of the Rings", "Desc", 20.0), 10));

        bookstore.addBook(new Pair<Book, Integer>(new Book("1231234567890", "11A", "The Sex of Gundi", "Desc", 999.9), 10));

        System.out.println(bookstore.getBookStorePrice());
        System.out.println(bookstore.getCheapestItem());

        Map<String, List<Book>> booksByAuthor = bookstore.getBooksByAuthor();
        booksByAuthor.forEach((author, books) -> {
            System.out.println(author);
            books.forEach(System.out::println);
        });

        System.out.println("Get book: " + bookstore.getBook("1234567890123"));
    }
}