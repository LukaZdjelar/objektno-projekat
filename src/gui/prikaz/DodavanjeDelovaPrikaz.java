package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import automobil.Deo;
import fajlovi.RadSaFajlovima;
import gui.forme.AutoForma;
import gui.forme.ServisForma;
import rad_sa_entitetima.Dodavanje;
import rad_sa_entitetima.Izmene;
import rad_sa_entitetima.Liste;
import servis.Servis;

public class DodavanjeDelovaPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	
	private DefaultTableModel tableModel;
	private JTable deloviTabela;
	
	Servis servis;
	Servis dodavanje;
	String id;
	
	public DodavanjeDelovaPrikaz(Servis servis, Servis dodavanje, String id) {
		this.servis = servis;
		this.dodavanje = dodavanje;
		this.id = id;
		setTitle("Dodavanje delovi");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		Liste.dostupniDelovi = RadSaFajlovima.dostupniDelovi();
		
		mainToolbar.add(btnDodavanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		ArrayList<Deo> odgovarajuciDelovi = new ArrayList<Deo>();
		if(servis == null) {
			for (Deo deo : Liste.dostupniDelovi) {
				if(dodavanje.getServisiraniAuto().getMarka().equals(deo.getMarka())
				&& dodavanje.getServisiraniAuto().getModel().equals(deo.getModel())) {
					odgovarajuciDelovi.add(deo);
				}
			}
		}
		else {
			for (Deo deo : Liste.dostupniDelovi) {
				if(servis.getServisiraniAuto().getMarka().equals(deo.getMarka())
				&& servis.getServisiraniAuto().getModel().equals(deo.getModel())) {
					odgovarajuciDelovi.add(deo);
				}
			}
		}
		
		String[] heder = new String[] {"ID", "Naziv", "Marka", "Model", "Cena"};
		Object[][] sadrzaj = new Object[odgovarajuciDelovi.size()][heder.length];
		
		for (int i=0; i<odgovarajuciDelovi.size(); i++) {
				Deo deo = odgovarajuciDelovi.get(i);
				sadrzaj[i][0] = deo.getId();
				sadrzaj[i][1] = deo.getNaziv();
				sadrzaj[i][2] = deo.getMarka().toString();
				sadrzaj[i][3] = deo.getModel().toString();
				sadrzaj[i][4] = deo.getCena();
		}
		tableModel = new DefaultTableModel(sadrzaj, heder);
		deloviTabela = new JTable(tableModel);
		
		deloviTabela.setRowSelectionAllowed(true);
		deloviTabela.setColumnSelectionAllowed(false);
		deloviTabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		deloviTabela.setDefaultEditor(Object.class, null);
		deloviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(deloviTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDodavanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Deo> delovi = new ArrayList<Deo>();
				int[] redovi = deloviTabela.getSelectedRows();
				for (int red : redovi) {
					String id = tableModel.getValueAt(red, 0).toString();
					Deo deo = RadSaFajlovima.nadjiDeo(id);
					delovi.add(deo);
				}
				
				if(servis == null) {
					Servis novi = new Servis(dodavanje.getId() , false, dodavanje.getServisiraniAuto(), dodavanje.getServiser(), delovi, dodavanje.getTermin(), dodavanje.getStatus(), dodavanje.getOpis());
					Dodavanje.dodajServis(novi);
				}
				else {
					Izmene.izmenaServisa(servis, servis.getServisiraniAuto(), servis.getServiser(), delovi, servis.getTermin(), servis.getStatus(), servis.getOpis(), id);
				}
				DodavanjeDelovaPrikaz.this.dispose();
				DodavanjeDelovaPrikaz.this.setVisible(false);
			}
		});
	}
}
