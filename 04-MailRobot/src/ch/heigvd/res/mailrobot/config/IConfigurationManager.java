package ch.heigvd.res.mailrobot.config;

import java.util.List;

import ch.heigvd.res.mailrobot.mail.*;

public interface IConfigurationManager {
	public List<String> getMessages();
	public int getNumberOfGroups();
	public List<Person> getVictims();
	public List<Person> getWitnessesToCC();
	public String getSmtpServerAddress();
	public String getSmtpServerPort();
}
