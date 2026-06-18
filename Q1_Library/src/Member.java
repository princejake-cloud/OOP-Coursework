import java.util.ArrayList;

/**
 * Member.java
 * Represents a library member who can borrow books.
 *
 * DESIGN CHOICE: The loans list is an ArrayList because a member can hold
 * a variable number of active loans. The list stays private so that only
 * the Library class (via package-level methods addLoan/removeLoan) can
 * change it. External callers get a defensive copy through getLoans().
 */
public class Member {

    // ── Private fields ───────────────────────────────────────────────────
    private String          memberId;
    private String          name;
    private ArrayList<Loan> loans;    // active loans held by this member

    // ── Constructor ──────────────────────────────────────────────────────
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name     = name;
        this.loans    = new ArrayList<>();
    }

    // ── Getters ──────────────────────────────────────────────────────────
    public String getMemberId() { return memberId; }
    public String getName()     { return name; }

    /**
     * Returns a defensive copy so external code cannot modify the list
     * directly.  All mutations must go through Library.
     */
    public ArrayList<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    // ── Setters ──────────────────────────────────────────────────────────
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setName(String name)         { this.name     = name; }

    // ── Package-level helpers used only by Library ───────────────────────
    /** Adds a loan. Called by Library.lendBook(). */
    void addLoan(Loan loan)    { loans.add(loan); }

    /** Removes a loan. Called by Library.returnBook(). */
    void removeLoan(Loan loan) { loans.remove(loan); }

    // ── toString override ────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Member[ID=" + memberId
             + ", Name=" + name
             + ", ActiveLoans=" + loans.size()
             + "]";
    }
}
