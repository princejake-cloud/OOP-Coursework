/**
 * Customer.java
 * Represents a single water customer with encapsulated billing fields.
 * OOP Design: all fields are private; access is through getters only.
 * The class is responsible for knowing its own band and computing its own bill.
 */
public class Customer {

    // ── private fields (encapsulation) ──────────────────────────────────────
    private final int    customerId;
    private final double consumption;   // m3 consumed in the month
    private final String band;          // tariff band name
    private final double ratePerM3;     // UGX per m3
    private final double serviceCharge; // fixed monthly UGX charge
    private final double totalBill;     // computed bill in UGX

    // ── constructor ──────────────────────────────────────────────────────────
    public Customer(int customerId, double consumption) {
        this.customerId  = customerId;
        this.consumption = consumption;

        // Determine band using if-else-if as required by the coursework
        if (consumption >= 0 && consumption <= 5) {
            this.band          = "LIFELINE";
            this.ratePerM3     = 1_000;
            this.serviceCharge = 2_000;
        } else if (consumption <= 20) {
            this.band          = "DOMESTIC LOW";
            this.ratePerM3     = 2_500;
            this.serviceCharge = 4_000;
        } else if (consumption <= 50) {
            this.band          = "DOMESTIC HIGH";
            this.ratePerM3     = 3_800;
            this.serviceCharge = 7_500;
        } else if (consumption <= 100) {
            this.band          = "COMMERCIAL";
            this.ratePerM3     = 4_500;
            this.serviceCharge = 15_000;
        } else if (consumption <= 300) {
            this.band          = "INDUSTRIAL";
            this.ratePerM3     = 5_200;
            this.serviceCharge = 40_000;
        } else {
            this.band          = "INSTITUTIONAL";
            this.ratePerM3     = 6_000;
            this.serviceCharge = 90_000;
        }

        // Total bill formula from the question:
        // Total bill = (consumption * rate per m3) + fixed service charge
        this.totalBill = (consumption * ratePerM3) + serviceCharge;
    }

    // ── getters ──────────────────────────────────────────────────────────────
    public int    getCustomerId()   { return customerId;   }
    public double getConsumption()  { return consumption;  }
    public String getBand()         { return band;         }
    public double getRatePerM3()    { return ratePerM3;    }
    public double getServiceCharge(){ return serviceCharge;}
    public double getTotalBill()    { return totalBill;    }

    // ── display helper ───────────────────────────────────────────────────────
    public void printBill() {
        System.out.println("--------------------------------------------");
        System.out.println("  NWSC Monthly Water Bill - Customer #" + customerId);
        System.out.println("--------------------------------------------");
        System.out.printf("  Consumption      : %.2f m3%n",     consumption);
        System.out.printf("  Customer Band    : %s%n",           band);
        System.out.printf("  Rate per m3      : UGX %,.0f%n",   ratePerM3);
        System.out.printf("  Service Charge   : UGX %,.0f%n",   serviceCharge);
        System.out.printf("  TOTAL BILL       : UGX %,.0f%n",   totalBill);
        System.out.println("--------------------------------------------");
    }
}