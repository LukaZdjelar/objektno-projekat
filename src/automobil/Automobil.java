package automobil;

import osobe.Musterija;

public class Automobil extends MarkaIModel {
	private Musterija vlasnik;
	private String godiste;
	private int zapremina;
	private int snaga;
	private Gorivo gorivo;
	
	public Automobil() {
		super();
		this.vlasnik = new Musterija();
		this.godiste = "";
		this.zapremina = 0;
		this.snaga = 0;
		this.gorivo = Gorivo.BENZIN;
				
	} 
	
	

	public Automobil(String id, Marka marka, Model model, Musterija vlasnik, String godiste, int zapremina, int snaga,
			Gorivo gorivo) {
		super(id, marka, model);
		this.vlasnik = vlasnik;
		this.godiste = godiste;
		this.zapremina = zapremina;
		this.snaga = snaga;
		this.gorivo = gorivo;
	}

	public Musterija getVlasnik() { //MUSTERIJA
		return vlasnik;
	}

	public void setVlasnik(Musterija vlasnik) {
		this.vlasnik = vlasnik;
	}
	
	public String getGodiste() {
		return godiste;
	}

	public void setGodiste(String godiste) {
		this.godiste = godiste;
	}

	public int getZapremina() {
		return zapremina;
	}

	public void setZapremina(int zapremina) {
		this.zapremina = zapremina;
	}

	public int getSnaga() {
		return snaga;
	}

	public void setSnaga(int snaga) {
		this.snaga = snaga;
	}

	public Gorivo getGorivo() {
		return gorivo;
	}

	public void setGorivo(Gorivo gorivo) {
		this.gorivo = gorivo;
	}
	
	public String toFile() {
		return id + "|" + getMarka() + "|" + getModel() + "|" + godiste + "|" + zapremina + "|" + snaga + "|" + gorivo + "|" + vlasnik.getId();
	}

	@Override
	public String toString() {
		return "Automobil [vlasnik=" + vlasnik + ", godiste=" + godiste + ", zapremina=" + zapremina + ", snaga="
				+ snaga + ", gorivo=" + gorivo + ", marka=" + marka + ", model=" + model + ", id=" + id + "]";
	}
}
