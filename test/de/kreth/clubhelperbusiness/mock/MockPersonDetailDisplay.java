package de.kreth.clubhelperbusiness.mock;

import java.util.Calendar;
import java.util.List;

import de.kreth.clubhelperbusiness.data.Person;
import de.kreth.clubhelperbusiness.data.PersonContact;
import de.kreth.clubhelperbusiness.display.PersonDetailDisplay;

public class MockPersonDetailDisplay implements PersonDetailDisplay {

	public Person currentPerson = null;
	public List<PersonContact> currentContacts;
	public Calendar birtsdateInDialog;
	public int goBackCount = 0;
	public int userAskForDiscardCount = 0;
	
	@Override
	public void setPerson(Person person) {
		this.currentPerson = person;
	}

	@Override
	public void updatePersonContacts(List<PersonContact> contacts) {
		this.currentContacts = contacts;
	}

	@Override
	public void showBirthdayDialog(Calendar birthdate) {
		this.birtsdateInDialog = birthdate;
	}

	@Override
	public void goBack() {
		goBackCount++;
	}

	@Override
	public void askUserForDiscard() {
		userAskForDiscardCount++;
	}

	@Override
	public void setBirthdate(Calendar birthdate) {
		currentPerson.setBirthdate(birthdate);
	}

}
