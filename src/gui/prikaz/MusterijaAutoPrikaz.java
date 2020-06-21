package gui.prikaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import automobil.Automobil;
import fajlovi.RadSaFajlovima;
import osobe.Musterija;
import rad_sa_entitetima.Liste;

public class MusterijaAutoPrikaz extends JFrame {
	
	private DefaultTableModel tableModel;
	private JTable autoTabela;
	
	ArrayList<Automobil> listaAutomobila = new ArrayList<Automobil>();
	Musterija prijavljeniMusterija;
	
	public MusterijaAutoPrikaz(Musterija prijavljeniMusterija) {
		this.prijavljeniMusterija = prijavljeniMusterija;
		setTitle("Automobili musterije, " + prijavljeniMusterija.getIme() + " " + prijavljeniMusterija.getPrezime());
	
		Liste.dostupniAutomobili = RadSaFajlovima.dostupniAutomobili();
		for (Automobil automobil : Liste.dostupniAutomobili) {
			if(prijavljeniMusterija.getId().equals(automobil.getVlasnik().getId())) {
				listaAutomobila.add(automobil);
			}
		}
		
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
	}
	private void initGUI() {
		String[] heder = new String[] {"ID", "Marka", "Model", "Vlasnik", "Godiste", "Zapremina", "Snaga", "Vrsta gorivo"};
		Object[][] sadrzaj = new Object[listaAutomobila.size()][heder.length];

		for(int i=0; i<listaAutomobila.size(); i++) {
			Automobil auto = listaAutomobila.get(i);
			sadrzaj[i][0] = auto.getId();
			sadrzaj[i][1] = auto.getMarka().toString();
			sadrzaj[i][2] = auto.getModel().toString();
			sadrzaj[i][3] =	auto.getVlasnik().getId();
			sadrzaj[i][4] = auto.getGodiste();
			sadrzaj[i][5] = auto.getZapremina();
			sadrzaj[i][6] = auto.getSnaga();
			sadrzaj[i][7] = auto.getGorivo().toString();
		}
		tableModel = new DefaultTableModel(sadrzaj, heder);
		autoTabela = new JTable(tableModel);
		
		autoTabela.setRowSelectionAllowed(true);
		autoTabela.setColumnSelectionAllowed(false);
		autoTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		autoTabela.setDefaultEditor(Object.class, null);
		autoTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(autoTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
}
