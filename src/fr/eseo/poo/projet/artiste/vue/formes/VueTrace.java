package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Trace;

/* Define how to draw an trace. */
public class VueTrace extends VueForme {

	private Trace trace;

	public VueTrace(Trace trace) {
		super(trace);
		this.trace = trace;
	}

	public Trace getTrace() {
		return this.trace;
	}

	@Override
	public void affiche(Graphics2D g2d) {
		for (int i = 0; i < trace.getPoints().size() - 1; i++) {
			int xMin = (int) (trace.getPoints().get(i).getAbscisse());
			int yMin = (int) (trace.getPoints().get(i).getOrdonnee());
			int xMax = (int) (trace.getPoints().get(i + 1).getAbscisse());
			int yMax = (int) (trace.getPoints().get(i + 1).getOrdonnee());
			Color couleur = g2d.getColor();
			g2d.setColor(this.getForme().getCouleurContour());
			g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
			g2d.drawLine(xMin, yMin, xMax, yMax);
			g2d.setColor(couleur);
		}
	}

}
