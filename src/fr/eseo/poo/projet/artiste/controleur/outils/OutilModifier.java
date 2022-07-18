package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Cercle;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.modele.formes.Ligne;
import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.poo.projet.artiste.modele.formes.Texte;
import fr.eseo.poo.projet.artiste.modele.formes.Trace;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;
import fr.eseo.poo.projet.artiste.vue.formes.VueRectangle;

public class OutilModifier extends Outil {

	private static final long serialVersionUID = 1L;
	private VueForme vueFormeSelectionnee;
	
	/* Launch the appropriate action when a button is clicked. */
	@Override
	public void mouseClicked(MouseEvent event) {
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
	
	/* If it is a left click, it launch the object selection process*/
	private void clicGauche(MouseEvent event) {
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
			} else {
				this.vueFormeSelectionnee = bonneForme.get(0);
			}
		} else {
			this.vueFormeSelectionnee = null;
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
				getPanneauDessin().getVueFormes().remove( selectionneur.getValue());
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
			}
		};
		ok.addActionListener(listener);
	}
	
	/* If it is a right click, it launch the object modification process*/
	private void clicDroit() {
		VueForme vueForme = vueFormeSelectionnee;
		vueFormeSelectionnee = null;
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.anchor = GridBagConstraints.CENTER;
		JFrame fenetre = new JFrame("Modifier forme");
		fenetre.setAlwaysOnTop(true);
		initPanneau(vueForme, fenetre, constraint);
	}

	/* Create the modification frame */
	private void initPanneau(VueForme vueForme, JFrame fenetre, GridBagConstraints constraint) {
		JPanel panneau = new JPanel(new GridBagLayout());
		//supress button
		supprimer(fenetre, vueForme, constraint, panneau);
		//first layer button
		remonter(fenetre, vueForme, constraint, panneau);
		//colour button
		boutonContour(fenetre, vueForme, constraint, panneau);
		//colour indicator
		Color couleurC = vueForme.getForme().getCouleurContour();
		rectangleCouleur(couleurC, constraint, panneau);
		//fill colour button
		boutonRemplissage(fenetre, vueForme, constraint, panneau);
		//fill colour indicator
		Color couleurR = vueForme.getForme().getCouleurRemplissage();
		rectangleCouleur(couleurR, constraint, panneau);
		//fill state button
		remplir(fenetre, vueForme, constraint, panneau);
		//size modifier
		taille(vueForme, constraint, panneau);
		//thickness modifier
		epaisseur(vueForme, constraint, panneau);
		//angle modifier
		angle(vueForme, constraint, panneau);
		//ok button
		ok(fenetre, constraint, panneau);
		
		fenetre.add(panneau);
		fenetre.pack();
		fenetre.setVisible(true);
	}

	//configure suppress button
	private void supprimer(JFrame fenetre, VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("supprimer la forme") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous supprimer la forme ?", "Effacer",
						JOptionPane.YES_NO_OPTION);
				if (choix == JOptionPane.YES_OPTION) {
					getPanneauDessin().getVueFormes().remove(vueForme);
					getPanneauDessin().repaint();
					fenetre.dispose();
				}	
			}
		};
		JButton supprimer = new JButton(action);
		supprimer.setPreferredSize(new Dimension(200, 30));
		supprimer.setName("supprimer la forme");
		constraint.gridx = 0;
		constraint.gridy = 0;
		panneau.add(supprimer, constraint);
	}

	//configure first layer button
	private void remonter(JFrame fenetre, VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("mettre au premier plan") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous mettre la forme au premier plan ?", "Mettre à l'avant", JOptionPane.YES_NO_OPTION);
				if (choix == JOptionPane.YES_OPTION) {
					List<VueForme> vueFormes = getPanneauDessin().getVueFormes();
					vueFormes.remove(vueForme);
					vueFormes.add(vueForme);
					getPanneauDessin().repaint();
					initPanneau(vueForme, fenetre, constraint);
				} 	
			}
		};
		JButton avancer = new JButton(action);
		avancer.setPreferredSize(new Dimension(200, 30));
		avancer.setName("avancer la forme");
		constraint.gridx = 0;
		constraint.gridy = 1;
		panneau.add(avancer, constraint);
	}

	//configure colour button
	private void boutonContour(JFrame fenetre, VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("couleur du contour") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Color couleur = JColorChooser.showDialog(fenetre, "Choisir couleur contour",
						getPanneauDessin().getCouleurContourCourante());
				if (couleur != null) {
					vueForme.getForme().setCouleurContour(couleur);
					getPanneauDessin().repaint();
					initPanneau(vueForme, fenetre, constraint);
				}
			}
		};
		JButton contour = new JButton(action);
		contour.setPreferredSize(new Dimension(200, 30));
		contour.setName("couleur du contour");
		constraint.gridx = 0;
		constraint.gridy = 2;
		panneau.add(contour, constraint);
	}

	//configure fill colour button
	private void boutonRemplissage(JFrame fenetre, VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		AbstractAction action = new AbstractAction("couleur du remplissage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Color couleur = JColorChooser.showDialog(fenetre, "Choisir couleur remplissage",
						getPanneauDessin().getCouleurRemplissageCourante());
				if (couleur != null) {
					vueForme.getForme().setCouleurRemplissage(couleur);
					getPanneauDessin().repaint();
					initPanneau(vueForme, fenetre, constraint);
				}
			}
		};
		JButton remplissage = new JButton(action);
		remplissage.setPreferredSize(new Dimension(200, 30));
		remplissage.setName("couleur du remplissage");
		constraint.gridx = 0;
		constraint.gridy = 3;
		panneau.add(remplissage, constraint);
	}

	//configure colour indicator
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

	//configure fill state button
	private void remplir(JFrame fenetre, VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		JCheckBox remplir = new JCheckBox("remplir la forme");
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vueForme.getForme().setRempli(remplir.isSelected());
				getPanneauDessin().repaint();
				initPanneau(vueForme, fenetre, constraint);
			}
		};
		remplir.addActionListener(action);
		constraint.gridx = 0;
		constraint.gridy = 4;
		if (
			vueForme.getForme().getClass() != Trace.class &&
			vueForme.getForme().getClass() != Texte.class &&
			vueForme.getForme().getClass() != Ligne.class
		) {
			panneau.add(remplir, constraint);
		}
	}

	//configure size modifier
	private void taille(VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		JLabel taille = new JLabel("taille");
		taille.setPreferredSize(new Dimension(40, 30));
		Forme forme = vueForme.getForme();
		boolean tailleUnique = forme.getLargeur() == forme.getHauteur();
		if(forme.getClass() != Trace.class) {
			if(forme.getClass() != Texte.class) {
				JSpinner spinnerTaille = new JSpinner(new SpinnerNumberModel(forme.getLargeur(), forme.getTailleMin(), forme.getTailleMax(), 1));
				spinnerTaille.setPreferredSize(new Dimension(70, 25));
				
				ChangeListener change = new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						forme.setLargeur((double) spinnerTaille.getValue());
						getPanneauDessin().repaint();
					}
				};
				spinnerTaille.addChangeListener(change);
				
				constraint.gridx = 0;
				constraint.gridy = 5;
				panneau.add(taille, constraint);
				constraint.gridx = 1;
				panneau.add(spinnerTaille, constraint);
				
				if(!tailleUnique) {
					JLabel multi = new JLabel("x");
					JSpinner spinnerTaille2 = new JSpinner(new SpinnerNumberModel(forme.getHauteur(), forme.getTailleMin(), forme.getTailleMax(), 1));
					spinnerTaille2.setPreferredSize(new Dimension(70, 25));

					
					ChangeListener change2 = new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							forme.setHauteur((double) spinnerTaille2.getValue());
							getPanneauDessin().repaint();
						}
					};
					spinnerTaille2.addChangeListener(change2);
					
					constraint.gridx = 2;
					panneau.add(multi, constraint);
					constraint.gridx = 3;
					panneau.add(spinnerTaille2, constraint);
				}
			}
			else {
				JSpinner spinnerTaille = new JSpinner(new SpinnerNumberModel(((Texte)forme).getTaillePolice(), 6, 180, 1));
				spinnerTaille.setPreferredSize(new Dimension(70, 25));

				ChangeListener change = new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						((Texte)forme).setPolice((int) spinnerTaille.getValue());
						getPanneauDessin().repaint();
					}
				};
				spinnerTaille.addChangeListener(change);
				
				constraint.gridx = 0;
				constraint.gridy = 5;
				panneau.add(taille, constraint);
				constraint.gridx = 1;
				panneau.add(spinnerTaille, constraint);
			}
		}
	}

	//configure thickness modifier
	private void epaisseur(VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		Forme forme = vueForme.getForme();
		if (forme.getClass() != Texte.class) {
			JLabel taille = new JLabel("épaisseur du contour");
			taille.setPreferredSize(new Dimension(140, 30));
			JSpinner spinnerTaille = new JSpinner(new SpinnerNumberModel(forme.getEpaisseur(), 1, 20, 1));
			spinnerTaille.setPreferredSize(new Dimension(70, 25));
			
			ChangeListener change = new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					forme.setEpaisseur((int) spinnerTaille.getValue());
					getPanneauDessin().repaint();
				}
			};
			spinnerTaille.addChangeListener(change);
			
			constraint.gridx = 0;
			constraint.gridy = 6;
			panneau.add(taille, constraint);
			constraint.gridx = 1;
			panneau.add(spinnerTaille, constraint);
		}	
	}

	private void angle(VueForme vueForme, GridBagConstraints constraint, JPanel panneau) {
		Forme forme = vueForme.getForme();
		if (forme.getClass() != Cercle.class) {
			JLabel angle = new JLabel("angle de rotation");
			angle.setPreferredSize(new Dimension(140, 30));
			JSpinner spinnerAngle = new JSpinner(new SpinnerNumberModel(forme.getAngle(), 0, 360, 1));
			spinnerAngle.setPreferredSize(new Dimension(70, 25));
			
			ChangeListener change = new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					forme.setAngle((int) spinnerAngle.getValue());
					getPanneauDessin().repaint();
				}
			};
			spinnerAngle.addChangeListener(change);
			
			constraint.gridx = 0;
			constraint.gridy = 7;
			panneau.add(angle, constraint);
			constraint.gridx = 1;
			panneau.add(spinnerAngle, constraint);
		}	
		
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
		constraint.gridx = 3;
		constraint.gridy = 8;
		panneau.add(ok, constraint);
	}
}
