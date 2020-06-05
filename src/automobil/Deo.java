package automobil;

public class Deo extends MarkaIModel {
	private String naziv;
	private double cena;
	
	public Deo(){
		super();
		this.naziv = "";
		this.cena = 0;
	}
	public Deo(String id, Boolean izbrisan, Marka marka, Model model, String naziv, double cena) {
		super(id, izbrisan, marka, model);
		this.naziv = naziv;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	@Override
	public String toString() {
		return "Deo [naziv=" + naziv + ", cena=" + cena + ", marka=" + marka + ", model=" + model + ", id=" + id
				+ ", izbrisan=" + izbrisan + "]";
	}

	public String toFile() {
		return getId() + "|" + naziv + "|" + getMarka() + "|" + getModel() + "|" + cena;
	}
}
