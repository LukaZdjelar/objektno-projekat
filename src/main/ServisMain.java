package main;

import java.util.ArrayList;
import java.util.Arrays;

import automobil.Gorivo;
import automobil.Marka;
import automobil.Model;
import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import osobe.Musterija;
import osobe.Pol;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;
import rad_sa_entitetima.Liste;

public class ServisMain {
	
	public static void main(String[] args) {
		
		Dodavanje.dodavanje();
		
		Entiteti.PisanjeICitanje();
		
		
		//Brisanje.izbrisiMusteriju(Entiteti.musterija1);
		//Brisanje.izbrisiAdmina(Entiteti.admin2);
		//Brisanje.izbrisiServisera(Entiteti.serviser2);
		//Brisanje.izbrisiSK(Entiteti.sk1); 

		//Izmene.IzmenaMusterije(Entiteti.musterija2, "Darko", "Milicic", "2809789123456", Pol.MUSKO, "Kosovska 1", "0645678910", "darko123", "darko123", 17);
		//Izmene.izmenaDela(Entiteti.deo2, Marka.MERCEDES, Model.E, "Motor", 30000);
		//Izmene.izmenaAuta(Entiteti.auto2, Marka.FIAT, Model.MULTIPLA, Entiteti.musterija3, "2008", 1400, 78, Gorivo.BENZIN);
	}
}