package rad_sa_entitetima;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import automobil.Automobil;
import automobil.Deo;
import automobil.Gorivo;
import automobil.Marka;
import automobil.Model;
import fajlovi.RadSaFajlovima;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Pol;
import osobe.Serviser;
import osobe.Specijalizacija;
import servis.Servis;
import servis.ServisnaKnjizica;
import servis.Status;

public class Izmene {
	public static void IzmenaMusterije(Musterija zaMenjanje, String ime, String prezime, String jmbg, 
										Pol pol,  String adresa, String broj_tel,
										String kor_ime, String lozinka, int brojBodova) {
		
		for (Musterija m : Liste.musterije) {
			if(m.getId().equals(zaMenjanje.getId())) {
				
				if(ime != m.getIme()) {
					zaMenjanje.setIme(ime);
				}
				else {
					zaMenjanje.setIme(m.getIme());
				}
				
				if(prezime != m.getPrezime()) {
					zaMenjanje.setPrezime(prezime);
				}
				else {
					zaMenjanje.setPrezime(m.getPrezime());
				}
				
				if(jmbg!= m.getJmbg()) {
					zaMenjanje.setJmbg(jmbg);
				}
				else {
					zaMenjanje.setJmbg(m.getJmbg());
				}
				
				if(pol != m.getPol()) {
					zaMenjanje.setPol(pol);
				}
				else {
					zaMenjanje.setPol(m.getPol());
				}
				
				if(adresa != m.getAdresa()) {
					zaMenjanje.setAdresa(adresa);
				}
				else {
					zaMenjanje.setAdresa(m.getAdresa());
				}
				
				if(kor_ime != m.getKor_ime()) {
					zaMenjanje.setKor_ime(kor_ime);
				}
				else {
					zaMenjanje.setKor_ime(m.getKor_ime());
				}
				
				if(lozinka != m.getLozinka()) {
					zaMenjanje.setLozinka(lozinka);
				}
				else {
					zaMenjanje.setLozinka(m.getLozinka());
				}
				
				if(brojBodova != m.getBrojBodova()) {
					zaMenjanje.setBrojBodova(brojBodova);
				}
				else {
					zaMenjanje.setBrojBodova(m.getBrojBodova());
				}
				
				Liste.musterije.set(Liste.musterije.indexOf(m), zaMenjanje);
				
				RadSaFajlovima.PisanjeMusterija(Liste.musterije);
				RadSaFajlovima.CitanjeMusterija();
				RadSaFajlovima.dostupneMusterije();
			}
		}
	}
	
	public static void IzmenaAdmina(Administrator zaMenjanje, String ime, String prezime, String jmbg, 
									Pol pol,  String adresa, String broj_tel,
									String kor_ime, String lozinka, double plata) {
		for (Administrator a : Liste.administratori) {
			if(a.getId().equals(zaMenjanje.getId())) {
				
				if(ime != a.getIme()) {
					zaMenjanje.setIme(ime);
				}
				else {
					zaMenjanje.setIme(a.getIme());
				}
				
				if(prezime != a.getPrezime()) {
					zaMenjanje.setPrezime(prezime);
				}
				else {
					zaMenjanje.setPrezime(a.getPrezime());
				}
				
				if(jmbg!= a.getJmbg()) {
					zaMenjanje.setJmbg(jmbg);
				}
				else {
					zaMenjanje.setJmbg(a.getJmbg());
				}
				
				if(pol != a.getPol()) {
					zaMenjanje.setPol(pol);
				}
				else {
					zaMenjanje.setPol(a.getPol());
				}
				
				if(adresa != a.getAdresa()) {
					zaMenjanje.setAdresa(adresa);
				}
				else {
					zaMenjanje.setAdresa(a.getAdresa());
				}
				
				if(kor_ime != a.getKor_ime()) {
					zaMenjanje.setKor_ime(kor_ime);
				}
				else {
					zaMenjanje.setKor_ime(a.getKor_ime());
				}
				
				if(lozinka != a.getLozinka()) {
					zaMenjanje.setLozinka(lozinka);
				}
				else {
					zaMenjanje.setLozinka(a.getLozinka());
				}
				
				if(plata != a.getPlata()) {
					zaMenjanje.setPlata(plata);
				}
				else {
					zaMenjanje.setPlata(a.getPlata());
				}
				
				Liste.administratori.set(Liste.administratori.indexOf(a), zaMenjanje);
				
				RadSaFajlovima.PisanjeAdmin(Liste.administratori);
				RadSaFajlovima.CitanjeAdmin();
				RadSaFajlovima.dostupniAdmini();
			}
		}
	}
	
