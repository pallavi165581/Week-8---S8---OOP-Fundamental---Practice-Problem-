import java.util.Scanner;

// Problem 1 - Payment System Fee Calculation
// Each payment type overrides feeRate(); the main loop processes all of them uniformly.
public class PaymentFeeCalculator {

    static abstract class Payment {
        protected String type;
        protected double amount;

        Payment(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        abstract double feeRate(); // each payment type decides its own fee

        double finalAmount() {
            return amount + amount * feeRate();
        }
    }

    static class CardPayment extends Payment {
        CardPayment(double amount) { super("CARD", amount); }
        double feeRate() { return 0.02; }
    }

    static class WalletPayment extends Payment {
        WalletPayment(double amount) { super("WALLET", amount); }
        double feeRate() { return 0.01; }
    }

    static class BankTransferPayment extends Payment {
        BankTransferPayment(double amount) { super("BANKTRANSFER", amount); }
        double feeRate() { return 0.0; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Payment[] payments = new Payment[n];
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split(" ");
            double amount = Double.parseDouble(parts[1]);
            switch (parts[0]) {
                case "CARD":         payments[i] = new CardPayment(amount); break;
                case "WALLET":       payments[i] = new WalletPayment(amount); break;
                case "BANKTRANSFER": payments[i] = new BankTransferPayment(amount); break;
            }
        }

        // one polymorphic loop - no type checks, dynamic dispatch does the work
        double total = 0;
        for (Payment p : payments) {
            double adjusted = p.finalAmount();
            System.out.printf("%s: %.2f%n", p.type, adjusted);
            total += adjusted;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
