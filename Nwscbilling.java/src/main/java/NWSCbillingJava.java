import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// ---------------------------------------------------------------
// Part (d)(iii): Encapsulated Customer class
// ---------------------------------------------------------------
class Customer {

    private final int    id;
    private final double consumption;
    private String band;
    private double ratePerM3;
    private double fixedCharge;
    private double totalBill;

    public Customer(int id, double consumption) {
        this.id          = id;
        this.consumption = consumption;
        calculateBill();
    }

    // Part (b): determine band, rate, fixed charge using if-else-if
    private void calculateBill() {
        if (consumption >= 0 && consumption <= 5) {
            band        = "LIFELINE";
            ratePerM3   = 1_000;
            fixedCharge = 2_000;
        } else if (consumption <= 20) {
            band        = "DOMESTIC LOW";
            ratePerM3   = 2_500;
            fixedCharge = 4_000;
        } else if (consumption <= 50) {
            band        = "DOMESTIC HIGH";
            ratePerM3   = 3_800;
            fixedCharge = 7_500;
        } else if (consumption <= 100) {
            band        = "COMMERCIAL";
            ratePerM3   = 4_500;
            fixedCharge = 15_000;
        } else if (consumption <= 300) {
            band        = "INDUSTRIAL";
            ratePerM3   = 5_200;
            fixedCharge = 40_000;
        } else {
            band        = "INSTITUTIONAL";
            ratePerM3   = 6_000;
            fixedCharge = 90_000;
        }

        // Part (c): total bill formula
        totalBill = (consumption * ratePerM3) + fixedCharge;
    }

    // Getters
    public int    getId()          { return id; }
    public double getConsumption() { return consumption; }
    public String getBand()        { return band; }
    public double getTotalBill()   { return totalBill; }

    // Part (c): display individual customer bill
    public void printBill() {
        System.out.println("\n========================================");
        System.out.println("  NWSC MONTHLY WATER BILL - Customer " + id);
        System.out.println("========================================");
        System.out.printf("  Consumption     : %.2f m3%n",   consumption);
        System.out.printf("  Customer Band   : %s%n",         band);
        System.out.printf("  Rate per m3     : UGX %,.0f%n", ratePerM3);
        System.out.printf("  Fixed Charge    : UGX %,.0f%n", fixedCharge);
        System.out.println("  ----------------------------------------");
        System.out.printf("  TOTAL BILL      : UGX %,.0f%n", totalBill);
        System.out.println("========================================");
    }
}


// ---------------------------------------------------------------
// Main class
// ---------------------------------------------------------------
public class NWSCbillingJava{

    public static void main(String[] args) {

        // Part (d)(i): accept 6 customers using a loop
        // Part (d)(iii): store them in an ArrayList
        try (Scanner scanner = new Scanner(System.in)) {
            // Part (d)(i): accept 6 customers using a loop
            // Part (d)(iii): store them in an ArrayList
            ArrayList<Customer> customers = new ArrayList<>();
            
            int totalCustomers = 6;
            int i = 1;
            
            while (i <= totalCustomers) {
                // Part (d)(iv): exception handling - re-prompt on invalid input
                double consumption = -1;
                boolean validInput = false;
                
                while (!validInput) {
                    System.out.printf("%nEnter monthly water consumption for Customer %d (m3): ", i);
                    try {
                        consumption = scanner.nextDouble();
                        if (consumption < 0) {
                            System.out.println("Error: Consumption cannot be negative. Please try again.");
                        } else {
                            validInput = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input. Enter a numeric value (e.g. 45 or 12.5).");
                        scanner.nextLine(); // clear the bad token
                    }
                }
                
                // Create customer, compute bill, print individual bill
                Customer customer = new Customer(i, consumption);
                customer.printBill();
                customers.add(customer);
                i++;
            }
            
            // ---------------------------------------------------------------
            // Part (d)(ii): billing summary - iterate over the collection
            // ---------------------------------------------------------------
            int lifeline      = 0;
            int domesticLow   = 0;
            int domesticHigh  = 0;
            int commercial    = 0;
            int industrial    = 0;
            int institutional = 0;
            double totalRevenue = 0;
            
            for (Customer c : customers) {
                totalRevenue += c.getTotalBill();
                
                switch (c.getBand()) {
                    case "LIFELINE" -> lifeline++;
                    case "DOMESTIC LOW" -> domesticLow++;
                    case "DOMESTIC HIGH" -> domesticHigh++;
                    case "COMMERCIAL" -> commercial++;
                    case "INDUSTRIAL" -> industrial++;
                    case "INSTITUTIONAL" -> institutional++;
                }
            }
            
            double averageBill = totalRevenue / customers.size();
            
            System.out.println("\n\n============================================");
            System.out.println("         NWSC MONTHLY BILLING SUMMARY        ");
            System.out.println("============================================");
            System.out.printf("  %-20s : %d customer(s)%n", "LIFELINE",      lifeline);
            System.out.printf("  %-20s : %d customer(s)%n", "DOMESTIC LOW",  domesticLow);
            System.out.printf("  %-20s : %d customer(s)%n", "DOMESTIC HIGH", domesticHigh);
            System.out.printf("  %-20s : %d customer(s)%n", "COMMERCIAL",    commercial);
            System.out.printf("  %-20s : %d customer(s)%n", "INDUSTRIAL",    industrial);
            System.out.printf("  %-20s : %d customer(s)%n", "INSTITUTIONAL", institutional);
            System.out.println("  --------------------------------------------");
            System.out.printf("  Total Customers       : %d%n",         customers.size());
            System.out.printf("  Total Revenue         : UGX %,.0f%n",  totalRevenue);
            System.out.printf("  Average Bill          : UGX %,.0f%n",  averageBill);
            System.out.println("============================================");
        }
    }
}