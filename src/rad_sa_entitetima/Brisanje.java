package rad_sa_entitetima;

import automobil.Automobil;
import automobil.Deo;
import fajlovi.RadSaFajlovima;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Serviser;
import servis.Servis;
import servis.ServisnaKnjizica;

public class Brisanje {
	
	public static void izbrisiMusteriju(Musterija musterija) {
		for (Musterija zaBrisanje : Liste.musterije) {
			if(zaBrisanje.getId().equals(musterija.getId())) {
				musterija.setIzbrisan(true);
				Liste.musterije.set(Liste.musterije.indexOf(zaBrisanje), musterija);
				
				RadSaFajlovima.PisanjeMusterija(Liste.musterije);
				RadSaFajlovima.CitanjeMusterija();
				RadSaFajlovima.dostupneMusterije();
			}	
		}
	}
	
	public static void izbrisiAdmina(Administrator admin) {
		for (Administrator zaBrisanje : Liste.administratori) {
			if(zaBrisanje.getId().equals(admin.getId())) {
				admin.setIzbrisan(true);
				Liste.administratori.set(Liste.administratori.indexOf(zaBrisanje), admin);
				
				RadSaFajlovima.PisanjeAdmin(Liste.administratori);
				RadSaFajlovima.CitanjeAdmin();
				RadSaFajlovima.dostupniAdmini();
			}	
		}
	}
	
	public static void izbrisiServisera(Serviser serviser) {
		for (Serviser zaBrisanje : Liste.serviseri) {
			if(zaBrisanje.getId().equals(serviser.getId())) {
				serviser.setIzbrisan(true);
				Liste.serviseri.set(Liste.serviseri.indexOf(zaBrisanje), serviser);
				
				RadSaFajlovima.PisanjeServiser(Liste.serviseri);
				RadSaFajlovima.CitanjeServiser();
				RadSaFajlovima.dostupniServiseri();
			}	
		}
	}
	
	public static void izbrisiAuto(Automobil auto) {
		for (Automobil zaBrisanje : Liste.automobili) {
			if(zaBrisanje.getId().equals(auto.getId())) {
				auto.setIzbrisan(true);
				Liste.automobili.set(Liste.automobili.indexOf(zaBrisanje), auto);
				
				RadSaFajlovima.PisanjeAuto(Liste.automobili);
				RadSaFajlovima.CitanjeAuto();
				RadSaFajlovima.dostupniAutomobili();
			}	
		}
	}
	
	public static void izbrisiDeo(Deo deo) {
		for (Deo zaBrisanje : Liste.delovi) {
			if(zaBrisanje.getId().equals(deo.getId())) {
				deo.setIzbrisan(true);
				Liste.delovi.set(Liste.delovi.indexOf(zaBrisanje), deo);
				
				RadSaFajlovima.PisanjeDeo(Liste.delovi);
				RadSaFajlovima.CitanjeDeo();
				RadSaFajlovima.dostupniDelovi();
			}	
		}
	}
	
	public static void izbrisiServisa(Servis servis) {
		for (Servis zaBrisanje : Liste.servisi) {
			if(zaBrisanje.getId().equals(servis.getId())) {
				servis.setIzbrisan(true);
				Liste.servisi.set(Liste.servisi.indexOf(zaBrisanje), servis);
				
				RadSaFajlovima.PisanjeServisa(Liste.servisi);
				RadSaFajlovima.CitanjeServis();
				RadSaFajlovima.dostupniServisi();
			}	
		}
	}
	
	public static void izbrisiSK(ServisnaKnjizica SK) {
		for (ServisnaKnjizica zaBrisanje : Liste.SK) {
			if(zaBrisanje.getId().equals(SK.getId())) {
				SK.setIzbrisan(true);
				Liste.SK.set(Liste.SK.indexOf(zaBrisanje), SK);
				
				RadSaFajlovima.PisanjeSK(Liste.SK);
				RadSaFajlovima.CitanjeSK();
				RadSaFajlovima.dostupneSK();
			}	
		}
	}
}
