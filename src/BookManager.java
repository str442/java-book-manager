
/**
 * This class is responsable to manage the books.
 * 
 * author Guilherme (str442 on GitHub)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookManager {
    private List<Book> list;

    /**
     * Creates and incialize the list of books
     */
    public BookManager() {
        this.list = new ArrayList<>();
        loadBooksFromFile();
    }

    /**
     * Add a book to the list
     * 
     * @requires book != null
     */
    public void addBook(Book book) {
        list.add(book);
    }

    /**
     * List all the books of the list
     * 
     * @requires !list.isEmpty()
     */
    public void listBooks() {
        if (list.isEmpty()) {
            System.out.println("No books in the list.");
        } else {
            for (Book b : list) {
                System.out.println(b);
            }
        }
    }

    /**
     * Searchs a book by his title. If the book was found, it appears a message with
     * him.
     * Otherwise, appears a message saying that the book wasn't on the list.
     * 
     * @param title Title of the book
     * @requires title != null
     */
    public void searchByTitle(String title) {
        boolean found = false;
        for (Book b : list) {
            if (b.getTitle().toLowerCase().equals(title.toLowerCase())) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with that title");
        }
    }

    /**
     * Removes a book by his title. If the book is not in the list, it can't be
     * remove.
     * 
     * @param title Title of the book
     */
    public void removeByTitle(String title) {
        Iterator<Book> it = list.iterator();
        boolean removed = false;

        while (it.hasNext()) {
            Book b = it.next();
            if (b.getTitle().equalsIgnoreCase(title)) {
                it.remove();
                removed = true;
                System.out.println("Book removed");
                break;
            }
        }

        if (!removed) {
            System.out.println("The book can't be removed because it isn't in the list");
        } else {
            updateBooksFile();
        }
    }

    /**
     * Rewrites the books.txt file with the current list of books.
     */
    public void updateBooksFile() {
        try (FileWriter writer = new FileWriter("books.txt", false)) { // false = sobrescrever
            for (Book b : list) {
                writer.write(b.getTitle() + ";" + b.getAuthor() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error updating the file.");
        }
    }

    /**
     * Loads books from the file "books.txt" into the current book list.
     * Each line in the file must be in the format: title;author
     * If the file does not exist or cannot be read, an error message is printed
     * and the list remains unchanged.
     */
    public void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    Book book = new Book(parts[0], parts[1]);
                    list.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading books from file.");
        }
    }

}
