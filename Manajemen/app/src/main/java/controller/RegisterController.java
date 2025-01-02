package controller;

import model.User;
import model.MyBatisUtil;
import utils.Email;
import view.RegisterView;

import javax.swing.*;
import java.util.Random;

public class RegisterController {
    private RegisterView view;
    private String generatedOtp;

    public RegisterController(RegisterView view) {
        this.view = view;

        // Add action listener to the register button
        this.view.getRegisterButton().addActionListener(e -> handleRegister());
    }

    private void handleRegister() {
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate OTP and send email
        generatedOtp = generateOtp();
        Email.sendOtp(email, generatedOtp);

        // Show OTP input dialog
        String userOtp = JOptionPane.showInputDialog(view, "Enter the OTP sent to your email:");

        if (userOtp != null && userOtp.equals(generatedOtp)) {
            // Save user to database
            User user = new User(username, email, password);
            MyBatisUtil.insertUser(user);
            JOptionPane.showMessageDialog(view, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid OTP!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
