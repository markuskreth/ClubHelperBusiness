package de.kreth.clubhelperbusiness.attendence;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import de.kreth.clubhelperbusiness.data.Attendance;
import de.kreth.clubhelperbusiness.data.Person;

public class AttendanceManager {

	private HashSet<Attendance> attendences;
	private Calendar date;

	public AttendanceManager(Calendar date) {
		this.attendences = new HashSet<Attendance>();
		this.date = date;
	}
	
	public Collection<Attendance> getAttendences() {
		return attendences;
	}

	/**
	 * Die {@link Person} ist am Konfigurierten Datum anwesend.
	 * @param p
	 */
	public void attendent(Person p) {
		Attendance a = new Attendance(p, date);
		attendences.add(a);
	}

	public void remove(Person person) {
		Attendance toRemove = null;
		for(Attendance a: attendences){
			if(a.getPerson().equals(person)){
				toRemove = a;
				break;
			}
		}
		if(toRemove != null) 
			attendences.remove(toRemove);
	}

	public void add(Attendance a) {
		if(a.getDate().equals(date))
			attendences.add(a);
		else 
			throw new IllegalArgumentException(Attendance.class.getSimpleName() + ".date passt nicht zum Datum dieses " + getClass().getSimpleName());
	}

}
