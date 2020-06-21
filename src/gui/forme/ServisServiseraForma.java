package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import automobil.Automobil;
import automobil.Deo;
import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.prikaz.DodavanjeDelovaPrikaz;
import net.miginfocom.swing.MigLayout;
import osobe.Serviser;
import rad_sa_entitetima.Izmene;
import rad_sa_entitetima.Liste;
import servis.Servis;
import servis.Status;

public class ServisServiseraForma extends JFrame {
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
	
	private JButton btnOK = new JButton("Dodaj delove");
	private JButton btnCancel = new JButton("Cancel");
	
	Servis servis;
	Servis dodavanje;
	
	public ServisServiseraForma(Servis servis, Serviser prijavljeni) {
		this.servis = servis;
		cbServiser.addItem(prijavljeni.getId());
		if(servis == null) {
			setTitle("Dodavanje servisa");
			btnOK.setText("Dodajavnje delova");
			cbServiser.setSelectedItem(prijavljeni);
			cbServiser.setEnabled(false);
			cbStatus.setEnabled(false);
		}
		else {
			setTitle("Izmena podataka servisa");
			btnOK.setText("Izmeni");
			txtID.setEditable(false);
			cbAuto.setEnabled(false);
			cbServiser.setSelectedItem(prijavljeni);
			cbServiser.setEnabled(false);
			txtTermin.setEditable(false);
			cbStatus.setEnabled(false);
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
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(servis.getId());
		cbAuto.setSelectedItem(servis.getServisiraniAuto());
		cbServiser.setSelectedItem(servis.getServiser());
		txtTermin.setText(Entiteti.terminFormat.format(servis.getTermin().getTime()));
		cbStatus.setSelectedItem(servis.getStatus());
		txtOpis.setText(servis.getOpis());
	}
	public void initActions() {	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				String id = txtID.getText().trim();
				String AutoID= cbAuto.getSelectedItem().toString();
				Automobil auto = RadSaFajlovima.nadjiAutomobil(AutoID);
				String ServiserID= cbServiser.getSelectedItem().toString();
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
				if(servis == null) {
					DodavanjeDelovaPrikaz p = new DodavanjeDelovaPrikaz(servis, dodavanje, null);
					p.setVisible(true);

				}else {
					Izmene.izmenaServisa(servis, auto, serviser, servis.getDelovi(), termin, status, opis, id);
					ServisServiseraForma.this.dispose();
					ServisServiseraForma.this.setVisible(false);
				}
			}
		}
	});
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ServisServiseraForma.this.dispose();
			ServisServiseraForma.this.setVisible(false);
			
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
