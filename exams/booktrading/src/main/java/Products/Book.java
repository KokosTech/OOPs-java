package Products;

/*
    String isbn - съдържа 13 или 10 цифри, задължително поле, може да започва с 0. Уникален идентификатор. Не може да се променя.
    String author - автор, незадължително поле.
    String name - име на книгата, незадължително поле.
    String description - описание на книгата, незадължително поле.
    double price - цена на книгата, задължително, по-голямо от 0.
 */
public class Book extends Product {
    final String isbn;
    String author;
    String name;
    String description;

    public Book(String isbn, Double price) {
        super(price);

        if (isbn.length() != 10 && isbn.length() != 13)
            throw new IllegalArgumentException("ISBN must be 10 or 13 digits long");

        if (!isbn.chars().allMatch(Character::isDigit))
            throw new IllegalArgumentException("ISBN must contain only digits");

        this.isbn = isbn;

        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        this.price = price;
    }

    public Book(String isbn, String author, String name, String description, Double price) {
        this(isbn, price);

        if (author.isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");
        this.author = author;

        if (name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;

        if (description.isEmpty())
            throw new IllegalArgumentException("Description cannot be empty");
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Book && ((Book) obj).isbn.equals(this.isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
