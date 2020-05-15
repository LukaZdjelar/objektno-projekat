package osobe;

import identifikacija.Identifikacija;
import osobe.Pol;

public abstract class Osoba extends Identifikacija {
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected Pol pol;
	protected String adresa;
	protected String broj_tel;
	protected String kor_ime;
	protected String lozinka;
	
	public Osoba() {
		super();
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.pol = Pol.MUSKO;
		this.adresa = "";
		this.broj_tel = "";
		this.kor_ime = "";
		this.lozinka = "";
	}

	public Osoba(String id, String ime, String prezime, String jmbg, Pol pol, String adresa, String broj_tel,
			String kor_ime, String lozinka) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.broj_tel = broj_tel;
		this.kor_ime = kor_ime;
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBroj_tel() {
		return broj_tel;
	}

	public void setBroj_tel(String broj_tel) {
		this.broj_tel = broj_tel;
	}

	public String getKor_ime() {
		return kor_ime;
	}

	public void setKor_ime(String kor_ime) {
		this.kor_ime = kor_ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public String toString() {
		return "Osoba [ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol + ", adresa=" + adresa
				+ ", broj_tel=" + broj_tel + ", kor_ime=" + kor_ime + ", lozinka=" + lozinka + ", id=" + id + "]";
	}
	
	
}
