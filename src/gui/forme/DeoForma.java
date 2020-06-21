package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import automobil.Automobil;
import automobil.Deo;
import automobil.Gorivo;
import automobil.Marka;
import automobil.Model;
import fajlovi.RadSaFajlovima;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;

public class DeoForma extends JFrame {
	private JLabel labelID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel labelNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel labelMarka = new JLabel("Marka");
	private JComboBox<Marka> cbMarka = new JComboBox<Marka>(Marka.values());
	private JLabel labelModel = new JLabel("Marka");
	private JComboBox<Model> cbModel = new JComboBox<Model>(Model.values());
	private JLabel labelCena = new JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	Deo deo;
	
	public DeoForma(Deo deo) {
		this.deo = deo;
		if(deo == null) {
			setTitle("Dodavanje dela");
		}
		else {
			setTitle("Izmena podataka dela");
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(deo != null) {
			popuniPolja();
		}
		
		add(labelID);
		add(txtID);
		add(labelNaziv);
		add(txtNaziv);
		add(labelMarka);
		add(cbMarka);
		add(labelModel);
		add(cbModel);
		add(labelCena);
		add(txtCena);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(deo.getId());
		txtNaziv.setText(deo.getNaziv());
		cbMarka.setSelectedItem(deo.getMarka());
		cbModel.setSelectedItem(deo.getModel());
		txtCena.setText(String.valueOf(deo.getCena()));
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					String naziv = txtNaziv.getText().trim();
					Marka marka = (Marka)cbMarka.getSelectedItem();
					Model model= (Model)cbModel.getSelectedItem();
					int cena = Integer.parseInt(txtCena.getText().trim());
					
					if(deo == null) {
						Deo novi = new Deo(id, false, marka, model, naziv, cena);
						Dodavanje.dodajDeo(novi);
					}
					else {
						Izmene.izmenaDela(deo, marka, model, naziv, cena);
					}
					DeoForma.this.dispose();
					DeoForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeoForma.this.dispose();
				DeoForma.this.setVisible(false);
				
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
		if(deo == null){
			String id = txtID.getText().trim();
			Deo pronadjeni = RadSaFajlovima.nadjiDeo(id);
			if(pronadjeni != null) {
				poruka += "- Deo sa tim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Unesite naziv\n";
			ok = false;
		}
		
		try {
			Double.parseDouble(txtCena.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Cena mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
