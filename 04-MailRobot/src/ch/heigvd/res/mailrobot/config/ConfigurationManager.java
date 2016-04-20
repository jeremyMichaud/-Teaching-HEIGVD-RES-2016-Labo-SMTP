package ch.heigvd.res.mailrobot.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import ch.heigvd.res.mailrobot.mail.Person;

public class ConfigurationManager implements IConfigurationManager {
	private Properties properties  = new Properties();
	private String configFolder;
	
	public ConfigurationManager(String configFolder) throws FileNotFoundException, IOException {
		this.configFolder = configFolder;
		properties.load(new FileInputStream(configFolder + "/config.properties"));
	}
	
	@Override
	public List<String> getMessages() {
		return null;
	}

	@Override
	public int getNumberOfGroups() {
		return Integer.valueOf(properties.getProperty("numberOfGroups"));
	}

	@Override
	public List<Person> getVictims() {
		return null;
	}

	@Override
	public List<Person> getWitnessesToCC() {
		String witnesses = properties.getProperty("witnessesToCC");
		List<String> addresses = new ArrayList<String>(Arrays.asList(witnesses.split(",")));
		List<Person> witnessesToCC = new ArrayList<>();
		for(String address : addresses)
			witnessesToCC.add(new Person(address));
		return witnessesToCC;
	}

	@Override
	public String getSmtpServerAddress() {
		return properties.getProperty("smtpServerAddress");
	}

	@Override
	public String getSmtpServerPort() {
		return properties.getProperty("smtpServerPort");
	}

}
