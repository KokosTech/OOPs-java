import Products.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {
    BookStore bookStore;
    @BeforeEach
    void setUp() {
        bookStore = new BookStore("BookStore");
    }

    @Test
    void addBook() {
        bookStore.addBook(new Pair<>(new Book("1234567890123", "title", "author", "desc", 10.0), 1));
        bookStore.addBook(new Pair<>(new Book("1234567890123", "title", "author", "desc", 10.0), 1));

        assertEquals(20.0, bookStore.getBookStorePrice());
    }

    @Test
    void addOther() {
    }

    @Test
    void getBookStorePrice() {
    }

    @Test
    void getCheapestItem() {
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void getBook() {
    }

    @org.junit.jupiter.api.Test
    void getOther() {
    }
}