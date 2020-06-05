package servis;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import automobil.Automobil;
import automobil.Deo;
import fajlovi.Entiteti;
import identifikacija.Identifikacija;
import osobe.Serviser;

public class Servis extends Identifikacija {
	private Automobil servisiraniAuto;
	private Serviser serviser;
	private Status status;
	private String opis;
	private ArrayList<Deo> delovi;
	private GregorianCalendar termin;
	
	public Servis() {
		super();
		this.servisiraniAuto = new Automobil();
		this.serviser = new Serviser();
		this.delovi = new ArrayList<Deo>();
		this.status = Status.ZAKAZAN;
		this.opis = "";
		this.termin = new GregorianCalendar();
	}

	public Servis(String id, Boolean izbrisan, Automobil servisiraniAuto, Serviser serviser, ArrayList<Deo> delovi, GregorianCalendar termin, Status status, String opis) {
		super(id, izbrisan);
		this.servisiraniAuto = servisiraniAuto;
		this.serviser = serviser;
		this.delovi = delovi;
		this.status = status;
		this.opis = opis;
		this.termin = termin;
	}

	public Automobil getServisiraniAuto() {
		return servisiraniAuto;
	}

	public void setServisiraniAuto(Automobil servisiraniAuto) {
		this.servisiraniAuto = servisiraniAuto;
	}

	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public ArrayList<Deo> getDelovi() {
		return delovi;
	}

	public void setDelovi(ArrayList<Deo> delovi) {
		this.delovi = delovi;
	}
	
	public GregorianCalendar getTermin() {
		return termin;
	}

	public void setTermin(GregorianCalendar termin) {
		this.termin = termin;
	}

	public String toFile() {
		return id + "|" + servisiraniAuto.getId() + "|" + serviser.getId() + "|" + delovi + "|" + Entiteti.terminFormat.format(termin.getTime()) + "|" + status + "|" + opis;
	}

	@Override
	public String toString() {
		return "Servis [servisiraniAuto=" + servisiraniAuto + ", serviser=" + serviser + ", status=" + status
				+ ", opis=" + opis + ", delovi=" + delovi + ", termin=" + termin + ", id=" + id + ", izbrisan="
				+ izbrisan + "]";
	}

	
	
	

}
