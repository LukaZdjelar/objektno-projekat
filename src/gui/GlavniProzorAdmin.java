package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.prikaz.AdministratoriPrikaz;
import gui.prikaz.AutoPrikaz;
import gui.prikaz.DeloviPrikaz;
import gui.prikaz.MusterijePrikaz;
import gui.prikaz.SKPrikaz;
import gui.prikaz.ServiseriPrikaz;
import gui.prikaz.ServisiPrikaz;
import osobe.Administrator;

public class GlavniProzorAdmin extends JFrame {
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu korisnici = new JMenu("Korisnici");
	private JMenu servisiAutomobili = new JMenu("Servisi i automobili");
	private JMenuItem admini = new JMenuItem("Administratori");
	private JMenuItem musterije = new JMenuItem("Musterije");
	private JMenuItem serviseri = new JMenuItem("Serviseri");
	private JMenuItem delovi = new JMenuItem("Delovi");
	private JMenuItem sk = new JMenuItem("Servisne knjizice");
	private JMenuItem servisi = new JMenuItem("Servisi");
	private JMenuItem automobili = new JMenuItem("Automobili");
	private Administrator prijavljeniAdmin;
	
	public GlavniProzorAdmin(Administrator prijavljeniAdmin) {
		this.prijavljeniAdmin = prijavljeniAdmin;
		setTitle("Admin: " + prijavljeniAdmin.getKor_ime());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(korisnici);
		mainMenu.add(servisiAutomobili);
		korisnici.add(admini);
		korisnici.add(serviseri);
		korisnici.add(musterije);
		servisiAutomobili.add(delovi);
		servisiAutomobili.add(sk);
		servisiAutomobili.add(servisi);
		servisiAutomobili.add(automobili);
	}
	
	private void initActions() {
		admini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratoriPrikaz p = new AdministratoriPrikaz();
				p.setVisible(true);
			}
		});
		
		serviseri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiseriPrikaz p = new ServiseriPrikaz();
				p.setVisible(true);
			}
		});
		
		musterije.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijePrikaz p = new MusterijePrikaz();
				p.setVisible(true);
			}
		});
		
		delovi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeloviPrikaz p = new DeloviPrikaz();
				p.setVisible(true);
			}
		});
		
		sk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SKPrikaz p = new SKPrikaz();
				p.setVisible(true);
			}
		});
		
		servisi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisiPrikaz p = new ServisiPrikaz();
				p.setVisible(true);
			}
		});
		
		automobili.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoPrikaz p = new AutoPrikaz();
				p.setVisible(true);
				
			}
		});
	}
}
