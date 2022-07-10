package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Etoile;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.vue.formes.VueEtoile;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public class OutilEtoile extends OutilForme {

	private static final long serialVersionUID = -3539997145793789720L;
	public static final String BRANCHE_SPINNER_NOM = "Nombre de branches des Etoiles";
	public static final String LONGUEUR_SPINNER_NOM = "Longueur de branche des Etoiles";
	private JFrame fenetre = new JFrame("Option étoile");
	private JSpinner spinnerNombreBranches;
	private JSpinner spinnerLongueurBranche;

	/* Display a frame to parameterize the stars.*/
	public OutilEtoile() {
		fenetre.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		JPanel panneauEtoile = new JPanel();
		panneauEtoile.setLayout(new BoxLayout(panneauEtoile, BoxLayout.Y_AXIS));
		JLabel labelNombreBranche = new JLabel(BRANCHE_SPINNER_NOM);
		spinnerNombreBranches = new JSpinner(new SpinnerNumberModel(Etoile.NOMBRE_BRANCHES_PAR_DEFAUT, 3, 15, 1));
		spinnerNombreBranches.setMaximumSize(new Dimension(45, 30));
		spinnerNombreBranches.setName(BRANCHE_SPINNER_NOM);
		spinnerNombreBranches.setAlignmentX(-20);
		labelNombreBranche.setLabelFor(spinnerNombreBranches);
		JLabel labelLongueurBranche = new JLabel(LONGUEUR_SPINNER_NOM);
		this.spinnerLongueurBranche = new JSpinner(
				new SpinnerNumberModel(Etoile.LONGUEUR_BRANCHE_PAR_DEFAUT, 0, 1, 0.01));
		spinnerLongueurBranche.setMaximumSize(new Dimension(45, 30));
		spinnerLongueurBranche.setName(LONGUEUR_SPINNER_NOM);
		spinnerLongueurBranche.setAlignmentX(-20);
		labelLongueurBranche.setLabelFor(spinnerLongueurBranche);
		panneauEtoile.add(labelNombreBranche);
		panneauEtoile.add(spinnerNombreBranches);
		panneauEtoile.add(labelLongueurBranche);
		panneauEtoile.add(spinnerLongueurBranche);
		fenetre.setLocation(50, 50);
		fenetre.add(panneauEtoile);
		fenetre.setMinimumSize(new Dimension(300, 200));
		fenetre.setResizable(false);
		fenetre.pack();
		fenetre.setVisible(true);
	}

	/* Create a star according to the action of the user drawing and the parameters.
	 * (See the documentation in the Application for more details.)
	 * (Called by OutilForme.)*/
	@Override
	protected VueForme creerVueForme() {
		Coordonnees c1 = getDebut();
		Coordonnees c2 = getFin();
		Color couleur = this.getPanneauDessin().getCouleurContourCourante();
		Color couleur2 = this.getPanneauDessin().getCouleurRemplissageCourante();
		int epaisseur = this.getPanneauDessin().getEpaisseurCourante();
		boolean rempli = this.getPanneauDessin().getModeRemplissage();
		Etoile etoile;
		if (c1.equals(c2)) {
			etoile = new Etoile(c1, Forme.LARGEUR_PAR_DEFAUT, (int) spinnerNombreBranches.getValue(),
					Etoile.ANGLE_PREMIERE_BRANCHE_PAR_DEFAUT, (double) spinnerLongueurBranche.getValue());
		} else {
			double taille = c1.distanceVers(c2) * 2;
			double angle = c2.angleVers(c1);
			Coordonnees position = new Coordonnees(c2.getAbscisse() - taille / 2, c2.getOrdonnee() - taille / 2);
			etoile = new Etoile(position, taille, (int) spinnerNombreBranches.getValue(), angle,
					(double) spinnerLongueurBranche.getValue());
		}
		etoile.setCouleurContour(couleur);
		etoile.setCouleurRemplissage(couleur2);
		etoile.setEpaisseur(epaisseur);
		etoile.setRempli(rempli);
		return new VueEtoile(etoile);
	}

	@Override
	public void destroy() {
		this.fenetre.dispose();
	}

}
