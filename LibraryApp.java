import java.util.*;

class Book {
    private String Id;
    private String Title;
    private String Author;
    private String Genre;
    private String status;

    public Book(String Id, String Title, String Author, String Genre, String status) {
        this.Id = Id;
        this.Title = Title;
        this.Author = Author;
        this.Genre = Genre;
        this.status = status;
    }

    public String getId() { return Id; }
    public String getTitle() { return Title; }
    public void setTitle(String Title) { this.Title = Title; }
    public String getAuthor() { return Author; }
    public void setAuthor(String Author) { this.Author = Author; }
    public String getGenre() { return Genre; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ID: " + Id + ", Title: " + Title + ", Author: " + Author +
               ", Genre: " + Genre + ", Status: " + status;
    }
}

class LibraryManagementSystem {
    private List<Book> books;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
    }

    public void addBook(String Id, String Title, String Author, String Genre, String status) {
        for (Book book : books) {
            if (book.getId().equals(Id)) {
                System.out.println("Book ID must be unique.");
                return;
            }
        }
        books.add(new Book(Id, Title, Author, Genre, status));
        System.out.println("Book added successfully!");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String searchQuery) {
        boolean found = false;
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(searchQuery) || book.getTitle().equalsIgnoreCase(searchQuery)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public void updateBook(String Id, String newTitle, String newAuthor, String newStatus) {
        for (Book book : books) {
            if (book.getId().equals(Id)) {
                if (!newTitle.isEmpty()) book.setTitle(newTitle);
                if (!newAuthor.isEmpty()) book.setAuthor(newAuthor);
                if (!newStatus.isEmpty()) book.setStatus(newStatus);
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void deleteBook(String Id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(Id)) {
                iterator.remove();
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void exitSystem() {
        System.out.println("Exiting the Library Management System. Goodbye!");
        System.exit(0);
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String Id = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String Title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String Author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String Genre = scanner.nextLine();
                    System.out.print("Enter Availability Status (Available/Checked Out): ");
                    String status = scanner.nextLine();
                    library.addBook(Id, Title, Author, Genre, status);
                    break;

                case 2:
                    library.viewAllBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID or Title to search: ");
                    String searchQuery = scanner.nextLine();
                    library.searchBook(searchQuery);
                    break;

                case 4:
                    System.out.print("Enter Book ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter new Title (or leave blank): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new Author (or leave blank): ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new Availability Status (Available/Checked Out, or leave blank): ");
                    String newStatus = scanner.nextLine();
                    library.updateBook(updateId, newTitle, newAuthor, newStatus);
                    break;

                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    String deleteId = scanner.nextLine();
                    library.deleteBook(deleteId);
                    System.out.println("Deleted the book succesfully.");
                    break;

                case 6:
                    library.exitSystem();
                    break;

                default:
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}