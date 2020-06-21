package main;

import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.LoginProzor;

public class ServisMain {
	
	public static void main(String[] args) {
		RadSaFajlovima.dodavanjeServisaSK();
		Entiteti.PisanjeICitanje();
		
		LoginProzor lp = new LoginProzor();
		lp.setVisible(true);
	}
}