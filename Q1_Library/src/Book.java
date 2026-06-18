import java.util.ArrayList;

/**
 * Book.java
 * Represents a single book in the library collection.
 *
 * DESIGN CHOICE: Two overloaded constructors are provided.
 * Constructor 1 accepts ISBN + title only (author defaults to "Unknown").
 * Constructor 2 accepts ISBN + title + author (the full version).
 * All fields are private. Getters and setters provide controlled access.
 */
public class Book {

    // ── Private fields (encapsulation) ───────────────────────────────────
    private String  isbn;
    private String  title;
    private String  author;
    private boolean available;  // true = on shelf, false = on loan

    // ── Constructor 1: ISBN + title only ────────────────────────────────
    public Book(String isbn, String title) {
        this.isbn      = isbn;
        this.title     = title;
        this.author    = "Unknown";
        this.available = true;   // every new book starts as available
    }

    // ── Constructor 2 (overload): ISBN + title + author ─────────────────
    public Book(String isbn, String title, String author) {
        this.isbn      = isbn;
        this.title     = title;
        this.author    = author;
        this.available = true;
    }

    // ── Getters ──────────────────────────────────────────────────────────
    public String  getIsbn()      { return isbn; }
    public String  getTitle()     { return title; }
    public String  getAuthor()    { return author; }
    public boolean isAvailable()  { return available; }

    // ── Setters ──────────────────────────────────────────────────────────
    public void setIsbn(String isbn)            { this.isbn      = isbn; }
    public void setTitle(String title)          { this.title     = title; }
    public void setAuthor(String author)        { this.author    = author; }
    public void setAvailable(boolean available) { this.available = available; }

    // ── toString override ────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Book[ISBN=" + isbn
             + ", Title=\"" + title + "\""
             + ", Author=" + author
             + ", Available=" + (available ? "Yes" : "On Loan")
             + "]";
    }
}
