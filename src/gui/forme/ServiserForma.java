package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fajlovi.RadSaFajlovima;
import net.miginfocom.swing.MigLayout;
import osobe.Administrator;
import osobe.Pol;
import osobe.Serviser;
import osobe.Specijalizacija;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;

public class ServiserForma extends JFrame {
	private JLabel labelID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel labelIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel labelPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel labelJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel labelPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel labelAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel labelBrTel = new JLabel("Broj Telefona");
	private JTextField txtBrTel= new JTextField(20);
	private JLabel labelPlata = new JLabel("Plata");
	private JTextField txtPlata= new JTextField(20);
	private JLabel labelSpec = new JLabel("Specijalizacija");
	private JComboBox<Specijalizacija> cbSpec = new JComboBox<Specijalizacija>(Specijalizacija.values()); 
	private JLabel labelKorIme = new JLabel("Korisnicko ime");
	private JTextField txtKorIme = new JTextField(20);
	private JLabel labelLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	Serviser serviser;
	
	
	public ServiserForma(Serviser serviser) {
		this.serviser = serviser;
		if(serviser == null) {
			setTitle("Dodavanje administratora");
		}
		else {
			setTitle("Izmena podataka - " + serviser.getKor_ime());
			txtID.setEditable(false);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][][][]20[]");
		setLayout(layout);
		
		if(serviser != null) {
			popuniPolja();
		}
		

		
		add(labelID);
		add(txtID);
		add(labelIme);
		add(txtIme);
		add(labelPrezime);
		add(txtPrezime);
		add(labelJMBG);
		add(txtJMBG);
		add(labelPol);
		add(cbPol);
		add(labelAdresa);
		add(txtAdresa);
		add(labelBrTel);
		add(txtBrTel);
		add(labelPlata);
		add(txtPlata);
		add(labelSpec);
		add(cbSpec);
		add(labelKorIme);
		add(txtKorIme);
		add(labelLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(serviser.getId());
		txtIme.setText(serviser.getIme());
		txtPrezime.setText(serviser.getPrezime());
		txtJMBG.setText(serviser.getJmbg());
		cbPol.setSelectedItem(serviser.getPol());
		txtAdresa.setText(serviser.getAdresa());
		txtBrTel.setText(serviser.getBroj_tel());
		txtPlata.setText(String.valueOf(serviser.getPlata()));
		cbSpec.setSelectedItem(serviser.getSpecijalizacija());
		txtKorIme.setText(serviser.getKor_ime());
		pfLozinka.setText(serviser.getLozinka());
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJMBG.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String broj_tel = txtBrTel.getText().trim();
					String plata = txtPlata.getText().trim();
					String korIme = txtKorIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Pol pol = (Pol)cbPol.getSelectedItem();
					Specijalizacija specijalizacija = (Specijalizacija)cbSpec.getSelectedItem();
					
					if(serviser == null) { // DODAVANJE:
						Serviser novi = new Serviser(id, false, ime, prezime, jmbg, pol, adresa, broj_tel, korIme, lozinka, Double.parseDouble(plata), specijalizacija);
						Dodavanje.dodajServisera(novi);
					}
					else { // IZMENA:
						Izmene.IzmenaServisera(serviser, ime, prezime, jmbg, pol, adresa, broj_tel, korIme, lozinka, Double.parseDouble(plata), specijalizacija);
					}
					ServiserForma.this.dispose();
					ServiserForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiserForma.this.dispose();
				ServiserForma.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Unesite id\n";
			ok = false;
		}
		if(serviser == null){
			String id = txtID.getText().trim();
			Serviser pronadjeni = RadSaFajlovima.nadjiServisera(id);
			if(pronadjeni != null) {
				poruka += "- Serviser sa tim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		
		if(txtJMBG.getText().trim().length() != 13) {
			poruka += "- Unesite JMBG sa 13 brojeva\n";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}
		
		if(txtBrTel.getText().trim().equals("")) {
			poruka += "- Unesite broj telefona\n";
			ok = false;
		}
		
		try {
			Integer.parseInt(txtBrTel.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Broj telefona mora biti broj\n";
			ok = false;
		}
		
		try {
			Double.parseDouble(txtPlata.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		
		if(txtKorIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		
		if(serviser == null){
			String korIme = txtKorIme.getText().trim();
			Serviser pronadjeni = RadSaFajlovima.nadjiServiseraKorIme(korIme);
			if(pronadjeni != null) {
				poruka += "- Serviser sa tim Korisnickim imenom vec postoji\n";
				ok = false;
			}
		}

		String sifra = new String(pfLozinka.getPassword()).trim();
		if(sifra.equals("")) {
			poruka += "- Unesite sifru\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
