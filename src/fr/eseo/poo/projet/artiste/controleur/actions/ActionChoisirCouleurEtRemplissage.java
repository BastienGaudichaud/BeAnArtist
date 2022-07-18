package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.poo.projet.artiste.vue.formes.VueRectangle;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirCouleurEtRemplissage extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Couleur/Remplissage";
	private PanneauDessin panneauDessin;

	public ActionChoisirCouleurEtRemplissage(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

	/* Make the user choose all the colors and fill effects for the next drawings.
	 * (Called by the "Couleur/Remplissage" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.anchor = GridBagConstraints.CENTER;
		JFrame fenetre = new JFrame("Paramètres dessin");
		fenetre.setAlwaysOnTop(true);
		initPanneau(fenetre, constraint);
	}
	
	private void initPanneau(JFrame fenetre, GridBagConstraints constraint) {
		JPanel panneau = new JPanel(new GridBagLayout());
		//background button
		boutonFond(fenetre, constraint, panneau);
		//colour indicator
		Color couleurF = panneauDessin.getCouleurFondCourante();
		rectangleCouleur(couleurF, constraint, panneau);
		//colour button
		boutonContour(fenetre, constraint, panneau);
		//colour indicator
		Color couleurC = panneauDessin.getCouleurContourCourante();
		rectangleCouleur(couleurC, constraint, panneau);
		//fill colour button
		boutonRemplissage(fenetre, constraint, panneau);
		//fill colour indicator
		Color couleurR = panneauDessin.getCouleurRemplissageCourante();
		rectangleCouleur(couleurR, constraint, panneau);
		//fill state button
		remplir(fenetre, constraint, panneau);
		//thickness modifier
		epaisseur(constraint, panneau);
		//ok button
		ok(fenetre, constraint, panneau);
		
		fenetre.add(panneau);
		fenetre.pack();
		fenetre.setVisible(true);
	}

	private void boutonFond(JFrame fenetre, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("couleur du fond") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Color couleur = JColorChooser.showDialog(fenetre, "Choisir couleur fond",
						panneauDessin.getCouleurFondCourante());
				if (couleur != null) {
					panneauDessin.setCouleurFondCourante(couleur);
					panneauDessin.repaint();
					initPanneau(fenetre, constraint);
				}
			}
		};
		JButton contour = new JButton(action);
		contour.setPreferredSize(new Dimension(200, 30));
		contour.setName("couleur du fond");
		constraint.gridx = 0;
		constraint.gridy = 0;
		panneau.add(contour, constraint);
	}

	private void boutonContour(JFrame fenetre, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("couleur du contour") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Color couleur = JColorChooser.showDialog(fenetre, "Choisir couleur contour",
						panneauDessin.getCouleurContourCourante());
				if (couleur != null) {
					panneauDessin.setCouleurContourCourante(couleur);
					panneauDessin.repaint();
					initPanneau(fenetre, constraint);
				}
			}
		};
		JButton contour = new JButton(action);
		contour.setPreferredSize(new Dimension(200, 30));
		contour.setName("couleur du contour");
		constraint.gridx = 0;
		constraint.gridy = 1;
		panneau.add(contour, constraint);
	}

	private void boutonRemplissage(JFrame fenetre, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("couleur du remplissage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Color couleur = JColorChooser.showDialog(fenetre, "Choisir couleur remplissage",
						panneauDessin.getCouleurRemplissageCourante());
				if (couleur != null) {
					panneauDessin.setCouleurRemplissageCourante(couleur);
					panneauDessin.repaint();
					initPanneau(fenetre, constraint);
				}
			}
		};
		JButton remplissage = new JButton(action);
		remplissage.setPreferredSize(new Dimension(200, 30));
		remplissage.setName("couleur du remplissage");
		constraint.gridx = 0;
		constraint.gridy = 2;
		panneau.add(remplissage, constraint);
	}

	private void rectangleCouleur(Color couleur, GridBagConstraints constraint, JPanel panneau) {
		Rectangle rectangle = new Rectangle(50, 25);
		rectangle.setEpaisseur(4);
		rectangle.setRempli(true);
		rectangle.setCouleurRemplissage(couleur);
		VueRectangle vueRectangle = new VueRectangle(rectangle);
		JPanel panneauRectangle = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				vueRectangle.affiche((Graphics2D) g);
			}
		};
		panneauRectangle.setPreferredSize(new Dimension(50, 25));
		constraint.gridx = 1;
		panneau.add(panneauRectangle, constraint);		
	}

	private void remplir(JFrame fenetre, GridBagConstraints constraint, JPanel panneau) {
		JCheckBox remplir = new JCheckBox("remplir la forme");
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panneauDessin.setModeRemplissage(remplir.isSelected());
				panneauDessin.repaint();
				initPanneau(fenetre, constraint);
			}
		};
		remplir.addActionListener(action);
		constraint.gridx = 0;
		constraint.gridy = 3;
		panneau.add(remplir, constraint);
	}

	private void epaisseur(GridBagConstraints constraint, JPanel panneau) {
		JLabel taille = new JLabel("épaisseur du contour");
		taille.setPreferredSize(new Dimension(140, 30));
		JSpinner spinnerTaille = new JSpinner(new SpinnerNumberModel(panneauDessin.getEpaisseurCourante(), 1, 20, 1));
		spinnerTaille.setPreferredSize(new Dimension(70, 25));
		
		ChangeListener change = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				panneauDessin.setEpaisseurCourante((int) spinnerTaille.getValue());
				panneauDessin.repaint();
			}
		};
		spinnerTaille.addChangeListener(change);
		
		constraint.gridx = 0;
		constraint.gridy = 4;
		panneau.add(taille, constraint);
		constraint.gridx = 1;
		panneau.add(spinnerTaille, constraint);
	}

	private void ok(JFrame fenetre, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("ok") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.dispose();	
			}
		};
		JButton ok = new JButton(action);
		ok.setPreferredSize(new Dimension(50, 30));
		ok.setName("ok");
		constraint.gridx = 1;
		constraint.gridy = 5;
		panneau.add(ok, constraint);
	}
}


