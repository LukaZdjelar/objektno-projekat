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
import rad_sa_entitetima.Liste;
import servis.Servis;
import servis.ServisnaKnjizica;
import servis.Status;

public class Entiteti {
	public static SimpleDateFormat terminFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	public static Administrator admin1 = new Administrator("A1", false, "Stefan", "Nastic", "1604979654800", Pol.MUSKO, "Novosadska 11", "0602231000", "stefann", "stefan123", 89500);
	public static Administrator admin2 = new Administrator("A2", false, "Nikola", "Lazic", "0301970431800", Pol.MUSKO, "Vojvode Stepe 3", "0628000123", "nikolal", "nikola123", 93000);
	
	public static Musterija musterija1 = new Musterija("M1", false, "Petar", "Petrovic", "1805989112239", Pol.MUSKO, "Njegoseva 3", "0625557890", "petarp", "petar123", 2);
	public static Musterija musterija2 = new Musterija("M2", false, "Marko", "Markovic", "0608990245777", Pol.MUSKO, "Karadjordjeva 23", "0617786590", "markomarko", "marko123", 7);
	public static Musterija musterija3 = new Musterija("M3", false, "Teodora", "Bogdanovic", "120289112239", Pol.ZENSKO, "Vojvodjanska 23", "0617891467", "teodora", "teodora123", 4);
	
	public static Serviser serviser1 = new Serviser("S1", false, "Dusan", "Nikolic", "2905969451890", Pol.MUSKO, "9. Maja 14", "0627834578", "dusann", "dusan123", 52000, Specijalizacija.LIMAR);
	public static Serviser serviser2 = new Serviser("S2", false, "Slobodan", "Zivkovic", "2202981345711", Pol.MUSKO, "22. Oktobra 19", "0617825609", "slobaz", "slobodan123", 49000, Specijalizacija.AUTOELEKTRICAR);
	
	public static Automobil auto1 = new Automobil("Auto1", false, Marka.FIAT, Model.STILO, musterija1,  "2009", 1200, 56, Gorivo.BENZIN);
	public static Automobil auto2 = new Automobil("Auto2", false, Marka.CITROEN, Model.C3, musterija2, "2012", 1400, 65, Gorivo.DIZEL);
	
	public static Deo deo1 = new Deo("Deo1", false, Marka.FIAT, Model.STILO, "Brisaci", 2500);
	public static Deo deo2 = new Deo("Deo2", false, Marka.CITROEN, Model.C3, "Bregasta osovina", 4350);
	public static Deo deo3 = new Deo("Deo3", false, Marka.FIAT, Model.STILO, "Akumulator", 14500);
	public static Deo deo4 = new Deo("Deo4", false, Marka.CITROEN, Model.C3, "Menjac", 7800);
	
	public static ServisnaKnjizica sk1 = new ServisnaKnjizica("SK1", false, auto1, new ArrayList<Servis>());
	public static ServisnaKnjizica sk2 = new ServisnaKnjizica("SK2", false, auto2, new ArrayList<Servis>());
	
	public static Servis servis1 = new Servis("Servis1", false, auto1, serviser1, new ArrayList<Deo>(), new GregorianCalendar(2020, 4, 10, 15, 20), Status.ZAVRSEN, "Promena brisaca");
	public static Servis servis2 = new Servis("Servis2", false, auto2, serviser2, new ArrayList<Deo>(), new GregorianCalendar(2020, 3, 29, 21, 15), Status.ZAVRSEN, "Promena bregaste osovine");
	public static Servis servis3 = new Servis("Servis3", false, auto1, serviser2, new ArrayList<Deo>(), new GregorianCalendar(2020, 4, 25, 9, 20), Status.ZAKAZAN, "Promena akumulatora");
	public static Servis servis4 = new Servis("Servis4", false, auto2, serviser1, new ArrayList<Deo>(), new GregorianCalendar(2020, 4, 21, 12, 30), Status.ZAKAZAN, "Promena menjaca");
	
	public static void PisanjeICitanje() {
		
		RadSaFajlovima.PisanjeMusterija(Liste.musterije);
		RadSaFajlovima.CitanjeMusterija();
		
		RadSaFajlovima.PisanjeAuto(Liste.automobili);
		RadSaFajlovima.CitanjeAuto();
		
		RadSaFajlovima.PisanjeAdmin(Liste.administratori);
		RadSaFajlovima.CitanjeAdmin();
		
		RadSaFajlovima.PisanjeServiser(Liste.serviseri);
		RadSaFajlovima.CitanjeServiser();
		
		RadSaFajlovima.PisanjeDeo(Liste.delovi);
		RadSaFajlovima.CitanjeDeo();
		
		RadSaFajlovima.PisanjeServisa(Liste.servisi);
		RadSaFajlovima.CitanjeServis();
		
		RadSaFajlovima.PisanjeSK(Liste.SK);
		RadSaFajlovima.CitanjeSK();
	}
}
