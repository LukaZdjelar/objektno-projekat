package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import automobil.Automobil;
import autoservis.AutoServis;
import gui.forme.ServisMusterijeForma;
import gui.prikaz.MusterijaAutoPrikaz;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;

public class GlavniProzorMusterija extends JFrame {
	private JMenuBar mainMenu = new JMenuBar();
	private JButton btnAutomobili = new JButton("Pregled automobila");
	private JButton btnZakazivanje = new JButton("Zakazivanje servisa");
	
	private Musterija prijavljeniMusterija;

	
	public GlavniProzorMusterija(Musterija prijavljeniMusterija) {
		this.prijavljeniMusterija = prijavljeniMusterija;
		setTitle("Dobrodosli, " + prijavljeniMusterija.getIme() + " [" + prijavljeniMusterija.getKor_ime() + "]");
		setSize(400, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		MigLayout mig = new MigLayout("wrap 2", "40[]20[]", "100[]");
		setLayout(mig);
		
		add(btnAutomobili);
		add(btnZakazivanje);
	}
	
	private void initActions() {
		btnAutomobili.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijaAutoPrikaz p = new MusterijaAutoPrikaz(prijavljeniMusterija);
				p.setVisible(true);
			}
		});
		
		btnZakazivanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisMusterijeForma f = new ServisMusterijeForma(prijavljeniMusterija);
				f.setVisible(true);
				
			}
		});
	}
}
