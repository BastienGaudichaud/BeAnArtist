package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.eseo.poo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public class OutilSelectionner extends Outil{

	private static final long serialVersionUID = 1L;
	private JFrame infos;

	public OutilSelectionner() {
		this.vueFormeSelectionnee = null;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(this.vueFormeSelectionnee != null) {
			int character = e.getKeyCode();
			Forme forme = vueFormeSelectionnee.getForme();
			switch (character) {
			case KeyEvent.VK_DOWN:
				forme.deplacerDe(0, 1);
				break;
			case KeyEvent.VK_LEFT:
				forme.deplacerDe(-1, 0);
				break;
			case KeyEvent.VK_UP:
				forme.deplacerDe(0, -1);
				break;
			case KeyEvent.VK_RIGHT:
				forme.deplacerDe(1, 0);
				break;
			case KeyEvent.VK_NUMPAD0:
				forme.deplacerVers(0, 0);
				break;
			case KeyEvent.VK_NUMPAD1:
				forme.deplacerDe(-10, 10);
				break;
			case KeyEvent.VK_NUMPAD2:
				forme.deplacerDe(0, 10);
				break;
			case KeyEvent.VK_NUMPAD3:
				forme.deplacerDe(10, 10);
				break;
			case KeyEvent.VK_NUMPAD4:
				forme.deplacerDe(-10, 0);
				break;
			case KeyEvent.VK_NUMPAD5:
				JOptionPane.showMessageDialog(getPanneauDessin(), forme, ActionSelectionner.NOM_ACTION, JOptionPane.INFORMATION_MESSAGE);
				break;
			case KeyEvent.VK_NUMPAD6:
				forme.deplacerDe(10, 0);
				break;
			case KeyEvent.VK_NUMPAD7:
				forme.deplacerDe(-10, -10);
				break;
			case KeyEvent.VK_NUMPAD8:
				forme.deplacerDe(0, -10);
				break;
			case KeyEvent.VK_NUMPAD9:
				forme.deplacerDe(10, -10);
				break;
			case KeyEvent.VK_ESCAPE:
				JOptionPane.showMessageDialog(getPanneauDessin(), this.vueFormeSelectionnee.getForme(), ActionSelectionner.NOM_ACTION, JOptionPane.INFORMATION_MESSAGE);
				this.vueFormeSelectionnee = null;
				infos.dispose();
			default:
				break;
			}
			getPanneauDessin().repaint();
		}
	}

	/* Launch the appropriate action when a button is clicked. */
	@Override
	public void mouseClicked(MouseEvent event) {
		try {infos.dispose();} catch (Exception e) {}
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
		if (event.getButton() == MouseEvent.BUTTON1) {
			clicGauche(event);
		}
		else {
			if (event.getButton() == MouseEvent.BUTTON3 && this.vueFormeSelectionnee != null) {
				clicDroit();
			}
		}
	}
	
	/* If it is a left click, it launch the object selection process or move the selected object. */
	private void clicGauche(MouseEvent event) {
		if(this.vueFormeSelectionnee == null) {
			List<VueForme> bonneForme = new ArrayList<>();
			Coordonnees click = new Coordonnees(event.getX(), event.getY());
			List<VueForme> vueFormes = getPanneauDessin().getVueFormes();
			int taille = vueFormes.size();
			for (int i = taille - 1; i >= 0; i--) {
				VueForme vueForme = vueFormes.get(i);
				Forme forme = vueForme.getForme();
				if (forme.contient(click)) {
					bonneForme.add(vueForme);
				}
			}
			if (!bonneForme.isEmpty()) {
				if (bonneForme.size() > 1) {
					JFrame fenetre = new JFrame("choisir forme");
					SpinnerListModel model = new SpinnerListModel(bonneForme);
					JSpinner selectionneur = new JSpinner(model);
					update(fenetre, selectionneur);
					JButton ok = new JButton("ok");
					ok(fenetre, selectionneur, ok);
					fenetre.setLayout(new FlowLayout());
					fenetre.add(selectionneur);
					fenetre.add(ok);
					fenetre.pack();
					fenetre.setVisible(true);
				}
				else{
					this.vueFormeSelectionnee = bonneForme.get(0);
					fenetreInfos();
				}
			}
		} else {
			this.vueFormeSelectionnee.getForme().setPosition(getDebut());
			getPanneauDessin().repaint();
		}
	}
	
	private void update(JFrame fenetre, JSpinner selectionneur) {
		DocumentListener listener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				action();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				action();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				action();
			}
			
			private void action() {
				getPanneauDessin().getVueFormes().remove(selectionneur.getValue());
				getPanneauDessin().getVueFormes().add((VueForme) selectionneur.getValue());	
				getPanneauDessin().repaint();
				fenetre.pack();
			}
		};
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)selectionneur.getEditor();
		spinnerEditor.getTextField().getDocument().addDocumentListener(listener);
	}

	private void ok(JFrame fenetre, JSpinner selectionneur, JButton ok) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vueFormeSelectionnee = (VueForme) selectionneur.getValue();	
				fenetre.dispose();
				fenetreInfos();
			}
		};
		ok.addActionListener(listener);
	}
	/* Right click deselect object and show information. */
	private void clicDroit() {
		JOptionPane.showMessageDialog(getPanneauDessin(), this.vueFormeSelectionnee.getForme(), ActionSelectionner.NOM_ACTION, JOptionPane.INFORMATION_MESSAGE);
		this.vueFormeSelectionnee = null;
		infos.dispose();
	}
	
	private void fenetreInfos() {
		try {
			infos = new JFrame("Informations déplacement");
			infos.setAlwaysOnTop(true);
			infos.setFocusable(false);
			BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/fr/eseo/poo/projet/artiste/numpad.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			JOptionPane.showMessageDialog(infos, picLabel, "Commandes au clavier", JOptionPane.PLAIN_MESSAGE, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}