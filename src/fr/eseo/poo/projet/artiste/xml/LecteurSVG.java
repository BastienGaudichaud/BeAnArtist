package fr.eseo.poo.projet.artiste.xml;

import static javax.xml.xpath.XPathConstants.NODESET;
import static javax.xml.xpath.XPathConstants.NUMBER;
import static javax.xml.xpath.XPathConstants.STRING;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Cercle;
import fr.eseo.poo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.poo.projet.artiste.modele.formes.Etoile;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.modele.formes.Ligne;
import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulier;
import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.poo.projet.artiste.modele.formes.Texte;
import fr.eseo.poo.projet.artiste.modele.formes.Trace;
import fr.eseo.poo.projet.artiste.vue.formes.VueCercle;
import fr.eseo.poo.projet.artiste.vue.formes.VueEllipse;
import fr.eseo.poo.projet.artiste.vue.formes.VueEtoile;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;
import fr.eseo.poo.projet.artiste.vue.formes.VueLigne;
import fr.eseo.poo.projet.artiste.vue.formes.VuePolygoneRegulier;
import fr.eseo.poo.projet.artiste.vue.formes.VueRectangle;
import fr.eseo.poo.projet.artiste.vue.formes.VueTexte;
import fr.eseo.poo.projet.artiste.vue.formes.VueTrace;

/**
 * Un lecteur SVG est un processeur DOM/XPath responsable du chargement d'un
 * dessin au format SVG.
 * 
 * Les méthodes lisDessin et lisXxxx devront être complétées.
 * 
 */
public class LecteurSVG {

	private static final String POINTS2 = "@points";
	private static final String FILL = "@fill";
	/**
	 * Evaluateur d'expressions XPath permettant de naviguer facilement dans le
	 * document DOM.
	 */
	private XPath xpath = XPathFactory.newInstance().newXPath();

	/**
	 * Charge le fichier SVG donné dans un document DOM puis renvoie
	 * l'intégralité du dessin sous la forme d'une liste de vues représentant
	 * les formes stockées dans le fichier.
	 * 
	 * @param nomFichier le nom du fichier SVG
	 * @return l'intégralité du dessin sous la forme d'une liste de vues
	 * @throws FileNotFoundException si le fichier n'est pas trouvé ou
	 *             accessible
	 * @throws XPathExpressionException
	 */
	public List<VueForme> lisDessin(File fichier) throws FileNotFoundException, XPathExpressionException {
		List<VueForme> dessin = new ArrayList<>();
		Document document = new ChargeurDOM().chargeFile(fichier);
		// L'expression XPath doit renvoyer les listes des noeuds DOM représentant toutes les formes
		// présentes dans le dessin.
		NodeList figures = (NodeList) xpath.evaluate("//svg/*", document, NODESET);
		for (int i = 0; i < figures.getLength(); i++) {
			Node noeud = figures.item(i);
			VueForme vue = lisVueForme(noeud);
			// note: la condition suivante "vue != null" permet de lancer le "main" bien que toutes 
			// les méthodes lis<Forme> ne soient pas encore implémentées.
			if (vue != null) {
				dessin.add(vue);
				vue.getForme().setCouleurContour(lisCouleur1(noeud));
				vue.getForme().setCouleurRemplissage(lisCouleur2(noeud));
			}
		}
		return dessin;
	}

	/**
	 * Crée une forme et sa vue associée réprésentées par le noeud DOM donné,
	 * puis renvoie cette vue. Cette méthode invoque les méthodes lis<Forme>
	 * définies pour chacune des <Forme> considérée.
	 * 
	 * @param noeud le noeud représentant la vue et sa forme
	 * @return la vue stockée dans le noeud considéré
	 */
	public VueForme lisVueForme(Node noeud) throws XPathExpressionException {
		VueForme vue = null;
		switch (noeud.getNodeName()) {
			case "text":
				vue = new VueTexte(lisTexte(noeud));
				break;
			case "polyline":
				vue=new VueTrace(lisTrace(noeud));
				break;
			case "line":
				vue = new VueLigne(lisLigne(noeud));
				break;
			case "rect":
				vue = new VueRectangle(lisRectangle(noeud));
				break;
			case "ellipse":
				vue = new VueEllipse(lisEllipse(noeud));
				break;
			case "circle":
				vue = new VueCercle(lisCercle(noeud));
				break;
			case "polygon":
				String nom = (String) xpath.evaluate("@name", noeud, STRING);
					if(nom.equals("etoile")){
						vue = new VueEtoile(lisEtoile(noeud));
					}
					else if(nom.equals("polygon")) {
						vue =new VuePolygoneRegulier(lisPolygoneRegulier(noeud));
					}
				break;
			default:
				break;
		}
		return vue;
	}

