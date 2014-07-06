package de.kreth.clubhelperbusiness.data;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person extends PersistentDataObject {
	
	private String preName;
	private String surName;
	private Person father;
	private Person mother;
	private Calendar birthdate;

	public Person(Person toClone){
		super(toClone.getId());
		this.preName = toClone.preName;
		this.surName = toClone.surName;
		this.father = toClone.getFather();
		this.mother = toClone.getMother();
		this.birthdate = toClone.getBirthdate();
	}

	public Person(Person toClone, long id){
		super(id);
		this.preName = toClone.preName;
		this.surName = toClone.surName;
		this.father = toClone.getFather();
		this.mother = toClone.getMother();
		this.birthdate = (Calendar) toClone.getBirthdate().clone();
	}
	
	public Person(int id) {
		super(id);
	}
	
	public Person() {
		super();
	}
	
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	/**
	 * Delivers Clone of Father
	 * @return Clone of Father
	 */
	public Person getFather() {
		if(father != null)
			return new Person(father);
		return father;
	}
	
	/**
	 * @param father
	 */
	public void setFather(Person father) {
		this.father = new Person(father);
	}
	
	/**
	 * Delivers Clone of Mother
	 * @return	Clone of Mother
	 */
	public Person getMother() {
		if(mother != null)
			return new Person(mother);
		return mother;
	}
	
	public void setMother(Person mother) {
		this.mother = new Person(mother);
	}
	
	public Calendar getBirthdate() {
		return (Calendar) birthdate;
	}
	
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return preName + " " + surName;
	}
	
	public static Person createNew() {
		Person person = new Person();
		person.setBirthdate(getDefaultGeburtsDatum());
		person.setPreName("");
		person.setSurName("");
		return person;
	}
	
	public static Calendar getDefaultGeburtsDatum() {
		return new GregorianCalendar(1990,Calendar.JANUARY, 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
		result = prime * result + ((preName == null) ? 0 : preName.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
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
		Person other = (Person) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (!father.equals(other.father))
			return false;
		if (mother == null) {
			if (other.mother != null)
				return false;
		} else if (!mother.equals(other.mother))
			return false;
		if (preName == null) {
			if (other.preName != null)
				return false;
		} else if (!preName.equals(other.preName))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}
	
}
