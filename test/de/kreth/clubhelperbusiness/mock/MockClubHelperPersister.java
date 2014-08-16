package de.kreth.clubhelperbusiness.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.kreth.clubhelperbusiness.data.Attendance;
import de.kreth.clubhelperbusiness.data.ClubHelperPersister;
import de.kreth.clubhelperbusiness.data.Person;
import de.kreth.clubhelperbusiness.data.PersonContact;

public class MockClubHelperPersister implements ClubHelperPersister {

	public Map<Long, Person> idToPersonMap;
	public Map<Long, List<PersonContact>> personIdToPersonContactMap;
	public Person storedPerson;
	public List<PersonContact> storedContacts;
	
	public MockClubHelperPersister() {
		idToPersonMap = new HashMap<Long, Person>();
		personIdToPersonContactMap = new HashMap<Long, List<PersonContact>>();
	}
	
	@Override
	public void close() {
		idToPersonMap = null;
		personIdToPersonContactMap = null;
	}

	@Override
	public Person getPersonById(long personId) {
		return idToPersonMap.get(personId);
	}

	@Override
	public List<Person> getAllPersons() {
		return new ArrayList<Person>(idToPersonMap.values());
	}

	@Override
	public List<Person> getPersonsWhere(String whereClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attendance> getAttendancesWhere(String whereClause, Calendar forDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNegativContacts() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonContact> getContactsFor(Person person) {
		return personIdToPersonContactMap.get(person.getId());
	}

	@Override
	public Person storePerson(Person person) {
		this.storedPerson = person;
		return this.storedPerson;
	}

	@Override
	public void deletePerson(Person data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonContact> storePersonContacts(List<PersonContact> data) {
		this.storedContacts = data;
		return data;
	}

	@Override
	public void deletePersonContacts(List<PersonContact> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeAttendances(Calendar attendenceDate, Collection<Attendance> attendences) {
		// TODO Auto-generated method stub

	}

}
