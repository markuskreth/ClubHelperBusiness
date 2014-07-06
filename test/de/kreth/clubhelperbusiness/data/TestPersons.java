package de.kreth.clubhelperbusiness.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.kreth.clubhelperbusiness.data.Person;

public class TestPersons {
	
	private List<Person> testPersonen;
	
	/**
	 * Wenn persistente Personen gewünscht sind ist true zu übergeben, wenn egal oder nicht peristent = false
	 * @param persistent
	 */
	public TestPersons(boolean persistent) {
		testPersonen = new ArrayList<Person>();
		fillPersonen(persistent);
	}
	
	private void fillPersonen(boolean persistent) {
		int id = 1;
		Person p;
		
		if(persistent){
			p = new Person(id++);
		} else
			p = new Person();
		
		p.setSurName("Test1");
		p.setPreName("Test");
		p.setBirthdate(new GregorianCalendar(1973, Calendar.AUGUST , 21));
		testPersonen.add(p);
		
		if(persistent){
			p = new Person(id++);
		} else
			p = new Person();
		
		p.setSurName("Test2");
		p.setPreName("Test");
		p.setBirthdate(new GregorianCalendar(1973, Calendar.AUGUST , 21));
		testPersonen.add(p);

		if(persistent){
			p = new Person(id++);
		} else
			p = new Person();
		
		p.setSurName("Test3");
		p.setPreName("Test");
		p.setBirthdate(new GregorianCalendar(1973, Calendar.AUGUST , 21));
		testPersonen.add(p);

		if(persistent){
			p = new Person(id++);
		} else
			p = new Person();
		
		p.setSurName("Test4");
		p.setPreName("Test");
		p.setBirthdate(new GregorianCalendar(1973, Calendar.AUGUST , 21));
		testPersonen.add(p);
		
	}

	public List<Person> getTestPersonen() {
		return testPersonen;
	};
}
