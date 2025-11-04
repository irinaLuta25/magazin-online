package model;

import model.enums.Genre;

public class BookProduct extends AProduct{
    private String author;
    private int pages;
    private int year;
    private Genre genre;

    public BookProduct(String name, double price, String brand, int stock, String author, int pages, int year, Genre genre) {
        super(name, price, brand, stock);
        this.author = author;
        this.pages = pages;
        this.year = year;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BookProduct{");
        sb.append("author='").append(author).append('\'');
        sb.append(", pages=").append(pages);
        sb.append(", year=").append(year);
        sb.append(", genre=").append(genre);
        sb.append('}');
        return sb.toString();
    }
}
