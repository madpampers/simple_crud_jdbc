package controller;

import model.Model;
import view.View;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public void onShowLoginView() {
        try {
            model.connect();
        } catch (SQLException e) {
            view.showError(e);
        }
        view.showMenu();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onSelectCreate() {
        view.showCreateView();
    }

    public void onSelectRead() {
        view.showReadView();
    }

    public void onSelectUpdate() {
        view.showUpdateView();
    }

    public void onSelectDelete() {
        view.showDeleteView();
    }

    public void shutDown() {
        System.exit(0);
    }

    public void onWrongInput() {
        view.showMenu();
    }

    public void onCreate(String bookName, String bookAuthor, int pages) {
        try {
            model.addEntry(bookName, bookAuthor, pages);
        } catch (SQLException e) {
            view.showError(e);
        }
        onRead("All books");
    }

    public void onRead(String bookName) {
        List<String> toView = null;
        try {
        if (bookName.equals("All books")) {
            toView = model.showAllEntries();
        } else {
            toView = model.showEntry(bookName);
        } }
        catch (SQLException e) {
            view.showError(e);
        }
        view.showResultView(toView);
    }

    public void onUpdate(int bookName, int bookRating, int pages) {
        try {
            model.updateEntry(bookName, bookRating, pages);
        } catch (SQLException e) {
            view.showError(e);
        }
        onRead("All books");
    }

    public void onDelete(int bookID) {
        try {
            model.deleteEntry(bookID);
        } catch (SQLException e) {
            view.showError(e);
        }
        onRead("All books");
    }
}
