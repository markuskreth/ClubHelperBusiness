package de.kreth.clubhelperbusiness.data;

import java.util.Calendar;

/**
 * Sybolisiert eine Anwesenheit einer Person an einem bestimmten Datum. 
 * @author markus
 *
 */
public class Attendance extends PersistentDataObject {

	private Person person;
	private Calendar date;

	@Override
	public String toString() {
		return (date != null ? date.getTime() : "null") + " - " + person;
	}
	
	public Attendance(long insertId, Person person, Calendar date) {
		super(insertId);
		this.person = person;
		this.date = date;
	}

	public Attendance(Person person, Calendar date) {
		super();
		this.person = person;
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	/**
	 * Datum der Anwesenheit. null bedeutet nicht anwesend!
	 * @return
	 */
	public Calendar getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attendance other = (Attendance) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

}
