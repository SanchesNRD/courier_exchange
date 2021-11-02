package by.epam.courierexchange.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final Properties properties = new Properties();
    private static final String MAIL_PROPERTIES = "mail/mail.properties";
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";
    private static final String MAIL_FROM = "mail.from";
    private static final String MAIL_SUBJECT = "CourierExchange";
    private static final String TEXT_HTML = "text/html";
    private static final String STR_ID = "ID";
    private static final String TEXT_FORGOT_PASS = """
            <h2>Reset password request </h2>
            <h3> Please click on the button to reset password</h3> 
            <a href=http://localhost:8080/courier_exchange_war_exploded/controller?command=go_to_new_pass&id=ID>
            <button>Reset your password</button></a>
            """;
    private static final String TEXT_CONFIRM_PROFILE = """
            <h2>Confirm profile request </h2>
            <h3> Please click on the button to confirm your profile</h3> 
            <a href=http://localhost:8080/courier_exchange_war_exploded/controller?command=confirm_profile&id=ID>
            <button>Confirm profile</button></a>
            """;

    static {
        try (InputStream inputStream = MailSender.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Properties exception: " + e.getMessage());
        }
    }

    public static String confirmYourProfileMessage(long id){
        return TEXT_CONFIRM_PROFILE.replaceAll(STR_ID, String.valueOf(id));
    }

    public static String forgotPasswordMessage(long id){
        return TEXT_FORGOT_PASS.replaceAll(STR_ID, String.valueOf(id));
    }

    public static void send(String emailTo, String messageText) {
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty(MAIL_USER_NAME), properties.getProperty(MAIL_USER_PASSWORD));
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty(MAIL_FROM)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(MAIL_SUBJECT);
            message.setContent( messageText,TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("MessagingException: " + e.getMessage());
        }
    }

    private MailSender(){};
}
