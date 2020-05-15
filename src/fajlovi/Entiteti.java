package fajlovi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import automobil.Automobil;
import automobil.Deo;
import automobil.Gorivo;
import automobil.Marka;
import automobil.Model;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Pol;
import osobe.Serviser;
import osobe.Specijalizacija;
import servis.Servis;
import servis.ServisnaKnjizica;
import servis.Status;

public class Entiteti {
	public static SimpleDateFormat terminFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	public static Administrator admin1 = new Administrator("00001", "Stefan", "Nastic", "1604979654800", Pol.MUSKO, "Novosadska 11", "0602231000", "stefann", "stefan123", 89500);
	public static Administrator admin2 = new Administrator("00002", "Nikola", "Lazic", "0301970431800", Pol.MUSKO, "Vojvode Stepe 3", "0628000123", "nikolal", "nikola123", 93000);
	
	public static Musterija musterija1 = new Musterija("20000", "Petar", "Petrovic", "1805989112239", Pol.MUSKO, "Njegoseva 3", "0625557890", "petarp", "petar123", 2);
	public static Musterija musterija2 = new Musterija("20001", "Marko", "Markovic", "0608990245777", Pol.MUSKO, "Karadjordjeva 23", "0617786590", "markomarko", "marko123", 7);
	
	public static Serviser serviser1 = new Serviser("10000", "Dusan", "Nikolic", "2905969451890", Pol.MUSKO, "9. Maja 14", "0627834578", "dusann", "dusan123", 52000, Specijalizacija.LIMAR);
	public static Serviser serviser2 = new Serviser("10001", "Slobodan", "Zivkovic", "2202981345711", Pol.MUSKO, "22. Oktobra 19", "0617825609", "slobaz", "slobodan123", 49000, Specijalizacija.AUTOELEKTRICAR);
	
	public static Automobil auto1 = new Automobil("70000", Marka.FIAT, Model.STILO, musterija1,  "2009", 1200, 56, Gorivo.BENZIN);
	public static Automobil auto2 = new Automobil("70001", Marka.CITROEN, Model.C3, musterija2, "2012", 1400, 65, Gorivo.DIZEL);
	
	public static Deo deo1 = new Deo("20001", Marka.FIAT, Model.STILO, "Brisaci", 2500);
	public static Deo deo2 = new Deo("20002", Marka.CITROEN, Model.C3, "Bregasta osovina", 4350);
	public static Deo deo3 = new Deo("20003", Marka.FIAT, Model.STILO, "Akumulator", 14500);
	public static Deo deo4 = new Deo("20004", Marka.CITROEN, Model.C3, "Menjac", 7800);
	
	public static ServisnaKnjizica sk1 = new ServisnaKnjizica("40001", auto1, new ArrayList<Servis>());
	public static ServisnaKnjizica sk2 = new ServisnaKnjizica("40002", auto2, new ArrayList<Servis>());
	
	public static Servis servis1 = new Servis("50001", auto1, serviser1, new ArrayList<Deo>(), new GregorianCalendar(2020, 4, 10, 15, 20), Status.ZAVRSEN, "Promena brisaca");
	public static Servis servis2 = new Servis("50002", auto2, serviser2, new ArrayList<Deo>(), new GregorianCalendar(2020, 3, 29, 21, 15), Status.ZAVRSEN, "Promena bregaste osovine");
	
	public static Servis servis3 = new Servis("50003", auto1, serviser2, new ArrayList<Deo>(), new GregorianCalendar(2020, 4, 25, 9, 20), Status.ZAKAZAN, "Promena akumulatora");
	public static Servis servis4 = new Servis("50004", auto2, serviser1, new ArrayList<Deo>(), new GregorianCalendar(2020, 4, 21, 12, 30), Status.ZAKAZAN, "Promena menjaca");
	
	public static void RadSaMusterijama() {
		ArrayList<Musterija> listaMusterija = new ArrayList<Musterija>();
		listaMusterija.add(musterija1);
		listaMusterija.add(musterija2);
		
		RadSaFajlovima.PisanjeMusterija(listaMusterija);
		RadSaFajlovima.CitanjeMusterija();
	}
	
	public static void RadSaAutomobilima() {
		ArrayList<Automobil> listaAutomobila = new ArrayList<Automobil>();
		listaAutomobila.add(auto1);
		listaAutomobila.add(auto2);
		
		RadSaFajlovima.PisanjeAuto(listaAutomobila);
		RadSaFajlovima.CitanjeAuto();
	}
	
	public static void RadSaAdministratorima() {
		ArrayList<Administrator> listaAdmina = new ArrayList<Administrator>();
		listaAdmina.add(admin1);
		listaAdmina.add(admin2);
		
		RadSaFajlovima.PisanjeAdmin(listaAdmina);
		RadSaFajlovima.CitanjeAdmin();
	}
	
	public static void RadSaServiserima() {
		ArrayList<Serviser> listaServisera = new ArrayList<Serviser>();
		listaServisera.add(serviser1);
		listaServisera.add(serviser2);
		
		RadSaFajlovima.PisanjeServiser(listaServisera);
		RadSaFajlovima.CitanjeServiser();
	}
	
	public static void RadSaDelovima() {
		ArrayList<Deo> listaDelova = new ArrayList<Deo>();
		listaDelova.add(deo1);
		listaDelova.add(deo2);
		listaDelova.add(deo3);
		listaDelova.add(deo4);
		
		RadSaFajlovima.PisanjeDeo(listaDelova);
		RadSaFajlovima.CitanjeDeo();
	}
	
	public static void RadSaServisima() {
		ArrayList<Servis> listaServisa = new ArrayList<Servis>();
		listaServisa.add(servis1);
		listaServisa.add(servis2);
		listaServisa.add(servis3);
		listaServisa.add(servis4);
		
		RadSaFajlovima.PisanjeServisa(listaServisa);
		RadSaFajlovima.CitanjeServis();
	}
	
	public static void RadSaSK() {
		ArrayList<ServisnaKnjizica> listaSK = new ArrayList<ServisnaKnjizica>();
		listaSK.add(sk1);
		listaSK.add(sk2);
		
		RadSaFajlovima.PisanjeSK(listaSK);
		RadSaFajlovima.CitanjeSK();
	}
}
