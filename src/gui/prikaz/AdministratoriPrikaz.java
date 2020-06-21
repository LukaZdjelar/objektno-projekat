package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import fajlovi.Entiteti;
import fajlovi.RadSaFajlovima;
import gui.forme.AdminForma;
import osobe.Administrator;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Liste;

public class AdministratoriPrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	private JButton btnIzmena = new JButton("Izmena");
	private JButton btnBrisanje = new JButton("Brisanje");
	
	private DefaultTableModel tableModel;
	private JTable adminTabela;
	
	public AdministratoriPrikaz() {
		setTitle("Administratori");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		Liste.dostupniAdministratori = RadSaFajlovima.dostupniAdmini();
		mainToolbar.add(btnDodavanje);
		mainToolbar.add(btnIzmena);
		mainToolbar.add(btnBrisanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj telefona", "Plata", "Korisnicko ime", "Sifra"};
		Object[][] sadrzaj = new Object[Liste.dostupniAdministratori.size()][heder.length];
		
		for(int i=0; i<Liste.dostupniAdministratori.size(); i++) {
			Administrator admin = Liste.dostupniAdministratori.get(i);
			sadrzaj[i][0] = admin.getId();
			sadrzaj[i][1] = admin.getIme();
			sadrzaj[i][2] = admin.getPrezime();
			sadrzaj[i][3] = admin.getJmbg();
			sadrzaj[i][4] = admin.getPol().toString();
			sadrzaj[i][5] = admin.getAdresa();
			sadrzaj[i][6] = admin.getBroj_tel();
			sadrzaj[i][7] = admin.getPlata();
			sadrzaj[i][8] = admin.getKor_ime();
			sadrzaj[i][9] = admin.getLozinka();	
		}
		tableModel = new DefaultTableModel(sadrzaj, heder);
		adminTabela = new JTable(tableModel);
		
		adminTabela.setRowSelectionAllowed(true);
		adminTabela.setColumnSelectionAllowed(false);
		adminTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminTabela.setDefaultEditor(Object.class, null);
		adminTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(adminTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnBrisanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					Administrator admin = RadSaFajlovima.nadjiAdministratora(id);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete prodavca?", 
							 admin.getKor_ime() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Brisanje.izbrisiAdmina(admin);
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminForma f = new AdminForma(null);
				f.setVisible(true);
			}
		});
		
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Administrator admin = RadSaFajlovima.nadjiAdministratora(id);
					if(admin == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja prodavca sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
					AdminForma f = new AdminForma(admin);
					f.setVisible(true);
					}
				}
			}
		});
	}
}
