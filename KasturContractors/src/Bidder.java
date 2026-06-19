/**
 * Bidder.java
 * Represents a contractor who submits a bid for a sub-contract.
 *
 * OOP Design:
 *  - All fields are private (encapsulation).
 *  - The class is responsible only for holding bidder identity and bid amount.
 *  - Validity (zero or negative bids) is enforced when the object is created
 *    via the static factory method, keeping validation logic in one place.
 */
public class Bidder {

    private final String name;
    private final double bidAmount;
    private boolean isValid;     // false if bid <= 0

    // ── Constructor ───────────────────────────────────────────────────────────
    public Bidder(String name, double bidAmount) {
        this.name      = name;
        this.bidAmount = bidAmount;
        // A bid is valid only when it is strictly positive
        this.isValid   = bidAmount > 0;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String  getName()      { return name;      }
    public double  getBidAmount() { return bidAmount;  }
    public boolean isValid()      { return isValid;    }

    // ── Display helper ────────────────────────────────────────────────────────
    public void printBidDetails() {
        System.out.printf("  Bidder: %-20s  Bid: UGX %,.2f  [%s]%n",
                name,
                bidAmount,
                isValid ? "VALID" : "REJECTED (zero or negative)");
    }
}