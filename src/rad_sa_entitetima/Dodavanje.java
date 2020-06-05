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
	}
	
	public static void dodajAuto(Automobil auto) {
		Liste.automobili.add(auto);
	}
	
	public static void dodajAdmina(Administrator admin) {
		Liste.administratori.add(admin);
	}
	
	public static void dodajServisera(Serviser serviser) {
		Liste.serviseri.add(serviser);
	}
	
	public static void dodajDeo(Deo deo) {
		Liste.delovi.add(deo);
	}
	
	public static void dodajServis(Servis servis) {
		Liste.servisi.add(servis);
	}
	
	public static void dodajSK(ServisnaKnjizica sk) {
		Liste.SK.add(sk);
	}
	
	public static void dodavanje() {
		dodajMusteriju(Entiteti.musterija1);
		dodajMusteriju(Entiteti.musterija2);
		dodajMusteriju(Entiteti.musterija3);
		
		dodajAuto(Entiteti.auto1);
		dodajAuto(Entiteti.auto2);
		
		dodajAdmina(Entiteti.admin1);
		dodajAdmina(Entiteti.admin2);
		
		dodajServisera(Entiteti.serviser1);
		dodajServisera(Entiteti.serviser2);
		
		dodajDeo(Entiteti.deo1);
		dodajDeo(Entiteti.deo2);
		dodajDeo(Entiteti.deo3);
		dodajDeo(Entiteti.deo4);
		
		dodajServis(Entiteti.servis1);
		dodajServis(Entiteti.servis2);
		dodajServis(Entiteti.servis3);
		dodajServis(Entiteti.servis4);
		
		dodajSK(Entiteti.sk1);
		dodajSK(Entiteti.sk2);
		
	}
	
}
