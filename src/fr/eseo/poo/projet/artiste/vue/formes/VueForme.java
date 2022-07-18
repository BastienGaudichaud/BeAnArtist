package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Graphics2D;

import javax.swing.JComponent;

import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public abstract class VueForme extends JComponent{

	private static final long serialVersionUID = 1L;
	protected final Forme forme;
	protected PanneauDessin panneau;

	protected VueForme(Forme forme) {
		this.forme = forme;
	}

	public Forme getForme() {
		return forme;
	}

	public PanneauDessin getPanneau() {
		return this.panneau;
	}

	public void setPanneau(PanneauDessin panneau) {
		this.panneau = panneau;
	}

	public abstract void affiche(Graphics2D g2d);

	@Override
	public String toString() {
		return forme.toString();
	}

}
