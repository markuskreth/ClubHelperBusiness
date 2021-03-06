package de.kreth.clubhelperbusiness.data;

/**
 * Erweiterbares DatenObjekt.<br />
 * Mit interner Logik, zum Persistenz-Zustand {@link #isPersistent()}
 * @author markus
 *
 */
public abstract class PersistentDataObject {

	private static int nextUnpersistentId = -1;
	private long _id;

	/**
	 * For not persistent data objects, creation of new data to be stored
	 */
	public PersistentDataObject() {
		super();
		_id = nextUnpersistentId--;
	}
	
	/**
	 * Für persistente Objekte (laden existierender Datenobjekte)
	 * @param insertId	>0
	 */
	public PersistentDataObject(long insertId) {
		super();
		if(insertId <0)
			throw new IllegalArgumentException("Dieser Konstruktor ist nur für persistente Objekte - der Primary Key darf daher nicht <0 sein! War aber " + insertId);
		this._id = insertId;
	}

	public long getId() {
		return _id;
	}

	void setId(int id) {
		if(id <0)
			throw new IllegalArgumentException("der Primary Key darf nicht <0 sein! War aber " + id);
		this._id = id;
	}
	
	/**
	 * Ist Objekt in Db gespeichert?
	 * <br /> Keine Aussage, ob es auch Änderungen persistent hält.
	 * @return true, wenn in Db vorhanden.
	 */
	public boolean isPersistent(){
		return _id >= 0;
	}

	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = (int) (prime * result + _id);
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
//		PersistentDataObject other = (PersistentDataObject) obj;
//		if (_id != other._id)
//			return false;
		return true;
	}
	
}
