package osobe;

public abstract class Osoblje extends Osoba {
	protected double plata;
	
	public Osoblje() {
		super();
		this.plata = 0;
	}

	public Osoblje(String id, String ime, String prezime, String jmbg, Pol pol, String adresa, String broj_tel,
			String kor_ime, String lozinka, double plata) {
		super(id, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka);
		this.plata = plata;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	@Override
	public String toString() {
		return "Osoblje [plata=" + plata + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol
				+ ", adresa=" + adresa + ", broj_tel=" + broj_tel + ", kor_ime=" + kor_ime + ", lozinka=" + lozinka
				+ ", id=" + id + "]";
	}
	
	
	
}
