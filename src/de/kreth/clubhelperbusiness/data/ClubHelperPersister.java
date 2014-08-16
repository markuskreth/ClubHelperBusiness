package de.kreth.clubhelperbusiness.data;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public interface ClubHelperPersister {

	public abstract void close();

	/**
	 * Niemals null - leere Person.
	 * @param personId
	 * @return
	 */
	public abstract Person getPersonById(long personId);

	public abstract List<Person> getAllPersons();

	/**
	 * liefert eine auswahl an Personen auf die die whereClause zutrifft.
	 * @param whereClause null oder where-bedingung wie in sql ohne "WHERE" 
	 * @return
	 */
	public abstract List<Person> getPersonsWhere(String whereClause);

	/**
	 * liefert eine auswahl an Personen auf die die whereClause zutrifft.
	 * @param whereClause null oder where-bedingung wie in sql ohne "WHERE"
	 * @return
	 */
	public abstract List<Attendance> getAttendancesWhere(String whereClause, Calendar forDate);

	public abstract void deleteNegativContacts();

	/**
	 * Niemals null - leeres Array
	 * @param person
	 * @return
	 */
	public abstract List<PersonContact> getContactsFor(Person person);

	/**
	 * Speichert die Person und liefert eine ggf. aktualisierte Person zurück.
	 * @param person
	 * @return
	 */
	public abstract Person storePerson(Person person);

	public abstract void deletePerson(Person data);

	/**
	 * Speichert die Daten und liefert die aktualisierten Daten zurück.
	 * @param data
	 * @return
	 */
	public abstract List<PersonContact> storePersonContacts(List<PersonContact> data);

	public abstract void deletePersonContacts(List<PersonContact> data);

	public abstract void storeAttendances(Calendar attendenceDate, Collection<Attendance> attendences);

}