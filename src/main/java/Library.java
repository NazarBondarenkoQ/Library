package main.java;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

class Library {
    private List<Book> books = new ArrayList<>(Arrays.asList(new Book("1984", "Orwell"),
            new Book("To kill a Mockingbird", "Lee"), new Book("The Alchemist", "Coelho"),
            new Book("Head First Java", "Sierra")));
    private Map<LocalDate, Book> registration = new HashMap<>();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    Library() {
        System.out.println("~~~~~~~~~~~~~Welcome to the interactive library~~~~~~~~~~~~~");
        start();
    }

    private void start() {
        int choice = 0;
        System.out.println("~~~~~~~~~~~~~~~~PLEASE SELECT AN ACTION~~~~~~~~~~~~~~~~");
        System.out.println(" 1) - Add some book to the library;\n" +
                " 2) - Show all books available in the library;\n" +
                "3) - Take the book;\n" + "4) - Show dates, when the books were taken;\n" +
                "5) - Show which books were taken;\n" + "6) - Find the book by the date;\n" +
                "7) - Stop the program; \n");
        do {
            try {
                choice = scanner.nextInt();
                if (choice < 0 || choice > 7){
                    System.out.println("Such option is not available. Please select any of available options.");
                }
            } catch (InputMismatchException | IllegalStateException exception) {
                System.out.println("You have entered incorrect data, please try again.");
                System.exit(-1);
            }

        } while ((choice <= 0) || (choice > 7));
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                printBooks();
                break;
            case 3:
                takeBook();
                break;
            case 4:
                showDate();
                break;
            case 5:
                showTakenBooks();
                break;
            case 6:
                findBook();
                break;
            case 7:
                System.exit(1);
                break;
        }
        start();
    }

    private void addBook() {
        System.out.println("\nPlease enter the authors name: ");
        String author = scanner.next();
        System.out.println("Please enter the books name: ");
        String name = scanner.next();
        if (name != null && author != null) {
            books.add((new Book(name, author)));
        }
    }

    private void printBooks() {
        if (books.isEmpty()) {
            System.out.println("\nThere are no books left in the library");
        } else {
            for (Book i : books) {
                if (i != null) {
                    System.out.println("Shelf: " + (books.indexOf(i) + 1)
                            + ": Author: " + i.getAuthor() + "; " + "Book: " + "\"" + i.getName() + "\"" + "; ");
                }
            }
        }
    }

    private void takeBook() {
        System.out.println("Please select a book that you want to take: ");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null) {
                System.out.println((i + 1) + ": " + "\"" + books.get(i).getName() + "\" ");
            }
        }
        System.out.println("Please enter the number: ");
        int bookName = 0;
        do {
            try {
                bookName = scanner.nextInt();
            } catch (InputMismatchException | IllegalStateException exception) {
                System.out.println("You have entered incorrect data, please try again.");
                break;
            }
            if (bookName <= 0 || bookName > books.size()) {
                System.out.println("Such book is not in the list, please select the book from available ones: ");
            }

        } while (bookName <= 0 || bookName > books.size());
        System.out.println("You have taken the: " + "\"" + books.get((bookName - 1)).getName() + "\" written by: " + books.get((bookName - 1)).getAuthor());
        int minDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2019, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        registration.put(LocalDate.ofEpochDay(randomDay), books.get(bookName - 1));
        books.remove((bookName - 1));
    }

    private void showDate() {
        if (registration.isEmpty()) {
            System.out.println("No books were taken yet.");
        } else {
                System.out.println("Date: " + registration.keySet());
            }
        }

    private void showTakenBooks() {
        if (registration.isEmpty()) {
            System.out.println("No books were taken yet.");
        } else {
            System.out.println("\nList of taken books: ");
            for (Book i : registration.values()) {
                System.out.println("Book: " + "\"" + i.getName() + "\"");
            }
        }
    }

    private void findBook() {
        if (registration.isEmpty()) {
            System.out.println("No books were taken yet.");
        } else {
            LocalDate format = null;
            System.out.println("Please enter the date using such format: " + LocalDate.now());
            try {
                format = LocalDate.parse(scanner.next());
            } catch (DateTimeParseException exception) {
                System.out.println("You have entered invalid data. Please try again");
            }
            if (registration.containsKey(format)) {
                System.out.println("Book: " + "\"" + registration.get(format).getName() + "\"" + " were taken: " + format);
            } else {
                System.out.println("No books were taken on that date");
            }
        }
    }

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

}


