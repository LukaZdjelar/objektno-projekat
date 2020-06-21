package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import automobil.Automobil;
import fajlovi.RadSaFajlovima;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Liste;
import servis.Servis;
import servis.ServisnaKnjizica;

public class SKForma extends JFrame {
	private JLabel labelID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel txtAuto = new JLabel("Automobil");
	private JComboBox<String> cbAuto = new JComboBox<String>();
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	ServisnaKnjizica sk;
	
	public SKForma(ServisnaKnjizica sk) {
		this.sk = sk;
		if(sk == null) {
			setTitle("Dodavanje Servisne knjizice");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][]20[]");
		setLayout(layout);
		
		for(Automobil auto : RadSaFajlovima.automobiliBezSK()) {
			cbAuto.addItem(auto.getId());
		}
		
		add(labelID);
		add(txtID);
		add(txtAuto);
		add(cbAuto);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					String AutoID = cbAuto.getSelectedItem().toString();
					Automobil automobil = RadSaFajlovima.nadjiAutomobil(AutoID);
					ArrayList<Servis> servisi = new ArrayList<Servis>();
					
					if(sk == null) {
						ServisnaKnjizica nova = new ServisnaKnjizica(id, false, automobil, servisi);
						Dodavanje.dodajSK(nova);
					}
					SKForma.this.dispose();
					SKForma.this.setVisible(false);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SKForma.this.dispose();
				SKForma.this.setVisible(false);
				
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
		if(sk == null){
			String id = txtID.getText().trim();
			ServisnaKnjizica pronadjena = RadSaFajlovima.nadjiSK(id);
			if(pronadjena != null) {
				poruka += "- Servisna Knjizica sa tim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
