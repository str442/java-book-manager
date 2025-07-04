import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            menuPresentation();
            try {
                int option = sc.nextInt();
                sc.nextLine(); // limpa o buffer

                switch (option) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter author: ");
                        String author = sc.nextLine();
                        manager.addBook(new Book(title, author));
                        saveBookToFile(new Book(title, author));
                        break;
                    case 2:
                        manager.listBooks();
                        break;
                    case 3:
                        System.out.print("Enter title: ");
                        String searchTitle = sc.nextLine();
                        manager.searchByTitle(searchTitle);
                        break;
                    case 4:
                        System.out.print("Enter title: ");
                        String removeTitle = sc.nextLine();
                        manager.removeByTitle(removeTitle);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        sc.close(); // fecha o Scanner aqui
                        return;
                    default:
                        System.out.println("Invalid option. Try again!");
                }
            } catch (Exception e) {
                System.err.println("Error. Put a valid option.");
                sc.nextLine();
            }
        }
    }

    public static void menuPresentation() {
        System.out.println("\n== Book Manager ==");
        System.out.println("1. Add book");
        System.out.println("2. List books");
        System.out.println("3. Search by title");
        System.out.println("4. Remove by title");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public static void saveBookToFile(Book book) {
        try (FileWriter writer = new FileWriter("books.txt", true)) { // true = append
            writer.write(book.getTitle() + ";" + book.getAuthor() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving book to file.");
        }
    }

}