	public Texte lisTexte(Node noeud) throws XPathExpressionException {
		double x = (double) xpath.evaluate("@x", noeud, NUMBER);
		double y = (double) xpath.evaluate("@y", noeud, NUMBER);
		double taille= (double) xpath.evaluate("@font-size", noeud, NUMBER);
		String phrase = (String) xpath.evaluate("//child::*", noeud, STRING);
		String[] phrases;
		phrases = phrase.split("\n");
		phrases = phrases[1].split("\t");
		Coordonnees position = new Coordonnees(x, y);
		phrase=phrases[1];
		phrase = phrase.replace("/", System.lineSeparator());
		Texte texte= new Texte(position, phrase);
		texte.setPolice((int)taille);
		return texte;
	}

	public Trace lisTrace(Node noeud) throws XPathExpressionException {
		String points = (String) xpath.evaluate(POINTS2, noeud, STRING);
		StringTokenizer tokenizer = new StringTokenizer(points, ", ");
		List<Coordonnees> coordonnees = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			double x = Double.parseDouble(tokenizer.nextToken());
			double y = Double.parseDouble(tokenizer.nextToken());
			coordonnees.add(new Coordonnees(x, y));
		}
		Trace trace=new Trace(coordonnees.get(0));
		for(int i=1; i<coordonnees.size(); i++) {
			trace.ajouterCoordonnees(coordonnees.get(i));
		}
		return trace;
	}

	/**
	 * Renvoie la nouvelle ligne représentée par le noeud DOM donné.
	 * 
	 * @param noeud le noeud représentant la ligne
	 * @return la ligne stockée dans le noeud considéré
	 */
	public Ligne lisLigne(Node noeud) throws XPathExpressionException {
		double x1 = (double) xpath.evaluate("@x1", noeud, NUMBER);
		double y1 = (double) xpath.evaluate("@y1", noeud, NUMBER);
		double x2 = (double) xpath.evaluate("@x2", noeud, NUMBER);
		double y2 = (double) xpath.evaluate("@y2", noeud, NUMBER);
		Coordonnees p1 = new Coordonnees(x1, y1);
		Coordonnees p2 = new Coordonnees(x2, y2);
		Ligne ligne = new Ligne();
		ligne.setC1(p1);
		ligne.setC2(p2);
		return ligne;
	}

	public Rectangle lisRectangle(Node noeud) throws XPathExpressionException {
		double x = (double) xpath.evaluate("@x", noeud, NUMBER);
		double y = (double) xpath.evaluate("@y", noeud, NUMBER);
		double width = (double) xpath.evaluate("@width", noeud, NUMBER);
		double height = (double) xpath.evaluate("@height", noeud, NUMBER);
		Coordonnees position = new Coordonnees(x, y);
		String remplissage = (String) xpath.evaluate(FILL, noeud, STRING);
		Rectangle rectangle=new Rectangle(position, width, height);
		if (!remplissage.equals("none")) {
			rectangle.setRempli(true);
		}
		return rectangle;
	}

	/**
	 * Renvoie une nouvelle ellipse représentée par le noeud DOM donné.
	 * 
	 * @param noeud le noeud représentant l'ellipse
	 * @return l'ellipse stockée dans le noeud considéré
	 */
	public Ellipse lisEllipse(Node noeud) throws XPathExpressionException {
		double cx = (double) xpath.evaluate("@cx", noeud, NUMBER);
		double cy = (double) xpath.evaluate("@cy", noeud, NUMBER);
		double rx = (double) xpath.evaluate("@rx", noeud, NUMBER);
		double ry = (double) xpath.evaluate("@ry", noeud, NUMBER);
		Coordonnees position = new Coordonnees(cx-rx, cy-ry);
		String remplissage = (String) xpath.evaluate(FILL, noeud, STRING);
		Ellipse ellipse=new Ellipse(position, 2 * rx, 2 * ry);
		if (!remplissage.equals("none")) {
			ellipse.setRempli(true);
		}
		return ellipse;
	}

	/**
	 * Renvoie un nouveau cercle représenté par le noeud DOM donné.
	 * 
	 * @param noeud le noeud représentant le cercle
	 * @return le cercle stocké dans le noeud considéré
	 */
	public Cercle lisCercle(Node noeud) throws XPathExpressionException {
		double cx = (double) xpath.evaluate("@cx", noeud, NUMBER);
		double cy = (double) xpath.evaluate("@cy", noeud, NUMBER);
		double r = (double) xpath.evaluate("@r", noeud, NUMBER);
		Coordonnees position = new Coordonnees(cx-r, cy-r);
		String remplissage = (String) xpath.evaluate(FILL, noeud, STRING);
		Cercle cercle=new Cercle(position, 2 * r);
		if (!remplissage.equals("none")) {
			cercle.setRempli(true);
		}
		return cercle;	}

	/**
	 * Renvoie la nouvelle étoile représentée par le noeud DOM donné.
	 * 
	 * @param noeud le noeud représentant l'étoile
	 * @return l'étoile stockée dans le noeud considéré
	 */
	public Etoile lisEtoile(Node noeud) throws XPathExpressionException {
		String points = (String) xpath.evaluate(POINTS2, noeud, STRING);
		StringTokenizer tokenizer = new StringTokenizer(points, ", ");
		List<Coordonnees> coordonnees = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			double x = Double.parseDouble(tokenizer.nextToken());
			double y = Double.parseDouble(tokenizer.nextToken());
			coordonnees.add(new Coordonnees(x, y));
		}
		Etoile etoile = calculeEtoile(coordonnees);
		String remplissage = (String) xpath.evaluate(FILL, noeud, STRING);
		etoile.setRempli(!remplissage.equals("none"));
		return etoile;
	}

	/**
	 * Renvoie une nouvelle étoile calculée à partir de la liste de ses
	 * coordonnées.
	 * 
	 * Il faut "un peu" réflechir et donc s'aider du trio "feuille, stylo,
	 * cerveau".
	 * 
	 * Indications : calculer les coordonnéeds du centre de l'étoile ; calculer
	 * le(s) rayon(s) en utilisant la méthode Coordonnees.distanceVers ;
	 * calculer le(s) angle(s) en utilisant la méthode Coordonnees.angleVers.
	 * 
	 * @param points La liste des coordonnées des points de l'étoile
	 * @return L'étoile calculée à partir de la liste de ses coordonnées
	 */
	public Etoile calculeEtoile(List<Coordonnees> points) {
		double centreX=0;
		double centreY=0;
		Coordonnees centre;
		for(int i=0; i<points.size(); i++) {
			centreX+=points.get(i).getAbscisse();
			centreY+=points.get(i).getOrdonnee();
		}
		centreX/=points.size();
		centreY/=points.size();
		centre= new Coordonnees(centreX, centreY);
		double taille = points.get(0).distanceVers(centre)*2;
		Coordonnees position = new Coordonnees(centreX-taille/2, centreY-taille/2);
		int nombreBranches = points.size()/2;
		double anglePremiereBranche =centre.angleVers(points.get(0));
		double longueurBranche =1-(points.get(1).distanceVers(centre)/(points.get(0).distanceVers(centre)));
		return new Etoile(position, taille, nombreBranches, anglePremiereBranche, longueurBranche);
	}

	public PolygoneRegulier lisPolygoneRegulier(Node noeud) throws XPathExpressionException {
		String points = (String) xpath.evaluate(POINTS2, noeud, STRING);
		StringTokenizer tokenizer = new StringTokenizer(points, ", ");
		List<Coordonnees> coordonnees = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			double x = Double.parseDouble(tokenizer.nextToken());
			double y = Double.parseDouble(tokenizer.nextToken());
			coordonnees.add(new Coordonnees(x, y));
		}
		PolygoneRegulier polygone = calculePolygone(coordonnees);
		String remplissage = (String) xpath.evaluate(FILL, noeud, STRING);
		polygone.setRempli(!remplissage.equals("none"));
		return polygone;
	}

	public PolygoneRegulier calculePolygone(List<Coordonnees> points) {
		double centreX=0;
		double centreY=0;
		Coordonnees centre;
		for(int i=0; i<points.size(); i++) {
			centreX+=points.get(i).getAbscisse();
			centreY+=points.get(i).getOrdonnee();
		}
		centreX/=points.size();
		centreY/=points.size();
		centre= new Coordonnees(centreX, centreY);
		double taille = points.get(0).distanceVers(centre)*2;
		Coordonnees position = new Coordonnees(centreX-taille/2, centreY-taille/2);
		int nombreDeCotes = points.size();
		double anglePremierSommet =centre.angleVers(points.get(0));
		return new PolygoneRegulier(position, taille, nombreDeCotes, anglePremierSommet);
	}

	/**
	 * Convertis une couleur représentée par une chaîne de caractères en une
	 * couleur compatible avec l'API Java. La chaîne en question est au format
	 * "#RRVVBB" où RR représente la valeur héxadécimale de la composante rouge,
	 * VV celle de la composante verte et BB celle de la composante bleue.
	 * 
	 * @param couleur la couleur représentée par une chaîne au format "#RRVVBB"
	 * @return la couleur compatible avec l'API Java
	 */
	public Color lisCouleur1(Node noeud) throws XPathExpressionException {
		String couleur = (String) xpath.evaluate("@stroke", noeud, STRING);
		try {
			return Color.decode(couleur);
		}
		catch (NumberFormatException e) {
			return Forme.COULEUR_PAR_DEFAUT;
		}
	}
	
	public Color lisCouleur2(Node noeud) throws XPathExpressionException {
		String couleur = (String) xpath.evaluate(FILL, noeud, STRING);
		try {
			return Color.decode(couleur);
		}
		catch (NumberFormatException e) {
			return Forme.COULEUR_PAR_DEFAUT;
		}
	}

}
