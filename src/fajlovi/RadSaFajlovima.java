package fajlovi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
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

public class RadSaFajlovima {
	
	public static ArrayList<Musterija> CitanjeMusterija() {
		ArrayList<Musterija> musterije = new ArrayList<Musterija>();
		try {
		File fileMusterija = new File("src/fajlovi/Musterije.txt");
		BufferedReader readerMusterija = new BufferedReader(new FileReader(fileMusterija));
		String line;
		while((line = readerMusterija.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String ime = lineSplit[1];
			String prezime = lineSplit[2];
			String jmbg = lineSplit[3];
			String indeksPol = lineSplit[4];
			int intPol = Integer.parseInt(indeksPol);
			Pol pol = Pol.values()[intPol];
			String adresa = lineSplit[5];
			String broj_tel = lineSplit[6];
			int brojBodova = Integer.parseInt(lineSplit[7]);
			String kor_ime = lineSplit[8];
			String lozinka = lineSplit[9];
		
			Musterija musterija = new Musterija(id, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka, brojBodova);
			musterije.add(musterija);
		}
		readerMusterija.close();
		}catch(IOException e) {
			System.out.println("Greska pri ucitavanju musterija");
		}
		return musterije;
	}
	
	public static Musterija nadjiMusteriju(String id) {
		for(Musterija musterija : CitanjeMusterija()) {
			if(musterija.getId().equals(id)) {
				return musterija;
			}
		}
		return null;
	}
	
	public static ArrayList<Automobil> CitanjeAuto() {
		ArrayList<Automobil> automobili = new ArrayList<Automobil>();
		try {
		File fileAuto = new File("src/fajlovi/Automobili.txt");
		BufferedReader readerAuto = new BufferedReader(new FileReader(fileAuto));
		String line;
		while((line = readerAuto.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String indeksMarka = lineSplit[1];
			int intMarka = Integer.parseInt(indeksMarka);
			Marka marka = Marka.values()[intMarka];
			String indeksModel = lineSplit[2];
			int intModel = Integer.parseInt(indeksModel);
			Model model = Model.values()[intModel];
			String godiste = lineSplit[3];
			int zapremina = Integer.parseInt(lineSplit[4]);
			int  snaga = Integer.parseInt(lineSplit[5]);
			String indeksGorivo = lineSplit[6];
			int intGorivo = Integer.parseInt(indeksGorivo);
			Gorivo gorivo = Gorivo.values()[intGorivo];
			String vlasnikStr = lineSplit[7];
			Musterija vlasnik = nadjiMusteriju(vlasnikStr);
			
			Automobil automobil = new Automobil(id, marka, model, vlasnik, godiste, zapremina, snaga, gorivo);
			automobili.add(automobil);
		}
		readerAuto.close();
		}catch(IOException e) {
			System.out.println("Greska pri citanju automobila");
		}
		return automobili;
	}
	
	public static Automobil nadjiAutomobil(String id) {
		for(Automobil automobil : CitanjeAuto()) {
			if(automobil.getId().equals(id)) {
				return automobil;
			}
		}
		return null;
	}
	
	public static ArrayList<Administrator> CitanjeAdmin() {
		ArrayList<Administrator> administratori = new ArrayList<Administrator>();
		try {
		File fileAdmin = new File("src/fajlovi/Administratori.txt");
		BufferedReader readerAdmin = new BufferedReader(new FileReader(fileAdmin));
		String line;
		while((line = readerAdmin.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String ime = lineSplit[1];
			String prezime = lineSplit[2];
			String jmbg = lineSplit[3];
			String indeksPol = lineSplit[4];
			int intPol = Integer.parseInt(indeksPol);
			Pol pol = Pol.values()[intPol];
			String adresa = lineSplit[5];
			String broj_tel = lineSplit[6];
			Double plata = Double.parseDouble(lineSplit[7]);
			String kor_ime = lineSplit[8];
			String lozinka = lineSplit[9];
			
			Administrator administrator = new Administrator(id, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka, plata);
			administratori.add(administrator);
		}
		readerAdmin.close();
		}catch(IOException e) {
			System.out.println("Greska pri citanju admina");
		}
		return administratori;
	}
	
	public static Administrator nadjiAdministratora(String id) {
		for(Administrator administrator : CitanjeAdmin()) {
			if(administrator.getId().equals(id)) {
				return administrator;
			}
		}
		return null;
	}
	
	public static ArrayList<Serviser> CitanjeServiser() {
		ArrayList<Serviser> serviseri = new ArrayList<Serviser>();
		try {
		File fileServiser = new File("src/fajlovi/Serviser.txt");
		BufferedReader readerServiser = new BufferedReader(new FileReader(fileServiser));
		String line;
		while((line = readerServiser.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String ime = lineSplit[1];
			String prezime = lineSplit[2];
			String jmbg = lineSplit[3];
			//String indeksPol = lineSplit[4];
			int intPol = Integer.parseInt(lineSplit[4]);
			Pol pol = Pol.values()[intPol];
			String adresa = lineSplit[5];
			String broj_tel = lineSplit[6];
			Double plata = Double.parseDouble(lineSplit[7]);
			String indeksSpecijalizacija = lineSplit[8];
			int intSpecijalizacija = Integer.parseInt(indeksSpecijalizacija);
			Specijalizacija specijalizacija = Specijalizacija.values()[intSpecijalizacija];
			String kor_ime = lineSplit[9];
			String lozinka = lineSplit[10];
			
			Serviser serviser = new Serviser(id, ime, prezime, jmbg, pol, adresa, broj_tel, kor_ime, lozinka, plata, specijalizacija);
			serviseri.add(serviser);
		}
		readerServiser.close();
		}catch(IOException e) {
			System.out.println("Greska pri citanju servisera");
		}
		return serviseri;
	}
	
	public static Serviser nadjiServisera(String id) {
		for(Serviser serviser : CitanjeServiser()) {
			if(serviser.getId().equals(id)) {
				return serviser;
			}
		}
		return null;
	}
	
	public static ArrayList<Deo> CitanjeDeo() {
		ArrayList<Deo> delovi = new ArrayList<Deo>();
		try {
		File fileDeo = new File("src/fajlovi/Delovi.txt");
		BufferedReader readerDeo = new BufferedReader(new FileReader(fileDeo));
		String line;
		while((line = readerDeo.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String naziv = lineSplit[1];
			String indeksMarka = lineSplit[2];
			int intMarka = Integer.parseInt(indeksMarka);
			Marka marka = Marka.values()[intMarka];
			String indeksModel = lineSplit[3];
			int intModel = Integer.parseInt(indeksModel);
			Model model = Model.values()[intModel];
			Double cena = Double.parseDouble(lineSplit[4]);
			
			Deo deo = new Deo(id, marka, model, naziv, cena);
			delovi.add(deo);
		}
		readerDeo.close();
		}catch(IOException e) {
			System.out.println("Greska pri citanju delova");
		}
		return delovi;
	}
	
	public static Deo nadjiDeo(String id) {
		for(Deo deo : CitanjeDeo()) {
			if(deo.getId().equals(id)) {
				return deo;
			}
		}
		return null;
	}
	
	public static ArrayList<Servis> CitanjeServis() {
		ArrayList<Servis> servisi = new ArrayList<Servis>();
		try {
		File fileServis = new File("src/fajlovi/Servisi.txt");
		BufferedReader readerServis = new BufferedReader(new FileReader(fileServis));
		String line;
		while((line = readerServis.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String autoStr = lineSplit[1];
			Automobil servisiraniAuto = nadjiAutomobil(autoStr);
			String serviserStr = lineSplit[2];
			Serviser serviser = nadjiServisera(serviserStr);
			String deloviStr = lineSplit[3];
			String[] deloviSplit = deloviStr.split(";");
			ArrayList<Deo> delovi = new ArrayList<Deo>();
			for(String idd : deloviSplit) {
				Deo deo = nadjiDeo(idd);
				if(deo != null) {
					delovi.add(deo);
				}
			}
			
			String terminStr = lineSplit[4];
			GregorianCalendar termin = new GregorianCalendar();
			try {
				termin.setTime(Entiteti.terminFormat.parse(terminStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String indeksStatus = lineSplit[5];
			int intStatus = Integer.parseInt(indeksStatus);
			Status status = Status.values()[intStatus];
			String opis = lineSplit[6];
			
			Servis servis = new Servis(id, servisiraniAuto, serviser, delovi, termin, status, opis);
			servisi.add(servis);
		}
		readerServis.close();
		}catch(IOException e) {
			System.out.println("Greska pri ucitavanju servisa");
		}
		return servisi;
		
	}
	
	public static Servis nadjiServis(String id) {
		for(Servis servis: CitanjeServis()) {
			if(servis.getId().equals(id)) {
				return servis;
			}
		}
		return null;
	}
	
	public static ArrayList<ServisnaKnjizica> CitanjeSK() {
		ArrayList<ServisnaKnjizica> sKnjizice = new ArrayList<ServisnaKnjizica>();
		try {
		File fileSK = new File("src/fajlovi/ServisneKnjizice.txt");
		BufferedReader readerSK = new BufferedReader(new FileReader(fileSK));
		String line;
		while((line = readerSK.readLine()) != null) {
			String[] lineSplit = line.split("\\|");
			String id = lineSplit[0];
			String autoStr = lineSplit[1];
			Automobil automobil = nadjiAutomobil(autoStr);
			String servisiStr = lineSplit[2];
			String[] servisiSplit = servisiStr.split(";");
			ArrayList<Servis> servisi = new ArrayList<Servis>();
			for(String idd : servisiSplit) {
				Servis servis = nadjiServis(idd);
				if(servis != null) {
					servisi.add(servis);
				}
			}
			
			ServisnaKnjizica sk = new ServisnaKnjizica(id, automobil, servisi);
			sKnjizice.add(sk);
		}
		readerSK.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return sKnjizice;
	}
	
	public static ServisnaKnjizica nadjiSK(String id) {
		for(ServisnaKnjizica sKnjizica: CitanjeSK()) {
			if(sKnjizica.getId().equals(id)) {
				return sKnjizica;
			}
		}
		return null;
	}
	
	public static void PisanjeMusterija(ArrayList<Musterija> musterijaLista) {
		String sadrzaj = "";
		for (Musterija musterija : musterijaLista) {
			sadrzaj += musterija.getId() + "|" + musterija.getIme() + "|" + musterija.getPrezime() + "|" + musterija.getJmbg() + "|" + musterija.getPol().ordinal()
					+ "|" + musterija.getAdresa() + "|" + musterija.getBroj_tel() + "|" + musterija.getBrojBodova() + "|" + musterija.getKor_ime() + "|" + musterija.getLozinka()
					+"\n";
		}
		try {
			File fileMusterija = new File("src/fajlovi/Musterije.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileMusterija));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public static void PisanjeAuto(ArrayList<Automobil> autoLista) {
		String sadrzaj = "";
		for (Automobil automobil : autoLista) {
			sadrzaj += automobil.getId() + "|" + automobil.getMarka().ordinal() + "|" + automobil.getModel().ordinal() + "|" + automobil.getGodiste() + "|" + 
						automobil.getZapremina() + "|" + automobil.getSnaga() + "|" + automobil.getGorivo().ordinal() + "|" + automobil.getVlasnik().getId()
						 +"\n";
		}
		try {
			File fileAuto = new File("src/fajlovi/Automobili.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileAuto));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public static void PisanjeAdmin(ArrayList<Administrator> adminList) {
		String sadrzaj = "";
		for (Administrator administrator : adminList) {
			sadrzaj += administrator.getId() + "|" + administrator.getIme() + "|" + administrator.getPrezime() + "|" + administrator.getJmbg() + "|" + 
						administrator.getPol().ordinal() + "|" + administrator.getAdresa() + "|" + administrator.getBroj_tel() + "|" + administrator.getPlata()
						 + "|" + administrator.getKor_ime() + "|" + administrator.getLozinka() + "\n";
		}
		try {
			File fileAdmin = new File("src/fajlovi/Administratori.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileAdmin));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public static void PisanjeServiser(ArrayList<Serviser> serviserList) {
		String sadrzaj = "";
		for (Serviser serviser : serviserList) {
			sadrzaj += serviser.getId() + "|" + serviser.getIme() + "|" + serviser.getPrezime() + "|" + serviser.getJmbg() + "|" + serviser.getPol().ordinal() + "|" + 
						serviser.getAdresa() + "|" + serviser.getBroj_tel() + "|" + serviser.getPlata() + "|" + serviser.getSpecijalizacija().ordinal() + "|" + 
						serviser.getKor_ime() + "|" + serviser.getLozinka() + "\n";
		}
		try {
			File fileServiser = new File("src/fajlovi/Serviser.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileServiser));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public static void PisanjeDeo(ArrayList<Deo> deoList) {
		String sadrzaj = "";
		for (Deo deo : deoList) {
			sadrzaj += deo.getId() + "|" + deo.getNaziv() + "|" + deo.getMarka().ordinal() + "|" + deo.getModel().ordinal() + "|" + deo.getCena() + "\n";
		}
		try {
			File fileDeo = new File("src/fajlovi/Delovi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileDeo));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public static void PisanjeServisa(ArrayList<Servis> servisLista) {
		String sadrzaj = "";
		for (Servis servis : servisLista) {
			sadrzaj += servis.getId() + "|" + servis.getServisiraniAuto().getId() + "|" + servis.getServiser().getId() + "|" + servis.getDelovi() + "|" + 
					Entiteti.terminFormat.format(servis.getTermin().getTime()) + "|" + servis.getStatus().ordinal() + "|" + servis.getOpis() + "\n";
		}
		try {
			File fileServis = new File("src/fajlovi/Servisi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileServis));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public static void PisanjeSK(ArrayList<ServisnaKnjizica> SKLista) {
		String sadrzaj = "";
		for (ServisnaKnjizica sKnjizica : SKLista) {
			sadrzaj += sKnjizica.getId() + "|" + sKnjizica.getAutomobil().getId() + "|" + sKnjizica.getServisi() + "\n";
		}
		try {
			File fileServis = new File("src/fajlovi/ServisneKnjizice.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileServis));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
}
