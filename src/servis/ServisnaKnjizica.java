package servis;

import java.util.ArrayList;

import automobil.Automobil;
import identifikacija.Identifikacija;

public class ServisnaKnjizica extends Identifikacija {
	private Automobil automobil;
	private ArrayList<Servis> servisi;
	
	public ServisnaKnjizica() {
		super();
		this.automobil = new Automobil();
		this.servisi = new ArrayList<Servis>();
	}

	public ServisnaKnjizica(String id, Automobil automobil, ArrayList<Servis> servisi) {
		super(id);
		this.automobil = automobil;
		this.servisi = servisi;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public ArrayList<Servis> getServisi() {
		return servisi;
	}

	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}
	
	public String toFile() {
		return id + "|" + automobil.getId() + "|" + servisi;
	}
	
	@Override
	public String toString() {
		return "ServisnaKnjizica [automobil=" + automobil + ", servisi=" + servisi + ", id=" + id
				+ "]";
	}
}
