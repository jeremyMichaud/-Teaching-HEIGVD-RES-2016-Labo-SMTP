package ch.heigvd.res.mailrobot.prank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.heigvd.res.mailrobot.config.IConfigurationManager;
import ch.heigvd.res.mailrobot.mail.Group;
import ch.heigvd.res.mailrobot.mail.Person;

public class PrankGenerator {
	private IConfigurationManager configurationManager;
	
	public PrankGenerator(IConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}
	
	public List<Prank> generatePranks(){
		List<Prank> pranks = new ArrayList<>();
		List<String> messages = configurationManager.getMessages();
		int messageIndex = 0;

		int numberOfGroups  = configurationManager.getNumberOfGroups();
		int numberOfVictims = configurationManager.getVictims().size();
		
		if(numberOfVictims / numberOfGroups < 3){
			numberOfGroups = numberOfVictims / 3;
		}
		
		List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);
		for(Group group : groups){
			Prank prank = new Prank();
			List<Person> victims = group.getMembers();
			Collections.shuffle(victims);
			Person sender = victims.remove(0);
			prank.setVictimSender(sender);
			prank.addVictimRecipients(victims);
			
			prank.addWitnessRecipients(configurationManager.getWitnessesToCC());
			
			String message = messages.get(messageIndex);
			messageIndex = (messageIndex + 1) % messages.size();
			prank.setMessage(message);
			
			pranks.add(prank);
		}
		return pranks;
	}
	
	public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
		List<Person> availaibleVictims = new ArrayList<>(victims);
		Collections.shuffle(availaibleVictims);
		List<Group> groups = new ArrayList<>();
		for(int i = 0; i < numberOfGroups; i++){
			Group group = new Group();
			groups.add(group);
		}
		int turn = 0;
		Group targetGroup;
		while(availaibleVictims.size() > 0){
			targetGroup = groups.get(turn);
			turn = (turn + 1) % groups.size();
			Person victim = availaibleVictims.remove(0);
			targetGroup.addMember(victim);
		}
		return groups;
	}
}
