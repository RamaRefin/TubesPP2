import controller.UserController;
import model.User;
import view.UserView;

public class Main {
    public static void main(String[] args) {
        User model = new User("John Doe");
        UserView view = new UserView();
        UserController controller = new UserController(model, view);

        controller.updateView();

        controller.setUserName("Jane Doe");
        controller.updateView();
    }
}
