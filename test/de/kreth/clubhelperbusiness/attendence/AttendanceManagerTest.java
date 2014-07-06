package de.kreth.clubhelperbusiness.attendence;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;
import de.kreth.clubhelperbusiness.data.Attendance;
import de.kreth.clubhelperbusiness.data.Person;
import de.kreth.clubhelperbusiness.data.TestPersons;

public class AttendanceManagerTest extends TestCase {

	private TestPersons testPersons;

	protected void setUp() throws Exception {
		super.setUp();
		testPersons = new TestPersons(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAttendanceEquals() {

		Calendar date = new GregorianCalendar(2014, Calendar.JANUARY, 21);
		Person person = testPersons.getTestPersonen().get(1);
		Attendance a1 = new Attendance(person, date);
		date = new GregorianCalendar(2014, Calendar.JANUARY, 21);
		Attendance a2 = new Attendance(person, date);
		
		assertTrue(a1.equals(a2));
		assertEquals(a1, a2);
	}
	
	
	public void testWithNotAttendenceAndNoChange() {
		Calendar date = new GregorianCalendar(2014, Calendar.JANUARY, 21);		
		AttendanceManager manager = new AttendanceManager(date);
		Collection<Attendance> attendences = manager.getAttendences();
		assertNotNull(attendences);
		assertTrue(attendences.isEmpty());
	}

	public void testAllAttendent() {
		Calendar date = new GregorianCalendar(2014, Calendar.JANUARY, 21);
		List<Person> testPersonen = testPersons.getTestPersonen();
		
		AttendanceManager manager = new AttendanceManager(date);
		Collection<Attendance> attendences = manager.getAttendences();
		
		for(Person p: testPersonen){
			manager.attendent(p);
		}
		
		assertNotNull(attendences);
		assertEquals(testPersonen.size(), attendences.size());
	}

	public void testIgnoreDoubles() {
		Calendar date = new GregorianCalendar(2014, Calendar.JANUARY, 21);
		List<Person> testPersonen = testPersons.getTestPersonen();
		
		AttendanceManager manager = new AttendanceManager(date);
		Collection<Attendance> attendences = manager.getAttendences();
		
		for(Person p: testPersonen){
			manager.attendent(p);
		}

		manager.attendent(testPersonen.get(0));
		manager.attendent(testPersonen.get(1));
		manager.attendent(testPersonen.get(1));
		
		assertNotNull(attendences);
		assertEquals(testPersonen.size(), attendences.size());
	}
	
	public void testRemoveHalfAttendences() {

		Calendar date = new GregorianCalendar(2014, Calendar.JANUARY, 21);
		List<Person> testPersonen = testPersons.getTestPersonen();
		
		AttendanceManager manager = new AttendanceManager(date);
		
		for(Person p: testPersonen){
			Attendance a = new Attendance(p.getId(),p, date);
			manager.add(a);
		}

		manager.remove(testPersonen.get(2));
		manager.remove(testPersonen.get(3));
		
		Collection<Attendance> attendences = manager.getAttendences();
		
		assertNotNull(attendences);
		assertEquals(testPersonen.size()-2, attendences.size());
	}
}
