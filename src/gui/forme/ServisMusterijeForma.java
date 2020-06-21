package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import automobil.Automobil;
import automobil.Deo;
import fajlovi.RadSaFajlovima;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Pol;
import osobe.Serviser;
import osobe.Specijalizacija;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Liste;
import servis.Servis;
import servis.Status;

public class ServisMusterijeForma extends JFrame {
	
	private JLabel labelAuto = new JLabel("Automobil");
	private JComboBox<String> cbAuto = new JComboBox<String>();
	private JLabel labelOpis= new JLabel("Opis");
	private JTextField txtOpis= new JTextField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	Musterija prijavljeniMusterija;

	public ServisMusterijeForma(Musterija prijavljeniMusterija) {
		this.prijavljeniMusterija = prijavljeniMusterija;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	private void initGUI(){
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][]20[]");
		setLayout(layout);
		
		for (Automobil automobil : Liste.dostupniAutomobili) {
			if(prijavljeniMusterija.getId().equals(automobil.getVlasnik().getId())) {
				cbAuto.addItem(automobil.getId());
			}
		}
		
		add(labelAuto);
		add(cbAuto);
		add(labelOpis);
		add(txtOpis);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String AutoID= cbAuto.getSelectedItem().toString();
				Automobil auto = RadSaFajlovima.nadjiAutomobil(AutoID);
				String opis = txtOpis.getText().trim();
				ArrayList<Deo> delovi = new ArrayList<Deo>();
				Serviser serviser = RadSaFajlovima.nadjiServisera("S0");
				Deo deo = new Deo();
				deo.setId("privremeno");
				delovi.add(deo);
				
				Servis novi = new Servis(prijavljeniMusterija.getId() + "-" + auto.getId(), false, auto, serviser, delovi, new GregorianCalendar(), Status.ZAKAZAN, opis);
				Dodavanje.dodajServis(novi);
				
				ServisMusterijeForma.this.dispose();
				ServisMusterijeForma.this.setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisMusterijeForma.this.dispose();
				ServisMusterijeForma.this.setVisible(false);
				
			}
		});
	}
}
