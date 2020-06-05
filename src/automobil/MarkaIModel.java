package automobil;

import identifikacija.Identifikacija;

public abstract class MarkaIModel extends Identifikacija {
	protected Marka marka;
	protected Model model;
	
	public MarkaIModel() {
		super();
		this.marka = Marka.CITROEN;
		this.model = Model.C3;
	}

	public MarkaIModel(String id, Boolean izbrisan, Marka marka, Model model) {
		super(id, izbrisan);
		this.marka = marka;
		this.model = model;
	}

	public Marka getMarka() {
		return marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "MarkaIModel [marka=" + marka + ", model=" + model + ", id=" + id + "]";
	}
	
	
}
