import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * KasturContractors.java
 * Main program for Question Two.
 *
 * Covers all requirements:
 *  (a) Accepts bids from 4 bidders; selects LOWEST valid bid as winner.
 *  (b) Rejects zero / negative bids; ensures at least one valid bid exists.
 *  (c) Monitors payments received and expenses incurred on the awarded project.
 *  (d) Captures tender reference, contract value, and outstanding balance.
 *  (e) Computes and reports profit or loss = total payments - total expenses.
 *  (f) OOP: uses Bidder and Project classes with encapsulated fields.
 */
public class KasturContractors {

    private static final int TOTAL_BIDDERS = 4;
    private static Scanner   scanner       = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("    Kastur Contractors Ltd");
        System.out.println("    Sub-Contract Tender Management System");
        System.out.println("============================================");

        // ── Step 1: Collect bids ─────────────────────────────────────────────
        ArrayList<Bidder> bidders = collectBids();

        // ── Step 2: Identify the winning bidder (lowest valid bid) ────────────
        Bidder winner = selectWinner(bidders);
        if (winner == null) {
            System.out.println("  No valid bids were submitted. Cannot award contract.");
            scanner.close();
            return;
        }

        System.out.println();
        System.out.println("  All bids received:");
        for (Bidder b : bidders) {
            b.printBidDetails();
        }
        System.out.println();
        System.out.printf("  WINNER: %s with bid UGX %,.2f%n",
                winner.getName(), winner.getBidAmount());

        // ── Step 3: Set up the project ────────────────────────────────────────
        System.out.println();
        System.out.print("  Enter Tender Reference Number: ");
        String tenderRef = scanner.nextLine().trim();

        // The contract value equals the winning bid by default,
        // but the spec says "capture the agreed contract value" so we ask.
        double contractValue = readPositiveDouble(
                "  Enter Agreed Contract Value (UGX): ");

        Project project = new Project(tenderRef, contractValue, winner);

        // ── Step 4: Capture client payments (deposits) ────────────────────────
        System.out.println();
        System.out.println("  --- Client Payments Received ---");
        int paymentCount = readPositiveInt("  How many payment entries do you want to record? ");
        for (int i = 1; i <= paymentCount; i++) {
            double amount = readPositiveDouble("  Payment #" + i + " amount (UGX): ");
            project.addPayment(amount);
        }

        // ── Step 5: Capture project expenses ─────────────────────────────────
        System.out.println();
        System.out.println("  --- Project Expenses Incurred ---");
        int expenseCount = readPositiveInt("  How many expense entries do you want to record? ");
        for (int i = 1; i <= expenseCount; i++) {
            System.out.print("  Expense #" + i + " description: ");
            String desc = scanner.nextLine().trim(); // for context only
            double amount = readPositiveDouble("  Expense #" + i + " amount (UGX): ");
            project.addExpense(amount);
        }

        // ── Step 6: Print the full project financial report ───────────────────
        project.printProjectReport();

        scanner.close();
    }

    // ── Helper: collect 4 bids with exception handling ────────────────────────
    private static ArrayList<Bidder> collectBids() {
        ArrayList<Bidder> bidders = new ArrayList<>();
        System.out.println();
        System.out.println("  Enter details for " + TOTAL_BIDDERS + " bidders:");
        System.out.println();

        for (int i = 1; i <= TOTAL_BIDDERS; i++) {
            System.out.print("  Bidder #" + i + " Name: ");
            String name = scanner.nextLine().trim();

            double bidAmount = Double.MIN_VALUE; // sentinel
            while (bidAmount == Double.MIN_VALUE) {
                System.out.print("  Bidder #" + i + " Bid Amount (UGX): ");
                try {
                    bidAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                } catch (InputMismatchException e) {
                    System.out.println("  ERROR: Please enter a valid numeric bid amount.");
                    scanner.nextLine();
                    bidAmount = Double.MIN_VALUE; // stay in loop
                }
            }

            Bidder b = new Bidder(name, bidAmount);
            if (!b.isValid()) {
                System.out.println("  NOTE: Bid of UGX " + bidAmount
                        + " by " + name + " is rejected (zero or negative).");
            }
            bidders.add(b);
        }
        return bidders;
    }

    // ── Helper: find the lowest valid bid ────────────────────────────────────
    private static Bidder selectWinner(ArrayList<Bidder> bidders) {
        Bidder winner = null;
        for (Bidder b : bidders) {
            if (b.isValid()) {
                if (winner == null || b.getBidAmount() < winner.getBidAmount()) {
                    winner = b;
                }
            }
        }
        return winner; // null if no valid bid exists
    }

    // ── Helper: read a positive double with exception handling ────────────────
    private static double readPositiveDouble(String prompt) {
        double value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value <= 0) {
                    System.out.println("  ERROR: Amount must be greater than zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("  ERROR: Invalid input. Enter a numeric value.");
                scanner.nextLine();
            }
        }
        return value;
    }

    // ── Helper: read a positive integer with exception handling ───────────────
    private static int readPositiveInt(String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                if (value <= 0) {
                    System.out.println("  ERROR: Must be greater than zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("  ERROR: Invalid input. Enter a whole number.");
                scanner.nextLine();
            }
        }
        return value;
    }
}