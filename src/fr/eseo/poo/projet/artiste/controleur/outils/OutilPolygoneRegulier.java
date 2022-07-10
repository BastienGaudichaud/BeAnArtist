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
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulier;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;
import fr.eseo.poo.projet.artiste.vue.formes.VuePolygoneRegulier;

public class OutilPolygoneRegulier extends OutilForme {
	
	private static final long serialVersionUID = 697503588167520858L;
	public static final String COTE_SPINNER_NOM = "Nombre de cotés des Polygones";
	private JFrame fenetre = new JFrame("Option polygone");
	private JSpinner spinnerNombreCotes;

	/* Dysplay a frame to parameterize the polygons. */
	public OutilPolygoneRegulier() {
		fenetre.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		JPanel panneauPolygone = new JPanel();
		panneauPolygone.setLayout(new BoxLayout(panneauPolygone, BoxLayout.Y_AXIS));
		JLabel labelNombreCotes = new JLabel(COTE_SPINNER_NOM);
		spinnerNombreCotes = new JSpinner(
				new SpinnerNumberModel(PolygoneRegulier.NOMBRE_DE_COTES_PAR_DEFAUT, 3, 15, 1));
		spinnerNombreCotes.setMaximumSize(new Dimension(45, 30));
		spinnerNombreCotes.setAlignmentX(-20);
		spinnerNombreCotes.setName(COTE_SPINNER_NOM);
		labelNombreCotes.setLabelFor(spinnerNombreCotes);
		panneauPolygone.add(labelNombreCotes);
		panneauPolygone.add(spinnerNombreCotes);
		fenetre.setLocation(50, 50);
		fenetre.add(panneauPolygone);
		fenetre.setMinimumSize(new Dimension(300, 100));
		fenetre.setResizable(false);
		fenetre.pack();
		fenetre.setVisible(true);
	}

	/* Create a polygon according to the action of the user drawing and the parameters.
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
		PolygoneRegulier polygone;
		if (c1.equals(c2)) {
			polygone = new PolygoneRegulier(c1, Forme.LARGEUR_PAR_DEFAUT, (int) spinnerNombreCotes.getValue(),
					PolygoneRegulier.ANGLE_SOMMET_PAR_DEFAUT);
		} else {
			double taille = c1.distanceVers(c2) * 2;
			double angle = c2.angleVers(c1);
			Coordonnees position = new Coordonnees(c2.getAbscisse() - taille / 2, c2.getOrdonnee() - taille / 2);
			polygone = new PolygoneRegulier(position, taille, (int) spinnerNombreCotes.getValue(), angle);
		}	
		polygone.setCouleurContour(couleur);
		polygone.setCouleurRemplissage(couleur2);
		polygone.setEpaisseur(epaisseur);
		polygone.setRempli(rempli);
		return new VuePolygoneRegulier(polygone);
	}

	@Override
	public void destroy() {
		this.fenetre.dispose();
	}
}
