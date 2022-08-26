package fr.eseo.poo.projet.artiste.vue.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.eseo.poo.projet.artiste.controleur.outils.Outil;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

/* The main frame of the application. */
public class PanneauDessin extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int LARGEUR_PAR_DEFAUT = 500;
	public static final int HAUTEUR_PAR_DEFAUT = 300;
	public static final Color COULEUR_FOND_PAR_DEFAUT = Color.white;
	private final List<VueForme> vueFormes = new ArrayList<>();
	private Color couleurContourCourante;
	private Color couleurRemplissageCourante;
	private Color couleurFondCourante;
	private boolean modeRemplissage;
	private int epaisseurCourante;
	private Outil outilCourant;
	private JFrame fenetre;

	public PanneauDessin(JFrame parent) {
		this.setPreferredSize(new Dimension(LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT));
		this.setBackground(COULEUR_FOND_PAR_DEFAUT);
		this.setCouleurFondCourante(COULEUR_FOND_PAR_DEFAUT);
		this.setCouleurContourCourante(Forme.COULEUR_PAR_DEFAUT);
		this.setModeRemplissage(false);
		this.setEpaisseurCourante(Forme.EPAISSEUR_PAR_DEFAUT);
		this.fenetre = parent;		
	}

	public PanneauDessin(int largeur, int hauteur, Color fond) {
		this.setPreferredSize(new Dimension(largeur, hauteur));
		this.setBackground(fond);
		this.setCouleurFondCourante(COULEUR_FOND_PAR_DEFAUT);
		this.setCouleurContourCourante(Forme.COULEUR_PAR_DEFAUT);
		this.setModeRemplissage(false);
		this.requestFocusInWindow();
	}

	public Color getCouleurContourCourante() {
		return this.couleurContourCourante;
	}

	public Color getCouleurRemplissageCourante() {
		return couleurRemplissageCourante;
	}

	public Color getCouleurFondCourante() {
		return couleurFondCourante;
	}

	public boolean getModeRemplissage() {
		return this.modeRemplissage;
	}

	public int getEpaisseurCourante() {
		return epaisseurCourante;
	}

	public Outil getOutilCourant() {
		return this.outilCourant;
	}

	public List<VueForme> getVueFormes() {
		return vueFormes;
	}

	@Override
	public JFrame getParent() {
		return fenetre;
	}

	public void setCouleurContourCourante(Color couleur) {
		this.couleurContourCourante = couleur;
	}

	public void setCouleurRemplissageCourante(Color couleurRemplissageCourante) {
		this.couleurRemplissageCourante = couleurRemplissageCourante;
	}

	public void setCouleurFondCourante(Color couleurFondCourante) {
		this.couleurFondCourante = couleurFondCourante;
		setBackground(couleurFondCourante);
	}

	public void setModeRemplissage(boolean modeRemplissage) {
		this.modeRemplissage = modeRemplissage;
	}

	public void setEpaisseurCourante(int epaisseurCourante) {
		this.epaisseurCourante = epaisseurCourante;
	}

	private void setOutilCourant(Outil outil) {
		this.outilCourant = outil;
	}

	public void associerOutil(Outil outil) {
		if (outil != null) {
			if (this.getOutilCourant() != null) {
				this.dissocierOutil();
			}
			this.setOutilCourant(outil);
			outil.setPanneauDessin(this);
			this.addMouseListener(outil);
			this.addMouseMotionListener(outil);
			this.setFocusable(true);
			this.addKeyListener(outil);
			this.requestFocusInWindow();
			
		}
	}

	private void dissocierOutil() {
		this.getOutilCourant().setVueFormeSelectionnee();
		this.getOutilCourant().destroy();
		this.getOutilCourant().setPanneauDessin(null);
		this.removeMouseListener(getOutilCourant());
		this.removeMouseMotionListener(getOutilCourant());
		this.setOutilCourant(null);
	}

	public void ajouterVueForme(VueForme vueforme) {
		this.vueFormes.add(vueforme);
		vueforme.setPanneau(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		for (int i = 0; i < vueFormes.size(); i++) {
			vueFormes.get(i).affiche(g2d);
		}
		g2d.dispose();
	}
}
