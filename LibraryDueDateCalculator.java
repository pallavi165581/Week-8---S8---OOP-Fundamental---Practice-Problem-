import java.time.LocalDate;
import java.util.Scanner;

// Problem 2 - Library Item Due Date Calculator
// Each item type overrides loanDays(); due date = current date + that many days.
public class LibraryDueDateCalculator {

    private static final LocalDate TODAY = LocalDate.of(2023, 10, 26); // fixed per problem

    static abstract class LibraryItem {
        protected String title;

        LibraryItem(String title) {
            this.title = title;
        }

        abstract int loanDays(); // each item type has its own borrowing period

        LocalDate dueDate() {
            return TODAY.plusDays(loanDays());
        }
    }

    static class Book extends LibraryItem {
        Book(String title) { super(title); }
        int loanDays() { return 14; }
    }

    static class Dvd extends LibraryItem {
        Dvd(String title) { super(title); }
        int loanDays() { return 7; }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title) { super(title); }
        int loanDays() { return 3; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        LibraryItem[] items = new LibraryItem[n];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String type = line.substring(0, line.indexOf(' '));
            // title comes in quotes and may contain spaces - strip them off
            String title = line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));

            switch (type) {
                case "BOOK":     items[i] = new Book(title); break;
                case "DVD":      items[i] = new Dvd(title); break;
                case "MAGAZINE": items[i] = new Magazine(title); break;
            }
        }

        for (LibraryItem item : items) {
            System.out.println(item.title + ": " + item.dueDate());
        }
        sc.close();
    }
}
