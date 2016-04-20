package ch.heigvd.res.mailrobot.smtp;

import java.io.IOException;

import ch.heigvd.res.mailrobot.mail.Message;

public interface ISmtpClient {

		public void sendMessage(Message message) throws IOException;
	
}
