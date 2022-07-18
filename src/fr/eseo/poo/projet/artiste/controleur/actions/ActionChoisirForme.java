package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.poo.projet.artiste.controleur.outils.OutilCercle;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilCrayon;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilEllipse;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilEtoile;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilLigne;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilPolygoneRegulier;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilRectangle;
import fr.eseo.poo.projet.artiste.controleur.outils.OutilTexte;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirForme extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION_TEXTE = "Texte";
	public static final String NOM_ACTION_CRAYON = "Crayon";
	public static final String NOM_ACTION_LIGNE = "Ligne";
	public static final String NOM_ACTION_RECTANGLE = "Rectangle";
	public static final String NOM_ACTION_ELLIPSE = "Ellipse";
	public static final String NOM_ACTION_CERCLE = "Cercle";
	public static final String NOM_ACTION_ETOILE = "Etoile";
	public static final String NOM_ACTION_POLYGONE = "Polygone Régulier";
	private PanneauDessin panneauDessin;

	public ActionChoisirForme(PanneauDessin panneauDessin, String action) {
		super(action);
		this.panneauDessin = panneauDessin;
	}

	/* Associate the appropriate drawing tool to the drawing panel.
	 * (Called by the drawing buttons from the drawing menu.) */
	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action) {
		case NOM_ACTION_CERCLE:
			this.panneauDessin.associerOutil(new OutilCercle());
			break;
		case NOM_ACTION_ELLIPSE:
			this.panneauDessin.associerOutil(new OutilEllipse());
			break;
		case NOM_ACTION_LIGNE:
			this.panneauDessin.associerOutil(new OutilLigne());
			break;
		case NOM_ACTION_ETOILE:
			this.panneauDessin.associerOutil(new OutilEtoile());
			break;
		case NOM_ACTION_POLYGONE:
			this.panneauDessin.associerOutil(new OutilPolygoneRegulier());
			break;
		case NOM_ACTION_RECTANGLE:
			this.panneauDessin.associerOutil(new OutilRectangle());
			break;
		case NOM_ACTION_CRAYON:
			this.panneauDessin.associerOutil(new OutilCrayon());
			break;
		case NOM_ACTION_TEXTE:
			this.panneauDessin.associerOutil(new OutilTexte());
			break;
		default:
			break;
		}

	}

}
