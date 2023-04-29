package mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class SendingMailWithAttachment {

	static String username="a5ed720e3bb8b9";
	static String password="a1be86f7b4a1e7";

	public static Properties getProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.mailtrap.io");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
		return prop;
	}
	
	public static Session getSession(){
		return Session.getInstance(getProperties(), new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, password);
		    }
		});
	}
	
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		Message message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress("from@gmail.com"));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
		message.setSubject("Mail Subject");

		String msg = "This is my first email using JavaMailer";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
		mimeBodyPart.attachFile(new File("pom.xml"));

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
		System.out.println("Email sent");
	}
}
