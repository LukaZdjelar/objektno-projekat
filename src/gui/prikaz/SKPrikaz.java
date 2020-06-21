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
import automobil.Deo;
import fajlovi.RadSaFajlovima;
import gui.forme.AutoForma;
import gui.forme.SKForma;
import rad_sa_entitetima.Brisanje;
import rad_sa_entitetima.Liste;
import servis.Servis;
import servis.ServisnaKnjizica;

public class SKPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnDodavanje = new JButton("Dodavanje");
	//private JButton btnIzmena = new JButton("Izmena");
	private JButton btnBrisanje = new JButton("Brisanje");
	
	private DefaultTableModel tableModel;
	private JTable skTabela;
	
	public SKPrikaz() {
		setTitle("Automobili");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		Liste.dostupneSK = RadSaFajlovima.dostupneSK();
		mainToolbar.add(btnDodavanje);
		//mainToolbar.add(btnIzmena);
		mainToolbar.add(btnBrisanje);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] heder = new String[] {"ID", "Automobil", "Obavljeni servisi"};
		Object[][] sadrzaj = new Object[Liste.dostupneSK.size()][heder.length];
		
		for(int i=0; i<Liste.dostupneSK.size(); i++) {
			ServisnaKnjizica sk = Liste.dostupneSK.get(i);
			sadrzaj[i][0] = sk.getId();
			sadrzaj[i][1] = sk.getAutomobil().getId();
			String uradjeniServisi = "";
			for (Servis servis : sk.getServisi()) {
				uradjeniServisi += servis.getId() + ";";
			}
			sadrzaj[i][2] = uradjeniServisi;
		}
		
		tableModel = new DefaultTableModel(sadrzaj, heder);
		skTabela = new JTable(tableModel);
		
		skTabela.setRowSelectionAllowed(true);
		skTabela.setColumnSelectionAllowed(false);
		skTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		skTabela.setDefaultEditor(Object.class, null);
		skTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(skTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnBrisanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = skTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = skTabela.getValueAt(red, 0).toString();
					ServisnaKnjizica sk = RadSaFajlovima.nadjiSK(id);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete prodavca?", 
							sk.getId() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Brisanje.izbrisiSK(sk);
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodavanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SKForma f = new SKForma(null);
				f.setVisible(true);
			}
		});
		/*
		btnIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = skTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(red, 0).toString();
					ServisnaKnjizica sk = RadSaFajlovima.nadjiSK(id);
					if(sk == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja automobila sa tim ID", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
					SKForma f = new SKForma(sk);
					f.setVisible(true);
					}
				}
			}
		});
		*/
	}
}
