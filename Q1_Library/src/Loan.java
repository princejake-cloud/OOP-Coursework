import java.time.LocalDate;

/**
 * Loan.java
 * Connects exactly one Member to exactly one Book.
 * Records the borrow date and a due date 14 days later.
 *
 * DESIGN CHOICE: Loan holds references to Member and Book objects (not
 * copies), so the Library can navigate from any Loan back to the actual
 * Member and Book. The active flag is set to false when the book is
 * returned, giving the Library a history-friendly record.
 */
public class Loan {

    // ── Private fields ───────────────────────────────────────────────────
    private Member    member;
    private Book      book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean   active;     // false once the book is returned

    // ── Constructor ──────────────────────────────────────────────────────
    /**
     * Creates a new active loan with today as the borrow date and
     * a due date 14 days from now.
     *
     * @param member  the member borrowing the book
     * @param book    the book being borrowed
     */
    public Loan(Member member, Book book) {
        this.member     = member;
        this.book       = book;
        this.borrowDate = LocalDate.now();
        this.dueDate    = borrowDate.plusDays(14);
        this.active     = true;
    }

    // ── Getters ──────────────────────────────────────────────────────────
    public Member    getMember()     { return member; }
    public Book      getBook()       { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate()    { return dueDate; }
    public boolean   isActive()      { return active; }

    // ── Close the loan (called when book is returned) ────────────────────
    public void closeLoan() { this.active = false; }

    // ── toString override ────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Loan[Member=" + member.getName()
             + ", Book=\"" + book.getTitle() + "\""
             + ", BorrowDate=" + borrowDate
             + ", DueDate=" + dueDate
             + ", Status=" + (active ? "Active" : "Returned")
             + "]";
    }
}
