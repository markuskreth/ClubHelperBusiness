package de.kreth.clubhelperbusiness.display;

import java.util.Calendar;
import java.util.List;

import de.kreth.clubhelperbusiness.data.Person;
import de.kreth.clubhelperbusiness.data.PersonContact;

public interface PersonDetailDisplay {

	void setPerson(Person person);
	void updatePersonContacts(List<PersonContact> contacts);
	void showBirthdayDialog(Calendar birthdate);
	void goBack();
	void askUserForDiscard();
	void setBirthdate(Calendar birthdate);
}
