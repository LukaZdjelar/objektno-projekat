package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import automobil.Automobil;
import automobil.Gorivo;
import automobil.Marka;
import automobil.Model;
import fajlovi.RadSaFajlovima;
import gui.forme.AdminForma;
import gui.forme.AutoForma;
import osobe.Administrator;
import osobe.Musterija;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Liste;

public class AutoPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	private JButton btnIzmena = new JButton("Izmena");
	private JButton btnBrisanje = new JButton("Brisanje");
	
	private DefaultTableModel tableModel;
	private JTable autoTabela;
	
	public AutoPrikaz() {
		setTitle("Automobili");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI(){
		Liste.dostupniAutomobili = RadSaFajlovima.dostupniAutomobili();
		mainToolbar.add(btnDodavanje);
		mainToolbar.add(btnIzmena);
		mainToolbar.add(btnBrisanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Marka", "Model", "Vlasnik", "Godiste", "Zapremina", "Snaga", "Vrsta gorivo"};
		Object[][] sadrzaj = new Object[Liste.dostupniAutomobili.size()][heder.length];
		
		for(int i=0; i<Liste.dostupniAutomobili.size(); i++) {
			Automobil auto = Liste.dostupniAutomobili.get(i);
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
	
	private void initActions() {
		btnBrisanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = autoTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Automobil automobil= RadSaFajlovima.nadjiAutomobil(id);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete prodavca?", 
							automobil.getId() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Brisanje.izbrisiAuto(automobil);
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoForma f = new AutoForma(null);
				f.setVisible(true);
			}
		});
		
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = autoTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Automobil auto = RadSaFajlovima.nadjiAutomobil(id);
					if(auto == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja automobila sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
					AutoForma f = new AutoForma(auto);
					f.setVisible(true);
					}
				}
			}
		});
	}
}
