/*
    Имплементирайте клас Pair, който има 2 два елемента. Първия може да бъде Book или Other, а втория е цяло число, което е по-голямо или равно на 0. Двойката представлява артикул в книжарницата и неговото количество.
    Има 2 метода:
    getFirst() - връща артикула(Book или Other)
    getSecond() - връща количеството.
*/

import Products.*;

public class Pair<T extends Product, N extends Integer> {
    private T first;
    private N second;

    public Pair(T first, N second) {
        this.first = first;

        if (second.intValue() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public N getSecond() {
        return second;
    }

    public void addToSecond(N second) {
        if (this.second.intValue() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        this.second = (N) Integer.valueOf(this.second.intValue() + second.intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        return first.equals(pair.first);
    }

    @Override
    public int hashCode() {
        return first.hashCode();
    }
}
