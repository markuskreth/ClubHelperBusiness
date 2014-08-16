package de.kreth.clubhelperbusiness.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.kreth.clubhelperbusiness.data.ClubHelperPersister;
import de.kreth.clubhelperbusiness.data.ContactType;
import de.kreth.clubhelperbusiness.data.Person;
import de.kreth.clubhelperbusiness.data.PersonContact;
import de.kreth.clubhelperbusiness.display.PersonDetailDisplay;

public class PersonDetailController {

	public static final String ARG_PERSON_ID = "person_id";
	public static final int BTN_DEL_CONTACT_ID = 456123;

	private ClubHelperPersister persister;
	
	private Person personOriginal = null;
	private Person person = null;


	private List<PersonContact> contactsOriginal;
	private List<PersonContact> contacts;

	private PersonDetailDisplay display;

	public PersonDetailController(PersonDetailDisplay display, ClubHelperPersister persister) {
		this.display = display;
		this.persister = persister;
		contactsOriginal = new ArrayList<PersonContact>();
		contacts = new ArrayList<PersonContact>();
	}
	
	public void init(long personId) {

		personOriginal = persister.getPersonById(personId);
		person = new Person(personOriginal);

		contactsOriginal = persister.getContactsFor(person);
		contacts = new ArrayList<PersonContact>(contactsOriginal);
		
		display.setPerson(person);
		display.updatePersonContacts(contacts);
	}

	public PersonContact findContactWithId(long contactId) {
		for (PersonContact cont : new ArrayList<PersonContact>(contacts)) {
			if (cont.getId() == contactId)
				return cont;
		}
		throw new IllegalArgumentException("Kein " + PersonContact.class.getSimpleName() + " gefunden in Liste " + contacts);
	}

	@Deprecated
	public void delete(long contactIdToDelete) {
		delete(findContactWithId(contactIdToDelete));
	}
	
	public void delete(PersonContact toDelete) {
		contacts.remove(toDelete);
		display.updatePersonContacts(contacts);
	}

	private void setNameOfPerson(String fullName) {
		String[] split = fullName.split(" ");
		person.setPreName(split[0]);

		if (split.length == 1)
			person.setSurName("");
		if (split.length == 2)
			person.setSurName(split[1]);
		else if (split.length > 2) {
			String surName = split[split.length - 1];
			person.setSurName(surName);
			StringBuilder preNames = new StringBuilder(fullName.length() - surName.length() + split.length + 1);
			for (int i = 0; i < split.length - 1; i++) {
				preNames.append(split[i]).append(" ");
			}
			person.setPreName(preNames.toString().trim());
		}
	}

	public boolean isDirty() {
		boolean personMustBeStored = personOriginal.getBirthdate().compareTo(personOriginal.getBirthdate()) != 0;
		personMustBeStored |= nameHasChanged();
		personMustBeStored |= hasContactsChanged();
		return personMustBeStored;
	}

	private boolean hasContactsChanged() {
		boolean retval = false;
		List<PersonContact> editedData = new ArrayList<PersonContact>(contacts);

		if (contactsOriginal.size() != editedData.size()) {
			retval = true;
		} else {
			for (int i = 0; i < contactsOriginal.size(); i++) {
				if (!contactsOriginal.get(i).equals(editedData.get(i))) {
					retval = true;
					break;
				}
			}
		}
		return retval;
	}

	private boolean nameHasChanged() {
		boolean namesAreSame = person.getPreName().contentEquals(personOriginal.getPreName()) && person.getSurName().contentEquals(personOriginal.getSurName());
		return !namesAreSame;
	}

	public void changeNameOfPerson(String fullName) {
		setNameOfPerson(fullName);		
	}

	public void showBirthdayDialog() {
		display.showBirthdayDialog(person.getBirthdate());
	}

	public void goBack() {

		boolean personWasEdited = isDirty();
		if(personWasEdited)
			display.askUserForDiscard();
		else
			display.goBack();
	}
	
	public void goBackAndStore() {

		boolean wasPersistend = person.isPersistent();
		person = persister.storePerson(person);
		personOriginal = person;

		if (!wasPersistend) {
			List<PersonContact> storeContacts = new ArrayList<PersonContact>();

			for (PersonContact con : contacts) {
				storeContacts.add(new PersonContact(person.getId(), con.getType(), con.getValue()));
			}

			storeContacts = persister.storePersonContacts(storeContacts);
			contactsOriginal = storeContacts;
			contacts = new ArrayList<PersonContact>(storeContacts);
		} else {
			contactsOriginal = persister.storePersonContacts(contacts);
			contacts = new ArrayList<PersonContact>(contactsOriginal);
		}

		display.goBack();
	}

	public void setBirthdate(int year, int monthOfYear, int dayOfMonth) {

		person.getBirthdate().set(Calendar.YEAR, year);
		person.getBirthdate().set(Calendar.MONTH, monthOfYear);
		person.getBirthdate().set(Calendar.DAY_OF_MONTH, dayOfMonth);
		display.setBirthdate(person.getBirthdate());
	}

	public void addPersonContact(ContactType selectedType, String text) {

		PersonContact personContact = new PersonContact(person.getId(), selectedType, text);
		contacts.add(personContact);
	}

}
