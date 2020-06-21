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

import fajlovi.RadSaFajlovima;
import gui.forme.MusterijaForma;
import gui.forme.ServiserForma;
import osobe.Musterija;
import osobe.Serviser;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Liste;

public class MusterijePrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	private JButton btnIzmena = new JButton("Izmena");
	private JButton btnBrisanje = new JButton("Brisanje");
	
	private DefaultTableModel tableModel;
	private JTable MusterijaTabela;
	
	public MusterijePrikaz() {
		setTitle("Musterije");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		Liste.dostupneMusterije = RadSaFajlovima.dostupneMusterije();
		mainToolbar.add(btnDodavanje);
		mainToolbar.add(btnIzmena);
		mainToolbar.add(btnBrisanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj telefona", "Broj bodova", "Korisnicko ime", "Sifra"};
		Object[][] sadrzaj = new Object[Liste.dostupneMusterije.size()][heder.length];
		
		for(int i=0; i<Liste.dostupneMusterije.size(); i++) {
			Musterija musterija = Liste.dostupneMusterije.get(i);
			sadrzaj[i][0] = musterija.getId();
			sadrzaj[i][1] = musterija.getIme();
			sadrzaj[i][2] = musterija.getPrezime();
			sadrzaj[i][3] = musterija.getJmbg();
			sadrzaj[i][4] = musterija.getPol().toString();
			sadrzaj[i][5] = musterija.getAdresa();
			sadrzaj[i][6] = musterija.getBroj_tel();
			sadrzaj[i][7] = musterija.getBrojBodova();
			sadrzaj[i][8] = musterija.getKor_ime();
			sadrzaj[i][9] = musterija.getLozinka();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, heder);
		MusterijaTabela = new JTable(tableModel);
		
		MusterijaTabela.setRowSelectionAllowed(true);
		MusterijaTabela.setColumnSelectionAllowed(false);
		MusterijaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MusterijaTabela.setDefaultEditor(Object.class, null);
		MusterijaTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(MusterijaTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnBrisanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = MusterijaTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Musterija musterija = RadSaFajlovima.nadjiMusteriju(id);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete prodavca?", 
							 musterija.getKor_ime() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Brisanje.izbrisiMusteriju(musterija);
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijaForma f = new MusterijaForma(null);
				f.setVisible(true);
			}
		});
		
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = MusterijaTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Musterija musterija = RadSaFajlovima.nadjiMusteriju(id);
					if(musterija == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja prodavca sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
						MusterijaForma f = new MusterijaForma(musterija);
						f.setVisible(true);
					}
				}
			}
		});
	}
}
