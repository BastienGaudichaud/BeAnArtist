package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public abstract class OutilForme extends Outil {

	private static final long serialVersionUID = 1L;

	@Override
	public void mouseClicked(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
		VueForme vueForme = creerVueForme();
		if (event.getClickCount() == 2) {
			getPanneauDessin().ajouterVueForme(vueForme);
		}
		getPanneauDessin().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
		if ((event.getX() != getDebut().getAbscisse() || event.getY() != getDebut().getOrdonnee())) {
			VueForme vueForme = creerVueForme();
			getPanneauDessin().ajouterVueForme(vueForme);
		}
		getPanneauDessin().repaint();
	}

	protected abstract VueForme creerVueForme();
}
