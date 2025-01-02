package main;

import controller.RegisterController;
import view.RegisterView;

public class Main {
    public static void main(String[] args) {
        // Initialize the view and controller
        RegisterView view = new RegisterView();
        new RegisterController(view);
    }
}