	public static void IzmenaServisera(Serviser zaMenjanje, String ime, String prezime, String jmbg, Pol pol, 
										String adresa, String broj_tel, String kor_ime, String lozinka, 
										double plata, Specijalizacija specijalizacija) {
		for (Serviser s : Liste.serviseri) {
			if(s.getId().equals(zaMenjanje.getId())) {
				
				if(ime != s.getIme()) {
					zaMenjanje.setIme(ime);
				}
				else {
					zaMenjanje.setIme(s.getIme());
				}
				
				if(prezime != s.getPrezime()) {
					zaMenjanje.setPrezime(prezime);
				}
				else {
					zaMenjanje.setPrezime(s.getPrezime());
				}
				
				if(jmbg!= s.getJmbg()) {
					zaMenjanje.setJmbg(jmbg);
				}
				else {
					zaMenjanje.setJmbg(s.getJmbg());
				}
				
				if(pol != s.getPol()) {
					zaMenjanje.setPol(pol);
				}
				else {
					zaMenjanje.setPol(s.getPol());
				}
				
				if(adresa != s.getAdresa()) {
					zaMenjanje.setAdresa(adresa);
				}
				else {
					zaMenjanje.setAdresa(s.getAdresa());
				}
				
				if(kor_ime != s.getKor_ime()) {
					zaMenjanje.setKor_ime(kor_ime);
				}
				else {
					zaMenjanje.setKor_ime(s.getKor_ime());
				}
				
				if(lozinka != s.getLozinka()) {
					zaMenjanje.setLozinka(lozinka);
				}
				else {
					zaMenjanje.setLozinka(s.getLozinka());
				}
				
				if(plata != s.getPlata()) {
					zaMenjanje.setPlata(plata);
				}
				else {
					zaMenjanje.setPlata(s.getPlata());
				}
				
				if(specijalizacija != s.getSpecijalizacija()) {
					zaMenjanje.setSpecijalizacija(specijalizacija);
				}
				else {
					zaMenjanje.setSpecijalizacija(s.getSpecijalizacija());
				}
				Liste.serviseri.set(Liste.serviseri.indexOf(s), zaMenjanje);
				
				RadSaFajlovima.PisanjeServiser(Liste.serviseri);
				RadSaFajlovima.CitanjeServis();
				RadSaFajlovima.dostupniServisi();
			}
		}
	}
	
	public static void izmenaAuta(Automobil zaMenjanje, Marka marka, Model model, Musterija vlasnik, 
									String godiste, int zapremina, int snaga, Gorivo gorivo) {
		for (Automobil a : Liste.automobili) {
			if(a.getId().equals(zaMenjanje.getId())) {
				
				if(marka != a.getMarka()) {
					zaMenjanje.setMarka(marka);
				}
				else {
					zaMenjanje.setMarka(a.getMarka());
				}
				
				if(model != a.getModel()) {
					zaMenjanje.setModel(model);
				}
				else {
					zaMenjanje.setModel(a.getModel());
				}
				
				if(vlasnik != a.getVlasnik()) {
					zaMenjanje.setVlasnik(vlasnik);
				}
				else {
					zaMenjanje.setVlasnik(a.getVlasnik());
				}
				
				if(godiste != a.getGodiste()) {
					zaMenjanje.setGodiste(godiste);
				}
				else {
					zaMenjanje.setGodiste(a.getGodiste());
				}
				
				if(zapremina != a.getZapremina()) {
					zaMenjanje.setZapremina(zapremina);
				}
				else {
					zaMenjanje.setZapremina(a.getZapremina());
				}
				
				if(snaga != a.getSnaga()) {
					zaMenjanje.setSnaga(snaga);
				}
				else {
					zaMenjanje.setSnaga(a.getSnaga());
				}
				
				if(gorivo != a.getGorivo()) {
					zaMenjanje.setGorivo(gorivo);
				}
				else {
					zaMenjanje.setGorivo(a.getGorivo());
				}
				Liste.automobili.set(Liste.automobili.indexOf(a), zaMenjanje);
				
				RadSaFajlovima.PisanjeAuto(Liste.automobili);
				RadSaFajlovima.CitanjeAuto();
				RadSaFajlovima.dostupniAutomobili();
			}
		}
	}
	
