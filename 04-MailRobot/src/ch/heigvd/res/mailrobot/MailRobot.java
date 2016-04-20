package ch.heigvd.res.mailrobot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ch.heigvd.res.mailrobot.config.ConfigurationManager;
import ch.heigvd.res.mailrobot.mail.Person;

public class MailRobot {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("coucou");
		ConfigurationManager conf = new ConfigurationManager("config");
		List<Person> pers = conf.getWitnessesToCC();
		for(Person p : pers)
			System.out.println(p.getFirstName() + " " + p.getLastName());
	}

}
