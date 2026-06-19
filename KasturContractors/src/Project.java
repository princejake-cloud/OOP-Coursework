import java.util.ArrayList;

/**
 * Project.java
 * Represents an awarded sub-contract project that Kastur Contractors Ltd monitors.
 *
 * OOP Design:
 *  - Encapsulates all financial tracking: payments received and expenses incurred.
 *  - Provides methods to add payments/expenses and to compute profit/loss.
 *  - The class owns its own behaviour (Single Responsibility Principle).
 */
public class Project {

    // ── Project identity fields ───────────────────────────────────────────────
    private final String tenderReference;
    private final double contractValue;     // the agreed contract (= winning bid)
    private final Bidder winningBidder;

    // ── Financial tracking ────────────────────────────────────────────────────
    private final ArrayList<Double> payments;   // all client deposits received
    private final ArrayList<Double> expenses;   // materials, labour, transport …

    // ── Constructor ───────────────────────────────────────────────────────────
    public Project(String tenderReference, double contractValue, Bidder winningBidder) {
        this.tenderReference = tenderReference;
        this.contractValue   = contractValue;
        this.winningBidder   = winningBidder;
        this.payments        = new ArrayList<>();
        this.expenses        = new ArrayList<>();
    }

    // ── Mutators ──────────────────────────────────────────────────────────────
    public void addPayment(double amount) {
        if (amount > 0) {
            payments.add(amount);
        } else {
            System.out.println("  WARNING: Payment must be positive. Entry ignored.");
        }
    }

    public void addExpense(double amount) {
        if (amount > 0) {
            expenses.add(amount);
        } else {
            System.out.println("  WARNING: Expense must be positive. Entry ignored.");
        }
    }

    // ── Financial computations ────────────────────────────────────────────────
    public double getTotalPayments() {
        double total = 0;
        for (double p : payments) total += p;
        return total;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (double e : expenses) total += e;
        return total;
    }

    /**
     * Profit or Loss = total payments received - total expenses incurred.
     * A positive result is profit; negative is a loss.
     */
    public double getProfitOrLoss() {
        return getTotalPayments() - getTotalExpenses();
    }

    public double getOutstandingBalance() {
        return contractValue - getTotalPayments();
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String getTenderReference() { return tenderReference; }
    public double getContractValue()   { return contractValue;   }
    public Bidder getWinningBidder()   { return winningBidder;   }

    // ── Summary display ───────────────────────────────────────────────────────
    public void printProjectReport() {
        double profitLoss = getProfitOrLoss();

        System.out.println();
        System.out.println("============================================");
        System.out.println("        PROJECT FINANCIAL REPORT");
        System.out.println("============================================");
        System.out.printf("  Tender Reference   : %s%n",          tenderReference);
        System.out.printf("  Winning Bidder     : %s%n",          winningBidder.getName());
        System.out.printf("  Contract Value     : UGX %,.2f%n",   contractValue);
        System.out.printf("  Total Payments     : UGX %,.2f%n",   getTotalPayments());
        System.out.printf("  Outstanding Balance: UGX %,.2f%n",   getOutstandingBalance());
        System.out.printf("  Total Expenses     : UGX %,.2f%n",   getTotalExpenses());
        System.out.println("--------------------------------------------");
        if (profitLoss >= 0) {
            System.out.printf("  PROFIT             : UGX %,.2f%n", profitLoss);
        } else {
            System.out.printf("  LOSS               : UGX %,.2f%n", Math.abs(profitLoss));
        }
        System.out.println("============================================");
    }
}