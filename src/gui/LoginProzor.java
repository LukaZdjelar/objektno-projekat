package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import autoservis.AutoServis;
import net.miginfocom.swing.MigLayout;
import osobe.Administrator;
import osobe.Musterija;
import osobe.Serviser;

public class LoginProzor extends JFrame{

	private JLabel labelTekst = new JLabel("Dobrodošli. Molimo da se prijavite.");
	private JLabel labelKorisnickoIme = new JLabel("Korisničko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel labelSifra = new JLabel("Šifra");
	private JPasswordField pfSifra = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	public LoginProzor() {
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(labelTekst, "span 2");
		add(labelKorisnickoIme);
		add(txtKorisnickoIme);
		add(labelSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		
		txtKorisnickoIme.setText("stefann");
		pfSifra.setText("stefan123");
		getRootPane().setDefaultButton(btnOk);
	}
	
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String lozinka = new String(pfSifra.getPassword()).trim();
				
				if(korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					Musterija prijavljeniMusterija = AutoServis.loginM(korisnickoIme, lozinka);
					Administrator prijavljeniAdmin = AutoServis.loginA(korisnickoIme, lozinka);
					Serviser prijavljeniServiser = AutoServis.loginS(korisnickoIme, lozinka);
					if(prijavljeniMusterija == null && prijavljeniAdmin == null && prijavljeniServiser == null) {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci.", "Greška", JOptionPane.WARNING_MESSAGE);
					}
					else if(prijavljeniMusterija != null){
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						GlavniProzorMusterija gp = new GlavniProzorMusterija(prijavljeniMusterija);
						gp.setVisible(true);
					}
					else if(prijavljeniAdmin != null){
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						GlavniProzorAdmin gp = new GlavniProzorAdmin(prijavljeniAdmin);
						gp.setVisible(true);
					}
					else if(prijavljeniServiser != null) {
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						GlavniProzorServiser gp = new GlavniProzorServiser(prijavljeniServiser);
						gp.setVisible(true);
					}
				}
			}
		});
	}
}
