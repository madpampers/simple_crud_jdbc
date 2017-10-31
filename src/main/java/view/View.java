package view;

import controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class View {

    private static Controller controller;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void showLoginView() {
        System.out.println("*****************************************");
        System.out.println("|               Welcome                 |");
        System.out.println("*****************************************");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.onShowLoginView();
    }

    public static void setController(Controller controller) {
        View.controller = controller;
    }

    public void showMenu() {
        System.out.println("*****************************************");
        System.out.println("|               Main Menu               |");
        System.out.println("*****************************************");
        System.out.println("| choose option:                        |");
        System.out.println("|        1. Create Database Records     |");
        System.out.println("|        2. Read Database Records       |");
        System.out.println("|        3. Update Database Records     |");
        System.out.println("|        4. Delete Database Records     |");
        System.out.println("|        5. Exit                        |");
        System.out.println("*****************************************");
        System.out.print("Select option: ");

        switch (getInput()) {
            case "1":
                controller.onSelectCreate();
                break;
            case "2":
                controller.onSelectRead();
                break;
            case "3":
                controller.onSelectUpdate();
                break;
            case "4":
                controller.onSelectDelete();
                break;
            case "5":
                controller.shutDown();
                break;
            default:
                System.out.println("error");
                controller.onWrongInput();
                break;
        }
    }

    private static String getInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public void showError(SQLException e) {
        System.out.println("Error: " + e.getMessage());
        controller.shutDown();
    }

    public void showCreateView() {
        System.out.println("*****************************************");
        System.out.println("|         Creating new entry            |");
        System.out.println("*****************************************");

        System.out.print("Enter new book name: ");
        String bookName = getInput();

        System.out.print("Enter new book author: ");
        String bookAuthor = getInput();

        System.out.print("Enter how many pages: ");
        int pages = Integer.parseInt(getInput());

        controller.onCreate(bookName, bookAuthor, pages);
    }

    public void showReadView() {
        System.out.println("*****************************************");
        System.out.println("|           Reading  entry              |");
        System.out.println("*****************************************");

        System.out.print("Enter book name to read, or enter \"All books\": ");
        String bookName = getInput();

        controller.onRead(bookName);
    }

    public void showUpdateView() {
        System.out.println("*****************************************");
        System.out.println("|           Updating entry              |");
        System.out.println("*****************************************");

        System.out.print("Enter book id to update info: ");
        int bookID = Integer.parseInt(getInput());

        System.out.print("Enter book rating: ");
        int bookRating = Integer.parseInt(getInput());

        System.out.print("Enter new book pages (if you lost some ;)): ");
        int pages = Integer.parseInt(getInput());

        controller.onUpdate(bookID, bookRating, pages);
    }

    public void showDeleteView() {
        System.out.println("*****************************************");
        System.out.println("|           Deleting entry              |");
        System.out.println("*****************************************");

        System.out.print("Enter book name to delete: ");
        int bookID = Integer.parseInt(getInput());

        controller.onDelete(bookID);
    }

    public void showResultView(List<String> data) {
        if (data.size() == 0) {
            System.out.println("Your library is empty");
        } else {

            System.out.println("*****************************************");
            System.out.println("|          Your library is:             |");
            System.out.println("*****************************************\n\n");

            StringBuilder stringBuilder = new StringBuilder("---------------------------------------" +
                    "-----------------------------------------------\n");
            int countColumns = 0;
            for (String datum : data) {
                if (countColumns == 0) stringBuilder.append("|");
                stringBuilder.append(" ").append(datum);
                for (int i = 0; i < 15 - datum.length(); i++) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append("|");
                countColumns++;
                if (countColumns == 5) {
                    countColumns = 0;
                    stringBuilder.append("\n");
                }
            }
            stringBuilder.append("---------------------------------------" +
                    "-----------------------------------------------\n");

            System.out.println(stringBuilder.toString());

            System.out.println("Press any key to contine...");

            getInput();

            showMenu();
        }
    }
}

