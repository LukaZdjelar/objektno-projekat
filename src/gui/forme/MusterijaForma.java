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
import osobe.Musterija;
import osobe.Pol;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;

public class MusterijaForma extends JFrame {
	
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
	private JLabel labelBodovi = new JLabel("Broj bodova");
	private JTextField txtBodovi = new JTextField(20);
	private JLabel labelKorIme = new JLabel("Korisnicko ime");
	private JTextField txtKorIme = new JTextField(20);
	private JLabel labelLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	Musterija musterija;
	
	public MusterijaForma(Musterija musterija) {
		this.musterija = musterija;
		if(musterija == null) {
			setTitle("Dodavanje administratora");
		}
		else {
			setTitle("Izmena podataka - " + musterija.getKor_ime());
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
		
		if(musterija != null) {
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
		add(labelBodovi);
		add(txtBodovi);
		add(labelKorIme);
		add(txtKorIme);
		add(labelLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(musterija.getId());
		txtIme.setText(musterija.getIme());
		txtPrezime.setText(musterija.getPrezime());
		txtJMBG.setText(musterija.getJmbg());
		cbPol.setSelectedItem(musterija.getPol());
		txtAdresa.setText(musterija.getAdresa());
		txtBrTel.setText(musterija.getBroj_tel());
		txtBodovi.setText(String.valueOf(musterija.getBrojBodova()));
		txtKorIme.setText(musterija.getKor_ime());
		pfLozinka.setText(musterija.getLozinka());
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
					String bodovi = txtBodovi.getText().trim();
					String korIme = txtKorIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Pol pol = (Pol)cbPol.getSelectedItem();
					
					if(musterija == null) { // DODAVANJE:
						Musterija novi = new Musterija(id, false, ime, prezime, jmbg, pol, adresa, broj_tel, korIme, lozinka, Integer.parseInt(bodovi));
						Dodavanje.dodajMusteriju(novi);
					}
					else { // IZMENA:
						Izmene.IzmenaMusterije(musterija, ime, prezime, jmbg, pol, adresa, broj_tel, korIme, lozinka, Integer.parseInt(bodovi));
					}
					MusterijaForma.this.dispose();
					MusterijaForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijaForma.this.dispose();
				MusterijaForma.this.setVisible(false);
				
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
		if(musterija == null){
			String id = txtID.getText().trim();
			Musterija pronadjeni = RadSaFajlovima.nadjiMusteriju(id);
			if(pronadjeni != null) {
				poruka += "- Musterija sa tim ID vec postoji\n";
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
			Integer.parseInt(txtBodovi.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Broj bodova mora biti broj\n";
			ok = false;
		}
		
		if(txtKorIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		
		if(musterija == null){
			String korIme = txtKorIme.getText().trim();
			Musterija pronadjeni = RadSaFajlovima.nadjiMusterijuKorIme(korIme);
			if(pronadjeni != null) {
				poruka += "- Musterija sa tim Korisnickim imenom vec postoji\n";
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
