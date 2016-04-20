package ch.heigvd.res.mailrobot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ch.heigvd.res.mailrobot.config.ConfigurationManager;
import ch.heigvd.res.mailrobot.mail.Person;

public class MailRobot {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("coucou");
		ConfigurationManager conf = new ConfigurationManager("config", "messages.utf8", "victims.utf8");
		List<Person> pers = conf.getWitnessesToCC();
		for(Person p : pers)
			System.out.println(p.getFirstName() + " " + p.getLastName());
		List<String> mess = conf.getMessages();
		for(String s : mess)
			System.out.println(s);
		List<Person> vic = conf.getVictims();
		for(Person p : vic)
			System.out.println(p.getAdress());
	}

}
