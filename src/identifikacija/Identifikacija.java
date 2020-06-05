package identifikacija;

public abstract class Identifikacija {
	protected String id;
	protected Boolean izbrisan;
	
	public Identifikacija() {
		this.id = "";
		this.izbrisan = false;
	}

	public Identifikacija(String id, Boolean izbrisan) {
		super();
		this.id = id;
		this.izbrisan = izbrisan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(Boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	@Override
	public String toString() {
		return "Identifikacija [id=" + id + ", izbrisan=" + izbrisan + "]";
	}

}
