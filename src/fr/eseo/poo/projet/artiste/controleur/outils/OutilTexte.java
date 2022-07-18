package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Texte;
import fr.eseo.poo.projet.artiste.vue.formes.VueTexte;

public class OutilTexte extends OutilForme {

	private static final long serialVersionUID = 1L;
	public static final String ENTREE_TEXTE_NOM = "Texte à ajouter";
	public static final String TAILLE_TEXTE_NOM = "Taille de Police";
	private JFrame fenetre = new JFrame("Option texte");
	private JTextArea textefield;
	private JSpinner spinnerTaille;

	/* Display a frame to parameterize the texts. */
	public OutilTexte() {
		fenetre.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		JPanel panneauTexte = new JPanel();
		panneauTexte.setLayout(new BoxLayout(panneauTexte, BoxLayout.Y_AXIS));
		JLabel labelTexte = new JLabel(ENTREE_TEXTE_NOM);
		textefield = new JTextArea();
		textefield.setName(ENTREE_TEXTE_NOM);
		textefield.setAlignmentX(Component.LEFT_ALIGNMENT);
		DocumentListener listener = createListener();
		textefield.getDocument().addDocumentListener(listener);
		labelTexte.setLabelFor(textefield);
		JLabel labelTailleTexte = new JLabel(TAILLE_TEXTE_NOM);
		spinnerTaille = new JSpinner(new SpinnerNumberModel(24, 12, 180, 1));
		spinnerTaille.setMaximumSize(new Dimension(45, 30));
		spinnerTaille.setName(TAILLE_TEXTE_NOM);
		spinnerTaille.setAlignmentX(-20);
		labelTailleTexte.setLabelFor(spinnerTaille);
		panneauTexte.add(labelTexte);
		panneauTexte.add(textefield);
		panneauTexte.add(labelTailleTexte);
		panneauTexte.add(spinnerTaille);
		fenetre.setLocation(50, 50);
		fenetre.add(panneauTexte);
		fenetre.setMinimumSize(new Dimension(250, 120));
		fenetre.setResizable(false);
		fenetre.pack();
		fenetre.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
		if (event.getClickCount() == 1) {
			VueTexte vueTexte = creerVueForme();
			getPanneauDessin().ajouterVueForme(vueTexte);
		}
		getPanneauDessin().repaint();
	}

	@Override
	public void mousePressed(MouseEvent event) {
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.setFin(new Coordonnees(event.getX(), event.getY()));
	}

	/* Create a text according to the action of the user drawing.
	 * (See the documentation in the Application for more details.)
	 * (Called by OutilForme.)*/
	@Override
	protected VueTexte creerVueForme() {
		Texte texte = new Texte(getFin(), this.textefield.getText());
		texte.setPolice((int) spinnerTaille.getValue());
		texte.setCouleurContour(getPanneauDessin().getCouleurContourCourante());
		return new VueTexte(texte);
	}

	@Override
	public void destroy() {
		this.fenetre.dispose();
	}

	/* Create a listener that automatically resize the text field. */
	private DocumentListener createListener() {
		return new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				resize();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				resize();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				resize();
			}

			private void resize() {
				Dimension dim = textefield.getPreferredSize();
				double width = dim.getWidth();
				double height = dim.getHeight();
				width = (width < 200) ? 200 : width;
				height = (height < 20) ? 20 : height;
				dim.setSize(width + 50, height + 100);
				fenetre.setMinimumSize(dim);
				fenetre.pack();
				fenetre.setVisible(true);
			}
		};
	}
}
