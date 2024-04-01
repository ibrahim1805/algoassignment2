import java.util.Scanner;

public class BookDatabase {
    private static final int MIN_ISBN_LENGTH = 10;
    private static final int MAX_ISBN_LENGTH = 13;
    private static final int DATABASE_SIZE = 10;

    private long[] isbnDatabase = new long[DATABASE_SIZE];
    private int bookCount = 0;

    public boolean validateISBN(long isbn) {
        String isbnString = Long.toString(isbn);
        int isbnLength = isbnString.length();

        return isbnLength == MIN_ISBN_LENGTH || isbnLength == MAX_ISBN_LENGTH;
    }

    public boolean searchISBN(long isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (isbnDatabase[i] == isbn) {
                return true;
            }
        }
        return false;
    }

    public void addBook(long isbn) {
        if (bookCount < DATABASE_SIZE) {
            isbnDatabase[bookCount++] = isbn;
            System.out.println("Book with ISBN " + isbn + " added to the database.");
        } else {
            System.out.println("Database is full. Cannot add more books.");
        }
    }

    public void printAllISBNs() {
        if (bookCount == 0) {
            System.out.println("No books in the database.");
        } else {
            System.out.println("ISBNs in the database:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println("- " + isbnDatabase[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDatabase bookDatabase = new BookDatabase();
        
        bookDatabase.addBook(9780132350884L);
        bookDatabase.addBook(9780321356680L);
        bookDatabase.addBook(9780596009205L);

        while (true) {
            System.out.print("Enter ISBN to validate and search (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                long isbn = Long.parseLong(input);
                if (bookDatabase.validateISBN(isbn)) {
                    if (bookDatabase.searchISBN(isbn)) {
                        System.out.println("Book with ISBN " + isbn + " found in the database.");
                    } else {
                        System.out.println("Book with ISBN " + isbn + " not found in the database.");
                    }
                } else {
                    System.out.println("Invalid ISBN. Please enter a 10- or 13-digit number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid ISBN number.");
            }
        }

        scanner.close();
    }
}