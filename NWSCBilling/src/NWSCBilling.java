import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * NWSCBilling.java
 * Main program for Question One.
 *
 * Covers all sub-parts:
 *  (a)-(c) Single-customer bill with if-else-if band logic (inside Customer class).
 *  (d)     Loop over 6 customers, then display billing summary.
 *  (e)     Customers stored in an ArrayList; summary produced by iterating the list.
 *  (f)     Exception handling for invalid (non-numeric / negative) input.
 */
public class NWSCBilling {

    private static final int TOTAL_CUSTOMERS = 6;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("  National Water and Sewerage Corporation");
        System.out.println("        Monthly Billing System");
        System.out.println("============================================");

        // ArrayList to store all Customer objects (required by part e)
        ArrayList<Customer> customers = new ArrayList<>();

        // ── Loop over 6 customers (required by part d) ───────────────────────
        for (int i = 1; i <= TOTAL_CUSTOMERS; i++) {

            double consumption = -1; // sentinel: loop until valid input

            // ── Exception handling (required by part f) ───────────────────────
            while (consumption < 0) {
                System.out.printf("%nEnter monthly water consumption for Customer #%d (m3): ", i);
                try {
                    consumption = scanner.nextDouble();
                    if (consumption < 0) {
                        System.out.println("  ERROR: Consumption cannot be negative. Please re-enter.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("  ERROR: Invalid input. Please enter a numeric value.");
                    scanner.nextLine(); // clear the bad token from the buffer
                    consumption = -1;  // force another iteration
                }
            }
            scanner.nextLine(); // consume leftover newline

            // Create Customer object; band + bill computed inside the constructor
            Customer c = new Customer(i, consumption);
            c.printBill();

            customers.add(c); // add to collection
        }

        // ── Billing Summary (required by parts d and e) ───────────────────────
        printSummary(customers);

        scanner.close();
    }

    /**
     * Iterates over the ArrayList to compute and display the billing summary.
     * Uses a HashMap to count customers per band.
     */
    private static void printSummary(ArrayList<Customer> customers) {

        // Count customers per band
        Map<String, Integer> bandCount = new HashMap<>();
        double totalRevenue = 0;

        for (Customer c : customers) {
            totalRevenue += c.getTotalBill();
            String band = c.getBand();
            // If band not yet in map, start at 0 then add 1
            bandCount.put(band, bandCount.getOrDefault(band, 0) + 1);
        }

        double averageBill = totalRevenue / customers.size();

        System.out.println();
        System.out.println("============================================");
        System.out.println("         MONTHLY BILLING SUMMARY");
        System.out.println("============================================");

        // Defined order to print bands
        String[] bands = {
            "LIFELINE", "DOMESTIC LOW", "DOMESTIC HIGH",
            "COMMERCIAL", "INDUSTRIAL", "INSTITUTIONAL"
        };

        System.out.println("  Customers per Band:");
        for (String band : bands) {
            int count = bandCount.getOrDefault(band, 0);
            if (count > 0) {
                System.out.printf("    %-20s : %d customer(s)%n", band, count);
            }
        }

        System.out.println("--------------------------------------------");
        System.out.printf("  Total Revenue Collected : UGX %,.0f%n", totalRevenue);
        System.out.printf("  Number of Customers     : %d%n",          customers.size());
        System.out.printf("  Average Bill per Customer: UGX %,.0f%n",  averageBill);
        System.out.println("============================================");
    }
}