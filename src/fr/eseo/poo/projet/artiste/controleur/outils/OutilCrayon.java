package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Trace;
import fr.eseo.poo.projet.artiste.vue.formes.VueTrace;

public class OutilCrayon extends Outil {
	
	private static final long serialVersionUID = -3183957788529105817L;
	private List<VueTrace> vueTraces;

	public OutilCrayon() {
		this.vueTraces = new ArrayList<>();
	}

	/** Update the trace when we the mouse move and the button is still pressed. */
	@Override
	public void mouseDragged(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
		VueTrace vueCrayon = vueTraces.get(vueTraces.size() - 1);
		getPanneauDessin().getVueFormes().remove(vueCrayon);
		vueCrayon.getTrace().ajouterCoordonnees(getFin());
		getPanneauDessin().ajouterVueForme(vueCrayon);
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
		getPanneauDessin().repaint();
	}

	/** Start to add a trace when a button is pressed. */
	@Override
	public void mousePressed(MouseEvent event) {
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
		Trace crayon = new Trace(getDebut());
		Color couleur = getPanneauDessin().getCouleurContourCourante();
		int epaisseur = this.getPanneauDessin().getEpaisseurCourante();
		crayon.setCouleurContour(couleur);
		crayon.setEpaisseur(epaisseur);
		VueTrace vueCrayon = new VueTrace(crayon);
		vueTraces.add(vueCrayon);
	}

	/** Finalize the trace when the button is released. */
	@Override
	public void mouseReleased(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
		VueTrace vueCrayon = vueTraces.get(vueTraces.size() - 1);
		getPanneauDessin().getVueFormes().remove(vueCrayon);
		vueCrayon.getTrace().ajouterCoordonnees(getFin());
		getPanneauDessin().ajouterVueForme(vueCrayon);
		getPanneauDessin().repaint();
	}

}