	public static void izmenaDela(Deo zaMenjanje, Marka marka, Model model, String naziv, double cena) {
		
		for (Deo d : Liste.delovi) {
			if(d.getId().equals(zaMenjanje.getId())) {
				
				if(marka != d.getMarka()) {
					zaMenjanje.setMarka(marka);
				}
				else {
					zaMenjanje.setMarka(d.getMarka());
				}
				
				if(model != d.getModel()) {
					zaMenjanje.setModel(model);
				}
				else {
					zaMenjanje.setModel(d.getModel());
				}
				
				if(naziv != d.getNaziv()) {
					zaMenjanje.setNaziv(naziv);
				}
				else {
					zaMenjanje.setNaziv(d.getNaziv());
				}
				
				if(cena != d.getCena()) {
					zaMenjanje.setCena(cena);
				}
				else {
					zaMenjanje.setCena(d.getCena());
				}
				Liste.delovi.set(Liste.delovi.indexOf(d), zaMenjanje);
				
				RadSaFajlovima.PisanjeDeo(Liste.delovi);
				RadSaFajlovima.CitanjeDeo();
				RadSaFajlovima.dostupniDelovi();
			}
		}
	}
	
	public static void izmenaServisa(Servis zaMenjanje, Automobil servisiraniAuto, Serviser serviser, ArrayList<Deo> delovi, 
									GregorianCalendar termin, Status status, String opis, String id) {
		for (Servis s : Liste.servisi) {
			if(s.getId().equals(zaMenjanje.getId())) {
				
				if(id != s.getId()) {
					zaMenjanje.setId(id);
				}
				else {
					zaMenjanje.setId(s.getId());
				}
				
				if(servisiraniAuto != s.getServisiraniAuto()) {
					zaMenjanje.setServisiraniAuto(servisiraniAuto);
				}
				else {
					zaMenjanje.setServisiraniAuto(s.getServisiraniAuto());
				}
				
				if(serviser != s.getServiser()) {
					zaMenjanje.setServiser(serviser);
				}
				else{
					zaMenjanje.setServiser(s.getServiser());
				}
				
				if(delovi != s.getDelovi()) {
					zaMenjanje.setDelovi(delovi);
				}
				else {
					zaMenjanje.setDelovi(s.getDelovi());
				}
				
				if(termin != s.getTermin()) {
					zaMenjanje.setTermin(termin);
				}
				else {
					zaMenjanje.setTermin(s.getTermin());
				}
				
				if(status != s.getStatus()) {
					zaMenjanje.setStatus(status);
				}
				else {
					zaMenjanje.setStatus(s.getStatus());
				}
				
				if(opis != s.getOpis()) {
					zaMenjanje.setOpis(opis);
				}
				else {
					zaMenjanje.setOpis(s.getOpis());
				}
				Liste.servisi.set(Liste.servisi.indexOf(s), zaMenjanje);
				

				RadSaFajlovima.PisanjeServisa(Liste.servisi);
				RadSaFajlovima.CitanjeServis();
				RadSaFajlovima.dostupniServisi();
			}
		}
	}
	
	public static void izmenaSK(ServisnaKnjizica zaMenjanje, Automobil automobil, ArrayList<Servis> servisi) {
		for (ServisnaKnjizica sk : Liste.SK) {
			if(sk.getId().equals(zaMenjanje.getId())) {
				
				if(automobil != sk.getAutomobil()) {
					zaMenjanje.setAutomobil(automobil);
				}
				else {
					zaMenjanje.setAutomobil(sk.getAutomobil());
				}
				
				if(servisi != sk.getServisi()) {
					zaMenjanje.setServisi(servisi);
				}
				else {
					zaMenjanje.setServisi(sk.getServisi());
				}
				Liste.SK.set(Liste.SK.indexOf(sk), zaMenjanje);
				
				RadSaFajlovima.PisanjeSK(Liste.SK);
				RadSaFajlovima.CitanjeSK();
				RadSaFajlovima.dostupneSK();
			}
		}
	}
}
