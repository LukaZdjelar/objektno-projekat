package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.prikaz.AdministratoriPrikaz;
import net.miginfocom.swing.MigLayout;
import osobe.Administrator;
import osobe.Pol;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;

public class AdminForma extends JFrame{
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
	private JTextField txtPlata = new JTextField(20);
	private JLabel labelKorIme = new JLabel("Korisnicko ime");
	private JTextField txtKorIme = new JTextField(20);
	private JLabel labelLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	Administrator admin;

	public AdminForma(Administrator admin) {
		this.admin = admin;
		if(admin == null) {
			setTitle("Dodavanje administratora");
		}
		else {
			setTitle("Izmena podataka - " + admin.getKor_ime());
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][][]20[]");
		setLayout(layout);
		
		if(admin != null) {
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
		add(labelKorIme);
		add(txtKorIme);
		add(labelLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(admin.getId());
		txtIme.setText(admin.getIme());
		txtPrezime.setText(admin.getPrezime());
		txtJMBG.setText(admin.getJmbg());
		cbPol.setSelectedItem(admin.getPol());
		txtAdresa.setText(admin.getAdresa());
		txtBrTel.setText(admin.getBroj_tel());
		txtPlata.setText(String.valueOf(admin.getPlata()));
		txtKorIme.setText(admin.getKor_ime());
		pfLozinka.setText(admin.getLozinka());
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
					
					if(admin == null) { // DODAVANJE:
						Administrator novi = new Administrator(id, false, ime, prezime, jmbg, pol, adresa, broj_tel, korIme, lozinka, Double.parseDouble(plata));
						Dodavanje.dodajAdmina(novi);
					}
					else { // IZMENA:
						Izmene.IzmenaAdmina(admin, ime, prezime, jmbg, pol, adresa, broj_tel, korIme, lozinka, Double.parseDouble(plata));
					}
					AdminForma.this.dispose();
					AdminForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminForma.this.dispose();
				AdminForma.this.setVisible(false);
				
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
		if(admin == null){
			String id = txtID.getText().trim();
			Administrator pronadjeni = RadSaFajlovima.nadjiAdministratora(id);
			if(pronadjeni != null) {
				poruka += "- Prodavac sa tim ID vec postoji\n";
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
		
		if(admin == null){
			String korIme = txtKorIme.getText().trim();
			Administrator pronadjeni = RadSaFajlovima.nadjiAdministratoraKorIme(korIme);
			if(pronadjeni != null) {
				poruka += "- Prodavac sa tim Korisnickim imenom vec postoji\n";
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
