package ch.heigvd.res.mailrobot.config;

import java.io.BufferedReader;
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
	BufferedReader messageFile;
	BufferedReader victimsFile;
	
	
	public ConfigurationManager(String configFolder, String messageFile, String victimsFile) throws FileNotFoundException, IOException {
		this.configFolder = configFolder;
		this.messageFile = new BufferedReader(new FileReader(configFolder + "/" + messageFile));
		this.victimsFile = new BufferedReader(new FileReader(configFolder + "/" + victimsFile));
		properties.load(new FileInputStream(configFolder + "/config.properties"));
	}
	
	
	@Override
	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();
		String line = "";
		String message = "";
		try {
			do{
				while((line = messageFile.readLine()) != null && !line.equals("==")){
					message += line + "\r\n";
				}
				messages.add(message);
				message = "";
			} while(line != null);
			messageFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return messages;
	}

	@Override
	public int getNumberOfGroups() {
		return Integer.valueOf(properties.getProperty("numberOfGroups"));
	}

	@Override
	public List<Person> getVictims() {
		List<Person> victims = new ArrayList<>();
		String address;
		try {
			while((address = victimsFile.readLine()) != null){
				victims.add(new Person(address));
			}
			victimsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return victims;
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
	public int getSmtpServerPort() {
		return Integer.valueOf(properties.getProperty("smtpServerPort"));
	}

}
