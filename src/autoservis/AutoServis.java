package autoservis;

import osobe.Administrator;
import osobe.Musterija;
import osobe.Serviser;
import rad_sa_entitetima.Liste;

public class AutoServis {
	
	
	public static Musterija loginM(String korisnickoIme, String lozinka) {
		for(Musterija musterija : Liste.dostupneMusterije) {
			if(musterija.getKor_ime().equalsIgnoreCase(korisnickoIme) &&
					musterija.getLozinka().equals(lozinka) && !musterija.getIzbrisan()) {
				return musterija;
			}
		}
		return null;
	}
	
	public static Administrator loginA(String korisnickoIme, String lozinka) {
		for(Administrator admin: Liste.dostupniAdministratori) {
			if(admin.getKor_ime().equalsIgnoreCase(korisnickoIme) &&
					admin.getLozinka().equals(lozinka) && !admin.getIzbrisan()) {
				return admin;
			}
		}
		return null;
	}
	
	public static Serviser loginS(String korisnickoIme, String lozinka) {
		for(Serviser serviser : Liste.dostupniServiseri) {
			if(serviser.getKor_ime().equalsIgnoreCase(korisnickoIme) &&
					serviser.getLozinka().equals(lozinka) && !serviser.getIzbrisan()) {
				return serviser;
			}
		}
		return null;
	}
}
