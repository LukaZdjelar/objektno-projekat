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

import automobil.Deo;
import fajlovi.RadSaFajlovima;
import gui.forme.AdminForma;
import gui.forme.DeoForma;
import osobe.Administrator;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Liste;

public class DeloviPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	private JButton btnIzmena = new JButton("Izmena");
	private JButton btnBrisanje = new JButton("Brisanje");
	
	private DefaultTableModel tableModel;
	private JTable deoTabela;
	
	public DeloviPrikaz() {
		setTitle("Delovi");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		Liste.dostupniDelovi = RadSaFajlovima.dostupniDelovi();
		
		mainToolbar.add(btnDodavanje);
		mainToolbar.add(btnIzmena);
		mainToolbar.add(btnBrisanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Naziv", "Marka", "Model", "Cena"};
		Object[][] sadrzaj = new Object[Liste.dostupniDelovi.size()][heder.length];
		
		for (int i=0; i<Liste.dostupniDelovi.size(); i++) {
			Deo deo = Liste.dostupniDelovi.get(i);
			sadrzaj[i][0] = deo.getId();
			sadrzaj[i][1] = deo.getNaziv();
			sadrzaj[i][2] = deo.getMarka().toString();
			sadrzaj[i][3] = deo.getModel().toString();
			sadrzaj[i][4] = deo.getCena();
		}
		tableModel = new DefaultTableModel(sadrzaj, heder);
		deoTabela = new JTable(tableModel);
		
		deoTabela.setRowSelectionAllowed(true);
		deoTabela.setColumnSelectionAllowed(false);
		deoTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deoTabela.setDefaultEditor(Object.class, null);
		deoTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(deoTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnBrisanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deoTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Deo deo= RadSaFajlovima.nadjiDeo(id);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete deo?", 
							 deo.getNaziv() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Brisanje.izbrisiDeo(deo);
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeoForma f = new DeoForma(null);
				f.setVisible(true);
			}
		});
		
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deoTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Deo deo = RadSaFajlovima.nadjiDeo(id);
					if(deo == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja prodavca sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
					DeoForma f = new DeoForma(deo);
					f.setVisible(true);
					}
				}
			}
		});
	}
}
