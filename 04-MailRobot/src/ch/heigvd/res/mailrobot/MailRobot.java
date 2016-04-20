package ch.heigvd.res.mailrobot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ch.heigvd.res.mailrobot.config.ConfigurationManager;
import ch.heigvd.res.mailrobot.mail.Message;
import ch.heigvd.res.mailrobot.mail.Person;
import ch.heigvd.res.mailrobot.smtp.ISmtpClient;
import ch.heigvd.res.mailrobot.smtp.SmtpClient;

public class MailRobot {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ISmtpClient smtp = new SmtpClient("127.0.0.1", 2525);
		Message msg = new Message();
		msg.setFrom("jeremy.michaud@heig-vd.ch");
		msg.setBcc(new String[]{"bcc.aaa@heig-vd.ch"});
		msg.setTo(new String[]{"to.aaa@heig-vd.ch"});
		msg.setCc(new String[]{"cc.aaa@heig-vd.ch"});
		msg.setBody("coucou");
		msg.setSubject("blabla");
		smtp.sendMessage(msg);
	}

}
