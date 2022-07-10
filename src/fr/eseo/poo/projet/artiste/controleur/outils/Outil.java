package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.event.MouseInputListener;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public abstract class Outil implements MouseInputListener, Serializable {

	private static final long serialVersionUID = 7285396746194472942L;
	private Coordonnees debut;
	private Coordonnees fin;
	private PanneauDessin panneauDessin;

	public Coordonnees getDebut() {
		return this.debut;
	}

	public Coordonnees getFin() {
		return this.fin;
	}

	public PanneauDessin getPanneauDessin() {
		return this.panneauDessin;
	}

	public void setDebut(Coordonnees debut) {
		this.debut = debut;
	}

	public void setFin(Coordonnees fin) {
		this.fin = fin;
	}

	public void setPanneauDessin(PanneauDessin panneau) {
		this.panneauDessin = panneau;
	}

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseDragged(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mouseMoved(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
	}

	public void destroy() {
	}

}
