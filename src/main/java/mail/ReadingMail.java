package mail;

import java.io.IOException;
import java.util.Properties;

import org.eclipse.angus.mail.pop3.POP3Store;

import jakarta.mail.Authenticator;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;

public class ReadingMail {
	static String username = "a5ed720e3bb8b9";
	static String password = "a1be86f7b4a1e7";

	public static Properties getProperties() {
		Properties prop = new Properties();
		prop.put("mail.pop3.host", "pop3.mailtrap.io");
		prop.put("mail.pop3.port", "1100");
		return prop;
	}

	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		POP3Store emailStore = (POP3Store)  Session.getDefaultInstance(getProperties()).getStore("pop3");
		emailStore.connect(username, password);  
		Folder emailFolder = emailStore.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		Message[] messages = emailFolder.getMessages();
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (i + 1));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("Text: " + message.getContent().toString());
		}
		emailFolder.close(false);
		emailStore.close();
		System.out.println("Email Read successfully");
	}
}
