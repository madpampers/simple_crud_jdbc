import controller.Controller;
import model.Model;
import view.View;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();

        View.setController(controller);
        controller.setModel(model);
        controller.setView(view);

        View.showLoginView();
    }
}
