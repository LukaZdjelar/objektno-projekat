package identifikacija;

public abstract class Identifikacija {
	protected  String id;
	
	public Identifikacija() {
		this.id = "";
	}

	public Identifikacija(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Identifikacija [id=" + id + "]";
	}
	
	
}
