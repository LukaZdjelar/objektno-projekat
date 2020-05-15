package osobe;

public class Serviser extends Osoblje {
	private Specijalizacija specijalizacija;
	
	public Serviser() {
		super();
		this.specijalizacija = Specijalizacija.LIMAR;
	}

	public Serviser(String id, String ime, String prezime, String jmbg, Pol pol, String adresa, String broj_tel,
			String kor_ime, String lozinka, double plata, Specijalizacija specijalizacija) {
		super(id, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka, plata);
		this.specijalizacija = specijalizacija;
	}

	public Specijalizacija getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(Specijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + adresa + "|" + broj_tel + "|" + plata + "|" + getSpecijalizacija() + "|" +  kor_ime + "|" + lozinka;
	}
	
	@Override
	public String toString() {
		return "Serviser [specijalizacija=" + specijalizacija + ", plata=" + plata + ", ime=" + ime + ", prezime="
				+ prezime + ", jmbg=" + jmbg + ", pol=" + pol + ", adresa=" + adresa + ", broj_tel=" + broj_tel
				+ ", kor_ime=" + kor_ime + ", lozinka=" + lozinka + ", id=" + id + "]";
	}
	
	
}
