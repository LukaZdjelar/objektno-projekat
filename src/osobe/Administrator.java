package osobe;

public class Administrator extends Osoblje {
	
	public Administrator() {
		super();
	}
	
	public Administrator(String id, String ime, String prezime, String jmbg, Pol pol, String adresa, String broj_tel,
			String kor_ime, String lozinka, double plata) {
		super(id, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka, plata);
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + adresa + "|" + broj_tel + "|" + plata + "|" + kor_ime + "|" + lozinka;
	}
	@Override
	public String toString() {
		return "Administrator [plata=" + plata + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol="
				+ pol + ", adresa=" + adresa + ", broj_tel=" + broj_tel + ", kor_ime=" + kor_ime + ", lozinka="
				+ lozinka + ", id=" + id + "]";
	}
	
	
}
