package project;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		String id = "wangani55";
		String pass = "rlaehgud13";
		return new PasswordAuthentication(id, pass);
	}
}
