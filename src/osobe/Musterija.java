package osobe;

public class Musterija extends Osoba {
	private int brojBodova;
	
	public Musterija() {
		super();
		this.brojBodova = 0;
	}

	public Musterija(String id, Boolean izbrisan, String ime, String prezime, String jmbg, Pol pol, String adresa, String broj_tel,
			String kor_ime, String lozinka, int brojBodova) {
		super(id, izbrisan, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka);
		this.brojBodova = brojBodova;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}
	
	public String toFile() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + pol + "|" + adresa + "|" + broj_tel + "|" + brojBodova + "|" + kor_ime + "|" + lozinka;
	}

	@Override
	public String toString() {
		return "Musterija [brojBodova=" + brojBodova + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg
				+ ", pol=" + pol + ", adresa=" + adresa + ", broj_tel=" + broj_tel + ", kor_ime=" + kor_ime
				+ ", lozinka=" + lozinka + ", id=" + id + ", izbrisan=" + izbrisan + "]";
	}
	
	
	
}
