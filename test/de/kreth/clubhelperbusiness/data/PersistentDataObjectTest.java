package de.kreth.clubhelperbusiness.data;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class PersistentDataObjectTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetId() {
		TestDataObject data = new TestDataObject();
		assertTrue(data.getId()<0);
		data = new TestDataObject(5);
		assertEquals(5, data.getId());
		
	}

	public void testPersonId () {
		Person p = new Person();
		String preName = "TestPreName";
		String sureName = "TestSureName";
		Calendar birthdate = new GregorianCalendar();
		p.setPreName(preName);
		p.setSurName(sureName);
		p.setBirthdate(birthdate);

		assertEquals(preName, p.getPreName());
		assertEquals(sureName, p.getSurName());
		assertEquals(birthdate, p.getBirthdate());
		assertTrue(p.getId()<0);
		
		Person clone = new Person(p, 5);

		assertEquals(preName, p.getPreName());
		assertEquals(sureName, p.getSurName());
		assertEquals(birthdate, p.getBirthdate());
		assertEquals(5, clone.getId());
	}
	
	private class TestDataObject extends PersistentDataObject {

		public TestDataObject() {
			super();
		}

		public TestDataObject(long insertId) {
			super(insertId);
		}
		
	}
}
