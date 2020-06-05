package rad_sa_entitetima;

import java.util.ArrayList;

import automobil.Automobil;
import automobil.Deo;
import fajlovi.RadSaFajlovima;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Serviser;
import servis.Servis;
import servis.ServisnaKnjizica;

public class Liste {
	
	public static ArrayList<Musterija> musterije = RadSaFajlovima.CitanjeMusterija();
	public static ArrayList<Automobil> automobili = RadSaFajlovima.CitanjeAuto();
	public static ArrayList<Administrator> administratori = RadSaFajlovima.CitanjeAdmin();
	public static ArrayList<Serviser> serviseri = RadSaFajlovima.CitanjeServiser();
	public static ArrayList<Deo> delovi = RadSaFajlovima.CitanjeDeo();
	public static ArrayList<Servis> servisi = RadSaFajlovima.dostupniServisi();
	public static ArrayList<ServisnaKnjizica> SK = RadSaFajlovima.CitanjeSK();
	
	public static ArrayList<Musterija> dostupneMusterije = RadSaFajlovima.dostupneMusterije();
	public static ArrayList<Automobil> dostupniAutomobili = RadSaFajlovima.dostupniAutomobili();
	public static ArrayList<Administrator> dostupniAdministratori = RadSaFajlovima.dostupniAdmini();
	public static ArrayList<Serviser> dostupniServiseri = RadSaFajlovima.dostupniServiseri();
	public static ArrayList<Deo> dostupniDelovi = RadSaFajlovima.dostupniDelovi();
	public static ArrayList<Servis> dostupniServisi = RadSaFajlovima.dostupniServisi();
	public static ArrayList<ServisnaKnjizica> dostupneSK = RadSaFajlovima.dostupneSK();
}
