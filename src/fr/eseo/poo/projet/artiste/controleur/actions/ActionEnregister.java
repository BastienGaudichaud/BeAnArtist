package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;
import fr.eseo.poo.projet.artiste.xml.EnregistreurSVG;

public class ActionEnregister extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Enregistrer";
	private PanneauDessin panneauDessin;	
	private JFrame fenetre;
	private JTextField textefield;

	public ActionEnregister(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	/* Display a frame to save the drawing and choose is name.
	 * (Called by the "Enregistrer" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre = new JFrame("Enregistrer fichier");
		JPanel panneauTexte = new JPanel();
		JLabel labelTexte = new JLabel("Nom du fichier");
		textefield = new JTextField();
		textefield.setPreferredSize(new Dimension(200, 30));
		textefield.setName("Nom du fichier");
		JLabel extension = new JLabel(".svg");
		labelTexte.setLabelFor(textefield);
		
		AbstractAction action = new AbstractAction() {
			private static final long serialVersionUID = -3418451496402251473L;
			@Override
			public void actionPerformed(ActionEvent e) {
				action();
			}
		};
		JButton button= new JButton(action);
		button.setName("OK");
		button.setText("OK");
		
		panneauTexte.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panneauTexte.add(labelTexte, c);
		c.gridy = 1;
		c.gridwidth = 3;
		panneauTexte.add(textefield, c);
		c.gridx = 3;
		c.gridwidth = 1;
		panneauTexte.add(extension, c);
		c.gridx = 1;
		c.gridy = 2;
		panneauTexte.add(button, c);
		
		fenetre.setLocation(50, 50);
		fenetre.add(panneauTexte);
		fenetre.setMinimumSize(new Dimension(300, 100));
		fenetre.pack();
		fenetre.setVisible(true);
	}

	/* Save the drawing using EnregistreurSVG class.*/
	protected void action() {
		int choix = JOptionPane.showConfirmDialog(fenetre, "Si un fichier de ce nom '" + textefield.getText() + ".svg' existe déjà, il sera supprimé.", "Attention",
				JOptionPane.OK_CANCEL_OPTION);
		if (choix == JOptionPane.OK_OPTION) {
			String nomFichier=textefield.getText() + ".svg";
			EnregistreurSVG enregistreur = new EnregistreurSVG();
			try {
				enregistreur.enregistreDessin(nomFichier, panneauDessin.getVueFormes());
				fenetre.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
