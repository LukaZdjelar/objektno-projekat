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
import gui.forme.AdminForma;
import gui.forme.ServiserForma;
import osobe.Administrator;
import osobe.Serviser;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Liste;

public class ServiseriPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	private JButton btnIzmena = new JButton("Izmena");
	private JButton btnBrisanje = new JButton("Brisanje");
	
	private DefaultTableModel tableModel;
	private JTable serviserTabela;
	
	public ServiseriPrikaz() {
		setTitle("Serviseri");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		Liste.dostupniServiseri = RadSaFajlovima.dostupniServiseri();
		mainToolbar.add(btnDodavanje);
		mainToolbar.add(btnIzmena);
		mainToolbar.add(btnBrisanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj telefona", "Plata","Specijalizacija", "Korisnicko ime", "Sifra"};
		Object[][] sadrzaj = new Object[Liste.dostupniServiseri.size()][heder.length];
		
		for(int i=0; i<Liste.dostupniServiseri.size(); i++) {
			Serviser serviser = Liste.dostupniServiseri.get(i);
			sadrzaj[i][0] = serviser.getId();
			sadrzaj[i][1] = serviser.getIme();
			sadrzaj[i][2] = serviser.getPrezime();
			sadrzaj[i][3] = serviser.getJmbg();
			sadrzaj[i][4] = serviser.getPol().toString();
			sadrzaj[i][5] = serviser.getAdresa();
			sadrzaj[i][6] = serviser.getBroj_tel();
			sadrzaj[i][7] = serviser.getPlata();	
			sadrzaj[i][8] = serviser.getSpecijalizacija().toString();
			sadrzaj[i][9] = serviser.getKor_ime();
			sadrzaj[i][10] = serviser.getLozinka();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, heder);
		serviserTabela = new JTable(tableModel);
		
		serviserTabela.setRowSelectionAllowed(true);
		serviserTabela.setColumnSelectionAllowed(false);
		serviserTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviserTabela.setDefaultEditor(Object.class, null);
		serviserTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(serviserTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnBrisanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviserTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Serviser serviser = RadSaFajlovima.nadjiServisera(id);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete prodavca?", 
							 serviser.getKor_ime() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Brisanje.izbrisiServisera(serviser);
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiserForma f = new ServiserForma(null);
				f.setVisible(true);
			}
		});
		
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviserTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Serviser serviser = RadSaFajlovima.nadjiServisera(id);
					if(serviser == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja prodavca sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
						ServiserForma f = new ServiserForma(serviser);
						f.setVisible(true);
					}
				}
			}
		});
	}
}
