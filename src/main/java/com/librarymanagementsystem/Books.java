package com.librarymanagementsystem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Books {
    // private String isbn;
    // private int sno;
    // private String title;
    // private String author;
    // private String authorGender;
    // private int publicationYear;
    // private String genre;
    // private double bookPrice;
    // private boolean bookAvailability;
    // private String bookCover;
    // private String condition;
    private StringProperty isbn;
    //private IntegerProperty sno;
    private StringProperty title;
    private StringProperty author;
    private StringProperty authorGender;
    private IntegerProperty publicationYear;
    private StringProperty genre;
    private DoubleProperty bookPrice;
    private BooleanProperty bookAvailability;
    private StringProperty bookCover;
    private StringProperty condition;


    public Books(String isbn, String title, String author, String authorGender, int publicationYear, String genre, double bookPrice, boolean bookAvailability, String bookCover, String condition) {

        // this.sno = sno;
        // // this.isbn = isbn;
        // this.title = title;
        // this.author = author;
        // this.authorGender = authorGender;
        // this.publicationYear = publicationYear;
        // this.genre = genre;
        // this.bookPrice = bookPrice;
        // this.bookAvailability = bookAvailability;
        // this.bookCover = bookCover;
        // this.condition = condition;
        this.isbn = new SimpleStringProperty(isbn);
        //this.sno = new SimpleIntegerProperty(sno);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.authorGender = new SimpleStringProperty(authorGender);
        this.publicationYear = new SimpleIntegerProperty(publicationYear);
        this.genre = new SimpleStringProperty(genre);
        this.bookPrice = new SimpleDoubleProperty(bookPrice);
        this.bookAvailability = new SimpleBooleanProperty(bookAvailability);
        this.bookCover = new SimpleStringProperty(bookCover);
        this.condition = new SimpleStringProperty(condition);
    }

    public Books( StringProperty isbn, StringProperty title, StringProperty author,StringProperty authorGender,IntegerProperty publicationYear, StringProperty genre,  DoubleProperty bookPrice, BooleanProperty bookAvailability, StringProperty bookCover, StringProperty condition) {
        this.author = author;
        this.authorGender = authorGender;
        this.bookAvailability = bookAvailability;
        this.bookCover = bookCover;
        this.bookPrice = bookPrice;
        this.condition = condition;
        this.genre = genre;
        this.publicationYear = publicationYear;
       // this.sno = sno;
       this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    // getters and setters... for fields

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getAuthorGender() {
        return authorGender.get();
    }

    public void setAuthorGender(String authorGender) {
        this.authorGender.set(authorGender);
    }

    public int getPublicationYear() {
        return publicationYear.get();
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear.set(publicationYear);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public double getBookPrice() {
        return bookPrice.get();
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice.set(bookPrice);
    }

    public boolean isBookAvailability() {
        return bookAvailability.get();
    }

    public void setBookAvailability(boolean bookAvailability) {
        this.bookAvailability.set(bookAvailability);
    }

    public String getBookCover() {
        return bookCover.get();
    }

    public void setBookCover(String bookCover) {
        this.bookCover.set(bookCover);
    }

    public String getCondition() {
        return condition.get();
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    // public int getSno() {
    //     return sno.get();
    // }  
    // public void setSno(int sno) {
    //     this.sno.set(sno);
    // }


       // getters and setters... for properties

    // public IntegerProperty snoProperty() {
    //     return sno;
    // }
    public StringProperty isbnProperty() {
        return isbn;
    }
    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty authorProperty() {
        return author;
    }
    public StringProperty genreProperty() {
        return genre;
    }
    public DoubleProperty bookPriceProperty() {
        return bookPrice;
    }
    public BooleanProperty bookAvailabilityProperty() {
        return bookAvailability;
    }
    public StringProperty conditionProperty() {
        return condition;
    }

    
}
    