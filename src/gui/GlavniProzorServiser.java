package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.prikaz.ServiserServisiPrikaz;
import osobe.Serviser;

public class GlavniProzorServiser extends JFrame {
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu servisi = new JMenu("Servisi");
	private JMenuItem pregled  = new JMenuItem("Pregled servisa");
	
	private Serviser prijavljeniServiser;
	
	public GlavniProzorServiser(Serviser prijavljeniServiser) {
		this.prijavljeniServiser = prijavljeniServiser;
		setTitle("Serviser: " + prijavljeniServiser.getKor_ime());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(servisi);
		servisi.add(pregled);
	}
	
	private void initActions() {
		pregled.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiserServisiPrikaz p = new ServiserServisiPrikaz(prijavljeniServiser);
				p.setVisible(true);
			}
		});
	}
}
