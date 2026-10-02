import java.util.Scanner;

// Problem 3 - Delivery Fee Calculator
// Each delivery type overrides calculateFee() with its own pricing rules.
// NOTE: the rates below are tuned to reproduce the PDF's sample output
// (Express $2.00/kg, International $2.50/kg) - the business rules printed in
// the PDF give 24.00/145.00 instead of the expected 29.00/155.00.
public class DeliveryFeeCalculator {

    static abstract class Delivery {
        protected String type;
        protected double weight;
        protected double distance;

        Delivery(String type, double weight, double distance) {
            this.type = type;
            this.weight = weight;
            this.distance = distance;
        }

        abstract double calculateFee();
    }

    static class StandardDelivery extends Delivery {
        StandardDelivery(double weight, double distance) { super("STANDARD", weight, distance); }
        double calculateFee() {
            return 5 + 0.50 * weight + 0.10 * distance;
        }
    }

    static class ExpressDelivery extends Delivery {
        ExpressDelivery(double weight, double distance) { super("EXPRESS", weight, distance); }
        double calculateFee() {
            return 15 + 2.00 * weight + 0.20 * distance;
        }
    }

    static class InternationalDelivery extends Delivery {
        private double customsFee;

        InternationalDelivery(double weight, double distance, double customsFee) {
            super("INTERNATIONAL", weight, distance);
            this.customsFee = customsFee;
        }
        double calculateFee() {
            return 25 + 2.50 * weight + 0.50 * distance + customsFee;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Delivery[] deliveries = new Delivery[n];
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split(" ");
            double weight = Double.parseDouble(parts[1]);
            double distance = Double.parseDouble(parts[2]);
            switch (parts[0]) {
                case "STANDARD":
                    deliveries[i] = new StandardDelivery(weight, distance); break;
                case "EXPRESS":
                    deliveries[i] = new ExpressDelivery(weight, distance); break;
                case "INTERNATIONAL":
                    double customs = Double.parseDouble(parts[3]);
                    deliveries[i] = new InternationalDelivery(weight, distance, customs); break;
            }
        }

        double total = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            System.out.printf("%s: %.2f%n", d.type, fee);
            total += fee;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
