/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tool;

/**
 *
 * @author Leo
 */

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.InputStream;
import java.util.Properties;

public class MailUtil {

    private static Properties mailProps = new Properties();

    static {
        try (InputStream is = MailUtil.class.getResourceAsStream("/mail.properties")) {

            mailProps.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Không load được mail.properties", e);
        }
    }

    public static void send(String to, String subject, String content) {

        final String username = mailProps.getProperty("mail.username");
        final String password = mailProps.getProperty("mail.password");

        Properties props = new Properties();
        props.put("mail.smtp.host", mailProps.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", mailProps.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", mailProps.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable",
                  mailProps.getProperty("mail.smtp.starttls.enable"));

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);

            // Gửi HTML
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Gửi mail thất bại", e);
        }
    }
}
