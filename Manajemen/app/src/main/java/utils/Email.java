package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public static void sendOtp(String to, String otp) {
        String from = "gamingjam436@gmail.com"; // Ganti dengan email Anda
        String appPassword = "flwy irzl cfgp nmql"; // Ganti dengan App Password atau password email Anda

        // Konfigurasi SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true"); // Tambahkan untuk debugging

        // Membuat sesi
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, appPassword);
            }
        });

        try {
            // Membuat pesan email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            // Mengirim email
            Transport.send(message);
            System.out.println("OTP sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
