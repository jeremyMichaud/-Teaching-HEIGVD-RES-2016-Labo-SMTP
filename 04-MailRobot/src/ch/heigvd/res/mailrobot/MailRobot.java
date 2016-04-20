package ch.heigvd.res.mailrobot;

import java.io.IOException;
import java.util.List;

import ch.heigvd.res.mailrobot.config.ConfigurationManager;
import ch.heigvd.res.mailrobot.config.IConfigurationManager;
import ch.heigvd.res.mailrobot.prank.Prank;
import ch.heigvd.res.mailrobot.prank.PrankGenerator;
import ch.heigvd.res.mailrobot.smtp.ISmtpClient;
import ch.heigvd.res.mailrobot.smtp.SmtpClient;

public class MailRobot {

	public static void main(String[] args) throws IOException {
		IConfigurationManager config = new ConfigurationManager("config", "messages.utf8", "victims.utf8");
		List<Prank> pranks = new PrankGenerator(config).generatePranks();
		ISmtpClient smtpClient = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort());
		for(Prank prank : pranks)
			smtpClient.sendMessage(prank.generateMailMessage());
	}

}
