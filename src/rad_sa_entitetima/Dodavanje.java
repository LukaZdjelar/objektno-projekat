package rad_sa_entitetima;

import automobil.Automobil;
import automobil.Deo;
import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import main.ServisMain;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Pol;
import osobe.Serviser;
import servis.Servis;
import servis.ServisnaKnjizica;

public class Dodavanje {
	
	public static void dodajMusteriju(Musterija musterija) {
		Liste.musterije.add(musterija);
		RadSaFajlovima.PisanjeMusterija(Liste.musterije);
	}
	
	public static void dodajAuto(Automobil auto) {
		Liste.automobili.add(auto);
		RadSaFajlovima.PisanjeAuto(Liste.automobili);
	}
	
	public static void dodajAdmina(Administrator admin) {
		Liste.administratori.add(admin);
		RadSaFajlovima.PisanjeAdmin(Liste.administratori);
	}
	
	public static void dodajServisera(Serviser serviser) {
		Liste.serviseri.add(serviser);
		RadSaFajlovima.PisanjeServiser(Liste.serviseri);
	}
	
	public static void dodajDeo(Deo deo) {
		Liste.delovi.add(deo);
		RadSaFajlovima.PisanjeDeo(Liste.delovi);
	}
	
	public static void dodajServis(Servis servis) {
		Liste.servisi.add(servis);
		RadSaFajlovima.PisanjeServisa(Liste.servisi);
	}
	
	public static void dodajSK(ServisnaKnjizica sk) {
		Liste.SK.add(sk);
		RadSaFajlovima.PisanjeSK(Liste.SK);
	}
}
