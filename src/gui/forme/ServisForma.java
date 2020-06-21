package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import automobil.Automobil;
import automobil.Deo;
import automobil.Model;
import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.prikaz.DeloviPrikaz;
import gui.prikaz.DodavanjeDelovaPrikaz;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Serviser;
import rad_sa_entitetima.Liste;
import servis.Servis;
import servis.Status;

public class ServisForma extends JFrame {
	private JLabel labelID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel labelAuto = new JLabel("Automobil");
	private JComboBox<String> cbAuto = new JComboBox<String>();
	private JLabel labelServiser = new JLabel("Serviser");
	private JComboBox<String> cbServiser = new JComboBox<String>();
	private JLabel labelTermin = new JLabel("Termin (DD.MM.YYYY. HH:MM)");
	private JTextField txtTermin = new JTextField(20);
	private JLabel labelStatus = new JLabel("Status");
	private JComboBox<Status> cbStatus = new JComboBox<Status>(Status.values());
	private JLabel labelOpis= new JLabel("Opis");
	private JTextField txtOpis= new JTextField(20);
	
	private JButton btnDelovi = new JButton("Dodaj delove");
	private JButton btnCancel = new JButton("Cancel");
	
	Servis servis;
	Servis dodavanje;
	String id;
	
	public ServisForma(Servis servis) {
		this.servis = servis;
		if(servis == null) {
			setTitle("Dodavanje servisa");

		}
		else {
			setTitle("Izmena podataka");
			//txtID.setEditable(false);
			cbAuto.setEnabled(false);
			
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
		setLayout(layout);
		
		for(Automobil auto : Liste.dostupniAutomobili) {
			cbAuto.addItem(auto.getId());
		}
		
		for(Serviser serviser : Liste.dostupniServiseri) {
			cbServiser.addItem(serviser.getId());
		}
		
		
		if(servis != null) {
			popuniPolja();
		}
		
		
		add(labelID);
		add(txtID);
		add(labelAuto);
		add(cbAuto);
		add(labelServiser);
		add(cbServiser);
		add(labelTermin);
		add(txtTermin);
		add(labelStatus);
		add(cbStatus);
		add(labelOpis);
		add(txtOpis);
		add(new JLabel());
		add(btnDelovi, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(servis.getId());
		cbAuto.setSelectedItem(servis.getServisiraniAuto().getId());
		cbStatus.setEditable(false);
		cbServiser.setSelectedItem(servis.getServiser().getId());
		txtTermin.setText(Entiteti.terminFormat.format(servis.getTermin().getTime()));
		cbStatus.setSelectedItem(servis.getStatus());
		txtOpis.setText(servis.getOpis());
	}
	
	public void initActions() {
		btnDelovi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					id = txtID.getText().trim();
					String AutoID = cbAuto.getSelectedItem().toString();
					Automobil auto = RadSaFajlovima.nadjiAutomobil(AutoID);
					String ServiserID = cbServiser.getSelectedItem().toString();
					Serviser serviser = RadSaFajlovima.nadjiServisera(ServiserID);
					String terminString = txtTermin.getText();
					GregorianCalendar termin = new GregorianCalendar();
					try {
						termin.setTime(Entiteti.terminFormat.parse(terminString));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Status status = (Status)cbStatus.getSelectedItem();
					String opis = txtOpis.getText().trim();
					
					dodavanje = new Servis(id, false, auto, serviser, new ArrayList<Deo>(), termin , status, opis);
					

					servis.setOpis(opis);
					servis.setServiser(serviser);
					System.out.println(servis.getOpis());
					System.out.println(servis.getServiser().getId());
					
					DodavanjeDelovaPrikaz p = new DodavanjeDelovaPrikaz(servis, dodavanje, id);
					p.setVisible(true);
					
					
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisForma.this.dispose();
				ServisForma.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Unesite ID\n";
			ok = false;
		}
		if(servis == null){
			String id = txtID.getText().trim();
			Servis pronadjeni = RadSaFajlovima.nadjiServis(id);
			if(pronadjeni != null) {
				poruka += "- Servis sa tim ID vec postoji\n";
				ok = false;
			}
		}
		
		try {
			Entiteti.terminFormat.parse(txtTermin.getText());
		}catch (Exception e) {
			poruka += "- Datum mora bit u formatu DD.MM.YYYY. HH:MM \n";
			ok = false;
		}
		
		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite opis\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
