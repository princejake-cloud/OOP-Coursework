/**
 * LibraryDemo.java
 * Driver class. Demonstrates all Library operations including a correctly
 * rejected duplicate-loan attempt.
 *
 * Run this file to produce screenshot-ready console output.
 */
public class LibraryDemo {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("    COMMUNITY LIBRARY MANAGEMENT SYSTEM     ");
        System.out.println("============================================\n");

        // ── 1. Create the library ────────────────────────────────────────
        Library library = new Library("Victoria Community Library");

        // ── 2. Add three books ───────────────────────────────────────────
        System.out.println(">>> Adding Books...");
        Book b1 = new Book("978-0-13-110362-7", "The C Programming Language", "Kernighan & Ritchie");
        Book b2 = new Book("978-0-13-468599-1", "Clean Code", "Robert C. Martin");
        Book b3 = new Book("978-0-20-163361-5", "The Pragmatic Programmer");   // uses constructor 1

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // ── 3. Register two members ──────────────────────────────────────
        System.out.println("\n>>> Registering Members...");
        Member m1 = new Member("M001", "Alice Nakato");
        Member m2 = new Member("M002", "Brian Ssemanda");

        library.registerMember(m1);
        library.registerMember(m2);

        // ── 4. Print initial state (BEFORE operations) ───────────────────
        System.out.println("\n>>> LIBRARY STATE BEFORE LOANS:");
        library.printState();

        // ── 5. Perform lend operations ───────────────────────────────────
        System.out.println(">>> Performing Loan Operations...\n");

        // Alice borrows "Clean Code"
        library.lendBook("M001", "978-0-13-468599-1");

        // Brian borrows "The Pragmatic Programmer"
        library.lendBook("M002", "978-0-20-163361-5");

        // Brian tries to borrow "Clean Code" -- should be REJECTED
        System.out.println("\n>>> Attempting to lend an already-loaned book...");
        library.lendBook("M002", "978-0-13-468599-1");

        // ── 6. Search for a book ─────────────────────────────────────────
        System.out.println(">>> Searching for books with keyword \"code\":");
        library.searchByTitle("code");

        // ── 7. Return a book ─────────────────────────────────────────────
        System.out.println(">>> Alice returns \"Clean Code\":");
        library.returnBook("978-0-13-468599-1");

        // ── 8. Lend the returned book to Brian (should now succeed) ──────
        System.out.println("\n>>> Now Brian borrows the returned book:");
        library.lendBook("M002", "978-0-13-468599-1");

        // ── 9. Print final state (AFTER operations) ──────────────────────
        System.out.println("\n>>> LIBRARY STATE AFTER LOANS:");
        library.printState();

        // ── 10. OOP Design Notes (printed for screenshot) ────────────────
        System.out.println("============================================");
        System.out.println("          OOP DESIGN NOTES                  ");
        System.out.println("============================================");
        System.out.println("Association   : Loan links Member and Book (neither owns the other).");
        System.out.println("Aggregation   : Library aggregates Books and Members. They can exist");
        System.out.println("                independently of the Library.");
        System.out.println("Composition   : Library composes Loans. A Loan has no meaning outside");
        System.out.println("                the Library that created it.");
        System.out.println("Multiplicity  : 1..* means one-to-many. Example: one Member can hold");
        System.out.println("                one or more Loans at the same time.");
        System.out.println("============================================\n");
    }
}
