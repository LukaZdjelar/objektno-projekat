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
import automobil.Gorivo;
import automobil.Marka;
import automobil.Model;
import fajlovi.RadSaFajlovima;
import net.miginfocom.swing.MigLayout;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Pol;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;
import rad_sa_entitetima.Liste;

public class AutoForma extends JFrame {
	private JLabel labelID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel labelMarka = new JLabel("Marka");
	private JComboBox<Marka> cbMarka = new JComboBox<Marka>(Marka.values());
	private JLabel labelModel = new JLabel("Marka");
	private JComboBox<Model> cbModel = new JComboBox<Model>(Model.values());
	private JLabel labelVlasnik = new JLabel("Vlasnik");
	private JComboBox<String> cbVlasnik = new JComboBox<String>();
	private JLabel labelGodiste = new JLabel("Godiste");
	private JTextField txtGodiste = new JTextField(20);
	private JLabel labelZapremina = new JLabel("Zapremina");
	private JTextField txtZapremina = new JTextField(20);
	private JLabel labelSnaga = new JLabel("Snaga");
	private JTextField txtSnaga = new JTextField(20);
	private JLabel labelGorivo = new JLabel("Gorivo");
	private JComboBox<Gorivo> cbGorivo = new JComboBox<Gorivo>(Gorivo.values());
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	Automobil auto;
	
	public AutoForma(Automobil auto) {
		this.auto = auto;
		if(auto == null) {
			setTitle("Dodavanje automobila");
		}
		else {
			setTitle("Izmena podataka");
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][]20[]");
		setLayout(layout);
		
		for(Musterija vlasnik : Liste.dostupneMusterije) {
			cbVlasnik.addItem(vlasnik.getId());
		}
		
		if(auto != null) {
			popuniPolja();
		}
		
		add(labelID);
		add(txtID);
		add(labelMarka);
		add(cbMarka);
		add(labelModel);
		add(cbModel);
		add(labelVlasnik);
		add(cbVlasnik);
		add(labelGodiste);
		add(txtGodiste);
		add(labelZapremina);
		add(txtZapremina);
		add(labelSnaga);
		add(txtSnaga);
		add(labelGorivo);
		add(cbGorivo);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtID.setText(auto.getId());
		cbMarka.setSelectedItem(auto.getMarka());
		cbModel.setSelectedItem(auto.getModel());
		Musterija vlasnik = RadSaFajlovima.nadjiVlasnikaAuto(auto);
		cbVlasnik.setSelectedItem(vlasnik.getId());
		txtGodiste.setText(auto.getGodiste());
		txtZapremina.setText(String.valueOf(auto.getZapremina()));
		txtSnaga.setText(String.valueOf(auto.getSnaga()));
		cbGorivo.setSelectedItem(auto.getGorivo());
		
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					Marka marka = (Marka)cbMarka.getSelectedItem();
					Model model= (Model)cbModel.getSelectedItem();
					String VlasnikID = cbVlasnik.getSelectedItem().toString();
					Musterija vlasnik = RadSaFajlovima.nadjiMusteriju(VlasnikID);
					String godiste = txtGodiste.getText().trim();
					int zapremina = Integer.parseInt(txtZapremina.getText().trim());
					int snaga = Integer.parseInt(txtSnaga.getText().trim());
					Gorivo gorivo= (Gorivo)cbGorivo.getSelectedItem();
					
					if(auto == null) { // DODAVANJE:
						Automobil novi = new Automobil(id, false, marka, model, vlasnik, godiste, zapremina, snaga, gorivo);
						Dodavanje.dodajAuto(novi);
					}
					else { // IZMENA:
						Izmene.izmenaAuta(auto, marka, model, vlasnik, godiste, zapremina, snaga, gorivo);
					}
					AutoForma.this.dispose();
					AutoForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoForma.this.dispose();
				AutoForma.this.setVisible(false);
				
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
		if(auto == null){
			String id = txtID.getText().trim();
			Automobil pronadjeni = RadSaFajlovima.nadjiAutomobil(id);
			if(pronadjeni != null) {
				poruka += "- Automobil sa tim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtGodiste.getText().trim().equals("")) {
			poruka += "- Unesite godiste\n";
			ok = false;
		}
		
		if(1980 > Integer.parseInt(txtGodiste.getText().trim()) || 2020 < Integer.parseInt(txtGodiste.getText().trim())) {
			poruka += "- Unesite godinu u opsegu 1980-2020\n";
			ok = false;
		}
		
		try {
			Double.parseDouble(txtZapremina.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Zapremina mora biti broj\n";
			ok = false;
		}
		
		try {
			Double.parseDouble(txtSnaga.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
