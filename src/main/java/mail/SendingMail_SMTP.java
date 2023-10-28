package mail;

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

public class SendingMail_SMTP {

	static String username="5bb79c7519cb08";
	static String password="3f1075a4cd7483";

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
	
	public static void main(String[] args) throws AddressException, MessagingException {
		Message message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress("from@gmail.com"));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
		message.setSubject("Mail Subject");

		//String msg = "This is my first email using JavaMailer";
		String msg = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
		System.out.println("Email sent");
	}
}
