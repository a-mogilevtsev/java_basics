package ru.skillbox;

/**
 * Created by a.sosnina on 10/14/2021.
 */
public class Book {
    private final String name;
    private final String author;
    private final int pagesCount;
    private final int isbn;

    public Book(String name, String author, int pagesCount, int isbn) {
        this.name = name;
        this.author = author;
        this.pagesCount = pagesCount;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getIsbn() {
        return isbn;
    }
}
