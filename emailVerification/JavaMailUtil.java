package emailVerification;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import core.register;

public class JavaMailUtil
{
	public static void sendMail(String toEmail, String verCode)
	{
		final String username = "youremail@gmail.com";
		final String password = "yourpassword";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(properties, new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try
		{
			MimeMessage msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Email Auth - Community Service Tracker");
			msg.setContent("<div style=\"font-family:Arial, Helvetica, sans-serif; font-size: 18px;\"><p>This email was send to you because you registered for a Community Service Tracker Program. Do not worry if this was sent to you by mistake please ignore this message. Copy this code into the text box on the program after you click ok and then fill in the code that is listed below and then click ok then you are free to login.</p><br><p>Your Verification Code: </p><a style=\"text-decoration: none; background-color: lightgrey; border: 2px solid black; padding: 5px;\">" + verCode + "</a><br><p>This code is one use and is good until you close the dialog window asking for the code. Enjoy my Community Service Tracker.</p></div>", "text/html");
			System.out.println(verCode);
			Transport.send(msg);
			JOptionPane.showMessageDialog(register.contentPane, "Verification Code was sent to the address: " + toEmail);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
