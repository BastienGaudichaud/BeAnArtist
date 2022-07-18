package fr.eseo.poo.projet.artiste.vue.ihm;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import fr.eseo.poo.projet.artiste.controleur.actions.ActionAfficheInfos;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionAfficheInfosDessin;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionAgrandirRetrecir;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionCacherDessin;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionChoisirCouleurEtRemplissage;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionChoisirForme;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionDessiner;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionEffacer;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionEnregister;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionFermer;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionModifier;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionOuvrir;
import fr.eseo.poo.projet.artiste.controleur.actions.ActionSelectionner;

public class PanneauBarreOutils extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanneauDessin panneauDessin;
	private ActionDessiner dessin;
	private ButtonGroup boutons;

	public PanneauBarreOutils(PanneauDessin panneauDessin, PanneauBarreOutils barreOutils) {
		setLayout(new GridLayout(10, 1));
		this.panneauDessin = panneauDessin;
		if (barreOutils == null) {
			initComponents();
		} else {
			initDessin(barreOutils);
		}
	}

	/* The main menu. */
	public void initComponents() {
		// bouton information
		ActionAfficheInfos infos = new ActionAfficheInfos();
		JButton boutonInfos = new JButton(infos);
		boutonInfos.setName(ActionAfficheInfos.NOM_ACTION);
		// bouton dessiner
		this.dessin = new ActionDessiner(panneauDessin, this);
		JToggleButton boutonDessin = new JToggleButton(dessin);
		boutonDessin.setName(ActionDessiner.NOM_ACTION);
		// groupe de bouton
		boutons = new ButtonGroup();
		// bouton selectionner
		ActionSelectionner selectionner = new ActionSelectionner(panneauDessin, this);
		JToggleButton boutonSelectionner = new JToggleButton(selectionner);
		// bouton modifier
		ActionModifier modifier = new ActionModifier(panneauDessin, this);
		JToggleButton boutonModifier = new JToggleButton(modifier);
		// bouton couleur
		ActionChoisirCouleurEtRemplissage couleur = new ActionChoisirCouleurEtRemplissage(this.panneauDessin);
		JButton boutonCouleur = new JButton(couleur);
		boutonCouleur.setName(ActionChoisirCouleurEtRemplissage.NOM_ACTION);
		// bouton effacer
		ActionEffacer effacer = new ActionEffacer(this.panneauDessin);
		JButton boutonEffacer = new JButton(effacer);
		boutonEffacer.setName(ActionEffacer.NOM_ACTION);
		// bouton ouvrir
		ActionOuvrir ouvrir= new ActionOuvrir(panneauDessin);
		JButton boutonOuvrir =new JButton(ouvrir);
		boutonOuvrir.setName(ActionOuvrir.NOM_ACTION);
		// bouton enregistrer
		ActionEnregister enregistrer = new ActionEnregister(panneauDessin);
		JButton boutonEnregistrer = new JButton(enregistrer);
		boutonEnregistrer.setName(ActionEnregister.NOM_ACTION);
		// bouton taille
		ActionAgrandirRetrecir taille = new ActionAgrandirRetrecir(panneauDessin);
		JButton boutonTaille = new JButton(taille);
		boutonTaille.setName(ActionAgrandirRetrecir.NOM_ACTION);
		// bouton fermer
		ActionFermer fermer = new ActionFermer(panneauDessin);
		JButton boutonFermer = new JButton(fermer);
		boutonFermer.setName(ActionFermer.NOM_ACTION);
		// ajout des boutons
		this.add(boutonInfos);
		boutons.add(boutonDessin);
		this.add(boutonDessin);
		boutons.add(boutonSelectionner);
		this.add(boutonSelectionner);
		boutons.add(boutonModifier);
		this.add(boutonModifier);
		this.add(boutonCouleur);
		this.add(boutonEffacer);
		this.add(boutonOuvrir);
		this.add(boutonEnregistrer);
		this.add(boutonTaille);
		this.add(boutonFermer);
	}

	/* The Drawing menu. */
	private void initDessin(PanneauBarreOutils barreOutils) {
		PanneauBarreOutils barre = barreOutils;
		// bouton information
		ActionAfficheInfosDessin infos = new ActionAfficheInfosDessin();
		JButton boutonInfos = new JButton(infos);
		boutonInfos.setName(ActionAfficheInfosDessin.NOM_ACTION);
		// groupe de bouton
		ButtonGroup boutonsDessin = new ButtonGroup();
		// bouton texte
		ActionChoisirForme texte = new ActionChoisirForme(panneauDessin,ActionChoisirForme.NOM_ACTION_TEXTE);
		JToggleButton boutonTexte = new JToggleButton(texte);
		boutonTexte.setName(ActionChoisirForme.NOM_ACTION_TEXTE);
		// bouton pinceau
		ActionChoisirForme pinceau = new ActionChoisirForme(panneauDessin,ActionChoisirForme.NOM_ACTION_CRAYON);
		JToggleButton boutonPinceau = new JToggleButton(pinceau);
		boutonPinceau.setName(ActionChoisirForme.NOM_ACTION_CRAYON);
		// bouton ligne
		ActionChoisirForme ligne = new ActionChoisirForme(panneauDessin, ActionChoisirForme.NOM_ACTION_LIGNE);
		JToggleButton boutonLigne = new JToggleButton(ligne);
		boutonLigne.setName(ActionChoisirForme.NOM_ACTION_LIGNE);
		// bouton rectangle
		ActionChoisirForme rectangle = new ActionChoisirForme(panneauDessin, ActionChoisirForme.NOM_ACTION_RECTANGLE);
		JToggleButton boutonRectangle = new JToggleButton(rectangle);
		boutonRectangle.setName(ActionChoisirForme.NOM_ACTION_RECTANGLE);
		// bouton ellipse
		ActionChoisirForme ellipse = new ActionChoisirForme(panneauDessin, ActionChoisirForme.NOM_ACTION_ELLIPSE);
		JToggleButton boutonEllipse = new JToggleButton(ellipse);
		boutonEllipse.setName(ActionChoisirForme.NOM_ACTION_ELLIPSE);
		// bouton cercle
		ActionChoisirForme cercle = new ActionChoisirForme(panneauDessin, ActionChoisirForme.NOM_ACTION_CERCLE);
		JToggleButton boutonCercle = new JToggleButton(cercle);
		boutonCercle.setName(ActionChoisirForme.NOM_ACTION_CERCLE);
		// bouton etoile
		ActionChoisirForme etoile = new ActionChoisirForme(panneauDessin, ActionChoisirForme.NOM_ACTION_ETOILE);
		JToggleButton boutonEtoile = new JToggleButton(etoile);
		boutonEtoile.setName(ActionChoisirForme.NOM_ACTION_ETOILE);
		// bouton polygone
		ActionChoisirForme polygone = new ActionChoisirForme(panneauDessin, ActionChoisirForme.NOM_ACTION_POLYGONE);
		JToggleButton boutonPolygone = new JToggleButton(polygone);
		boutonPolygone.setName(ActionChoisirForme.NOM_ACTION_POLYGONE);
		// bouton cacher
		ActionCacherDessin cacher = new ActionCacherDessin(barre, this);
		JButton boutonCacher = new JButton(cacher);
		boutonCacher.setName(ActionCacherDessin.NOM_ACTION);
		// ajout des boutons
		this.add(boutonInfos);
		boutonsDessin.add(boutonTexte);
		this.add(boutonTexte);
		boutonsDessin.add(boutonPinceau);
		this.add(boutonPinceau);
		boutonsDessin.add(boutonLigne);
		this.add(boutonLigne);
		boutonsDessin.add(boutonRectangle);
		this.add(boutonRectangle);
		boutonsDessin.add(boutonEllipse);
		this.add(boutonEllipse);
		boutonsDessin.add(boutonCercle);
		this.add(boutonCercle);
		boutonsDessin.add(boutonEtoile);
		this.add(boutonEtoile);
		boutonsDessin.add(boutonPolygone);
		this.add(boutonPolygone);
		this.add(boutonCacher);
	}

	public ActionDessiner getDessin() {
		return dessin;
	}
	
	public PanneauDessin getPanneauDessin() {
		return panneauDessin;
	}
	
	public ButtonGroup getBoutons() {
		return boutons;
	}
}
