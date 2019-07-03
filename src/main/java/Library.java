package main.java;

import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>(Arrays.asList(new Book("1984", "Orwell"),
            new Book("To kill a Mockingbird", "Lee"), new Book("The Alchemist", "Coelho"),
            new Book("Head First Java", "Sierra")));

    public void addBook() {
        System.out.println("\nPlease enter the authors name: ");
        String author = scanner.next();
        System.out.println("Please enter the books name: ");
        String name = scanner.next();
        if (name != null && author != null) {
            books.add((new Book(name, author)));
        }
    }

    public void printBooks() {
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

    public void takeBook() {
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
        books.remove((bookName - 1));

    }

    private Scanner scanner = new Scanner(System.in);

}


