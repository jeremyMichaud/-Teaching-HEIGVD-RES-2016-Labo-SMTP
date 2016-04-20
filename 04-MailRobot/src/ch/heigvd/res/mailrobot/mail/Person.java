package ch.heigvd.res.mailrobot.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String firstName;
	private String lastName;
	private final String address;
	
	public Person(String firstname, String lastName, String address) {
		this.firstName = firstname;
		this.lastName = lastName;
		this.address = address;
	}
	
	public Person(String address){
		this.address = address;
		Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
		Matcher matcher = pattern.matcher(address);
		if(matcher.find()){
			this.firstName = matcher.group(1);
			firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
			this.lastName = matcher.group(2);
			lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
		}
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getAdress(){
		return address;
	}
}
