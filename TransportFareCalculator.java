import java.util.Scanner;

// Problem 5 - Public Transport Fare Calculator
// Each transport type overrides calculateFare() with its own fare structure.
public class TransportFareCalculator {

    static abstract class Transport {
        protected String type;
        protected double distance;

        Transport(String type, double distance) {
            this.type = type;
            this.distance = distance;
        }

        abstract double calculateFare();
    }

    static class Bus extends Transport {
        private static final double MAX_FARE = 10.0;

        Bus(double distance) { super("BUS", distance); }
        double calculateFare() {
            return Math.min(2 + 0.10 * distance, MAX_FARE); // bus fare is capped
        }
    }

    static class Train extends Transport {
        Train(double distance) { super("TRAIN", distance); }
        double calculateFare() {
            return 3 + 0.15 * distance;
        }
    }

    static class Metro extends Transport {
        private double peakHourFactor;

        Metro(double distance, double peakHourFactor) {
            super("METRO", distance);
            this.peakHourFactor = peakHourFactor;
        }
        double calculateFare() {
            return (1.50 + 0.20 * distance) * peakHourFactor;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Transport[] journeys = new Transport[n];
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split(" ");
            double distance = Double.parseDouble(parts[1]);
            switch (parts[0]) {
                case "BUS":   journeys[i] = new Bus(distance); break;
                case "TRAIN": journeys[i] = new Train(distance); break;
                case "METRO":
                    double peak = Double.parseDouble(parts[2]);
                    journeys[i] = new Metro(distance, peak); break;
            }
        }

        double total = 0;
        for (Transport t : journeys) {
            double fare = t.calculateFare();
            System.out.printf("%s: %.2f%n", t.type, fare);
            total += fare;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
