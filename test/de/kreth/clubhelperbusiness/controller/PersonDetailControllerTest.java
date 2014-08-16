package de.kreth.clubhelperbusiness.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;
import de.kreth.clubhelperbusiness.data.ContactType;
import de.kreth.clubhelperbusiness.data.Person;
import de.kreth.clubhelperbusiness.data.PersonContact;
import de.kreth.clubhelperbusiness.mock.MockClubHelperPersister;
import de.kreth.clubhelperbusiness.mock.MockPersonDetailDisplay;

public class PersonDetailControllerTest extends TestCase {

	private PersonDetailController controller;
	private MockPersonDetailDisplay display;
	private MockClubHelperPersister persister;

	private String prename = "PRENAME";
	private String surName = "SURNAME";
	private String mobileValue = "555-123549";
	private String emailValue = "test@email.de";
	private Calendar birthday = new GregorianCalendar(1973, Calendar.AUGUST, 21, 8, 0, 0);
	
	protected void setUp() throws Exception {
		super.setUp();
		display = new MockPersonDetailDisplay();
		persister = new MockClubHelperPersister();
		controller = new PersonDetailController(display, persister);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadAndShowPerson() {
		Person person = new Person(1);
		person.setPreName(prename);
		person.setSurName(surName);
		person.setBirthdate(birthday);
		
		PersonContact pers1Mobile = new PersonContact(1, person.getId(), ContactType.MOBILE, mobileValue);
		List<PersonContact> contacts = new ArrayList<PersonContact>();
		contacts.add(pers1Mobile);
		
		persister.idToPersonMap.put(person.getId(), person);
		persister.personIdToPersonContactMap.put(person.getId(), contacts);
		
		controller.init(1);
		
		assertFalse(controller.isDirty());
		assertEquals(person, display.currentPerson);
		assertEquals(1, display.currentContacts.size());
		controller.goBack();

		assertEquals(0, display.userAskForDiscardCount);
		assertNull(persister.storedPerson);
	}
	
	public void testChangeName() {

		Person person = new Person(1);
		person.setPreName(prename);
		person.setSurName(surName);
		person.setBirthdate(birthday);
		
		PersonContact pers1Mobile = new PersonContact(1, person.getId(), ContactType.MOBILE, mobileValue);
		List<PersonContact> contacts = new ArrayList<PersonContact>();
		contacts.add(pers1Mobile);
		
		persister.idToPersonMap.put(person.getId(), person);
		persister.personIdToPersonContactMap.put(person.getId(), contacts);
		
		controller.init(1);
		
		String fullName = "Markus Kreth";
		controller.changeNameOfPerson(fullName);

		assertTrue(controller.isDirty());
		controller.goBack();
		assertEquals(1, display.userAskForDiscardCount);
		assertNull(persister.storedPerson);
		controller.goBackAndStore();
		assertEquals("Markus", persister.storedPerson.getPreName());
		assertEquals("Kreth", persister.storedPerson.getSurName());
	}
	
	public void testAddContact() {

		Person person = new Person(1);
		person.setPreName(prename);
		person.setSurName(surName);
		person.setBirthdate(birthday);
		
		PersonContact pers1Mobile = new PersonContact(1, person.getId(), ContactType.MOBILE, mobileValue);
		List<PersonContact> contacts = new ArrayList<PersonContact>();
		contacts.add(pers1Mobile);
		
		persister.idToPersonMap.put(person.getId(), person);
		persister.personIdToPersonContactMap.put(person.getId(), contacts);
		
		controller.init(1);
		
		controller.addPersonContact(ContactType.EMAIL, emailValue);

		assertTrue(controller.isDirty());
		
		controller.goBack();
		assertEquals(1, display.userAskForDiscardCount);
		controller.goBackAndStore();
		assertEquals(person, persister.storedPerson);
		
		assertEquals(2, persister.storedContacts.size());
		
		for(PersonContact contact : persister.storedContacts) {
			if(contact.getType() == ContactType.MOBILE) {
				assertEquals(1, contact.getId());
				assertEquals(mobileValue.replaceAll("[-/+]", ""), contact.getValue());
			} else if(contact.getType() == ContactType.EMAIL) {
				assertTrue(contact.getId()<0);
				assertEquals(emailValue, contact.getValue());
			} else
				fail("Contact nicht erwartet: " + contact);
		}
	}
	
	public void testFindContact() {

		Person person = new Person(1);
		person.setPreName(prename);
		person.setSurName(surName);
		person.setBirthdate(birthday);

		PersonContact pers1Mobile = new PersonContact(1, person.getId(), ContactType.MOBILE, mobileValue);
		PersonContact pers1Email = new PersonContact(2, person.getId(), ContactType.EMAIL, mobileValue);
		List<PersonContact> contacts = new ArrayList<PersonContact>();
		contacts.add(pers1Mobile);
		contacts.add(pers1Email);
		
		persister.idToPersonMap.put(person.getId(), person);
		persister.personIdToPersonContactMap.put(person.getId(), contacts);
		
		controller.init(1);
		
		PersonContact emailContact = controller.findContactWithId(2);
		PersonContact mobileContact = controller.findContactWithId(1);
		assertEquals(pers1Email, emailContact);
		assertEquals(pers1Mobile, mobileContact);
		
	}
	
	public void testDeleteContact() {

		Person person = new Person(1);
		person.setPreName(prename);
		person.setSurName(surName);
		person.setBirthdate(birthday);

		PersonContact pers1Mobile = new PersonContact(1, person.getId(), ContactType.MOBILE, mobileValue);
		PersonContact pers1Email = new PersonContact(2, person.getId(), ContactType.EMAIL, mobileValue);
		List<PersonContact> contacts = new ArrayList<PersonContact>();
		contacts.add(pers1Mobile);
		contacts.add(pers1Email);
		
		persister.idToPersonMap.put(person.getId(), person);
		persister.personIdToPersonContactMap.put(person.getId(), contacts);
		
		controller.init(1);
		controller.delete(pers1Email);
		assertEquals(1, display.currentContacts.size());
		controller.goBackAndStore();
		assertEquals(1, persister.personIdToPersonContactMap.size());
		
	}
}
