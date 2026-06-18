import java.util.ArrayList;

/**
 * Library.java
 * The central class that manages the collection of books and members.
 *
 * DESIGN CHOICE:
 *   Library AGGREGATES Books and Members (they exist independently of the
 *   Library and can be passed in from outside). The relationship is
 *   aggregation because destroying the Library does not destroy the
 *   Book or Member objects.
 *
 *   Library COMPOSES Loans (a Loan is created and owned entirely by the
 *   Library; it has no meaning outside it). This is composition.
 *
 * Business rule enforced here:
 *   A book may have at most ONE active loan at any time.
 *   Attempting to lend an already-loaned book prints a rejection message.
 */
public class Library {

    // ── Private fields ───────────────────────────────────────────────────
    private String          name;
    private ArrayList<Book>   books;
    private ArrayList<Member> members;
    private ArrayList<Loan>   loans;     // all loans (active + returned)

    // ── Constructor ──────────────────────────────────────────────────────
    public Library(String name) {
        this.name    = name;
        this.books   = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans   = new ArrayList<>();
    }

    // ── Add a book to the collection ─────────────────────────────────────
    public void addBook(Book book) {
        books.add(book);
        System.out.println("[Library] Book added: " + book.getTitle());
    }

    // ── Register a member ────────────────────────────────────────────────
    public void registerMember(Member member) {
        members.add(member);
        System.out.println("[Library] Member registered: " + member.getName());
    }

    // ── Lend a book to a member ──────────────────────────────────────────
    /**
     * Enforces the "at most one active loan per book" rule.
     * If the book is already on loan, the request is rejected gracefully.
     *
     * @param memberId  ID of the member who wants to borrow
     * @param isbn      ISBN of the book to borrow
     */
    public void lendBook(String memberId, String isbn) {
        // 1. Find the member
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("[REJECTED] Member ID " + memberId + " not found.");
            return;
        }

        // 2. Find the book
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("[REJECTED] Book ISBN " + isbn + " not found.");
            return;
        }

        // 3. Enforce the business rule
        if (!book.isAvailable()) {
            System.out.println("[REJECTED] \"" + book.getTitle()
                + "\" is already on loan and cannot be lent again.");
            return;
        }

        // 4. Create the loan
        Loan loan = new Loan(member, book);
        loans.add(loan);
        member.addLoan(loan);
        book.setAvailable(false);

        System.out.println("[SUCCESS] \"" + book.getTitle()
            + "\" lent to " + member.getName()
            + ". Due: " + loan.getDueDate());
    }

    // ── Return a book ────────────────────────────────────────────────────
    /**
     * Finds the active loan for the given ISBN, closes it, and marks
     * the book as available again.
     *
     * @param isbn  ISBN of the book being returned
     */
    public void returnBook(String isbn) {
        // Search active loans for this book
        for (Loan loan : loans) {
            if (loan.getBook().getIsbn().equals(isbn) && loan.isActive()) {
                loan.closeLoan();
                loan.getBook().setAvailable(true);
                loan.getMember().removeLoan(loan);
                System.out.println("[SUCCESS] \"" + loan.getBook().getTitle()
                    + "\" returned by " + loan.getMember().getName() + ".");
                return;
            }
        }
        System.out.println("[INFO] No active loan found for ISBN " + isbn + ".");
    }

    // ── Search for a book by title (case-insensitive partial match) ──────
    public void searchByTitle(String keyword) {
        System.out.println("\n--- Search results for \"" + keyword + "\" ---");
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No books found matching \"" + keyword + "\".");
        }
        System.out.println("-------------------------------------------\n");
    }

    // ── Print full library state ─────────────────────────────────────────
    public void printState() {
        System.out.println("\n========== Library: " + name + " ==========");

        System.out.println("-- Books (" + books.size() + ") --");
        for (Book b : books) {
            System.out.println("  " + b);
        }

        System.out.println("-- Members (" + members.size() + ") --");
        for (Member m : members) {
            System.out.println("  " + m);
            for (Loan l : m.getLoans()) {
                System.out.println("      " + l);
            }
        }

        System.out.println("-- All Loans (" + loans.size() + ") --");
        for (Loan l : loans) {
            System.out.println("  " + l);
        }
        System.out.println("==========================================\n");
    }

    // ── Private helpers ──────────────────────────────────────────────────
    private Member findMemberById(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) return m;
        }
        return null;
    }

    private Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    // ── Getters ──────────────────────────────────────────────────────────
    public String getName() { return name; }

    // ── toString override ────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Library[Name=" + name
             + ", Books=" + books.size()
             + ", Members=" + members.size()
             + ", TotalLoans=" + loans.size()
             + "]";
    }
}
