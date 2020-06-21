package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import automobil.Deo;
import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.forme.ServisForma;
import gui.forme.ServisServiseraForma;
import osobe.Serviser;
import rad_sa_entitetima.Liste;
import servis.Servis;

public class ServiserServisiPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	private JButton btnIzmena = new JButton("Izmena");
	
	private DefaultTableModel tableModel;
	private JTable servisTabela;
	
	private ArrayList<Servis> servisiServisera = new ArrayList<Servis>();
	Serviser prijavljeniServiser;
	
	public ServiserServisiPrikaz(Serviser prijavljeniServiser) {
		this.prijavljeniServiser = prijavljeniServiser;
		setTitle("Servisi servisera, " + prijavljeniServiser.getIme() + " " + prijavljeniServiser.getPrezime());
		
		Liste.dostupniServisi = RadSaFajlovima.dostupniServisi();
		for (Servis servis : Liste.dostupniServisi) {
			if(prijavljeniServiser.getId().equals(servis.getServiser().getId())) {
				servisiServisera.add(servis);
			}
		}
		
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	private void initGUI() {
		mainToolbar.add(btnDodavanje);
		mainToolbar.add(btnIzmena);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Automobil", "Serviser", "Delovi", "Termin", "Status", "Opis"};
		Object[][] sadrzaj = new Object[servisiServisera.size()][heder.length];

		for(int i=0; i<servisiServisera.size(); i++) {
			Servis servis = servisiServisera.get(i);
			sadrzaj[i][0] = servis.getId();
			sadrzaj[i][1] = servis.getServisiraniAuto().getId();
			sadrzaj[i][2] = servis.getServiser().getId();
			String delovi = "";
			for (Deo deo : servis.getDelovi()) {
				delovi += deo.getId() + ";";
			}
			sadrzaj[i][3] = delovi;
			String termin = Entiteti.terminFormat.format(servis.getTermin().getTime());
			sadrzaj[i][4] = termin;
			sadrzaj[i][5] = servis.getStatus().toString();
			sadrzaj[i][6] = servis.getOpis();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, heder);
		servisTabela = new JTable(tableModel);
		
		servisTabela.setRowSelectionAllowed(true);
		servisTabela.setColumnSelectionAllowed(false);
		servisTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisTabela.setDefaultEditor(Object.class, null);
		servisTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(servisTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initActions() {
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisServiseraForma f = new ServisServiseraForma(null, prijavljeniServiser);
				f.setVisible(true);
			}
		});
		
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Servis servis = RadSaFajlovima.nadjiServis(id);
					if(servis == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja servisa sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
						ServisServiseraForma f = new ServisServiseraForma(servis, prijavljeniServiser);
					f.setVisible(true);
					}
				}
			}
		});
	}

}
