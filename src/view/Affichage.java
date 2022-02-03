package view;

import control.Control;
import model.Etat;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Ça représente l'interface graphique du jeu.
 *
 * @author Mahdi RANJBAR
 */
public class Affichage extends JPanel {

    //Largeur du panel
    public static final int LARG = 600;

    //Hauteur du panel
    public static final int HAUT = 400;

    //Abcisse de l'oval
    public static final int OVAL_X = 50;

    //Ordonnée de l'oval
    public static final int OVAL_Y = 300;

    //Largeur de l'oval
    public static final int OVAL_LARG = 20;

    //Hauteur de l'oval
    public static final int OVAL_HAUT = 100;

    //Ordonnée minimum de l'oval
    public static final int OVAL_Y_MIN = -80;

    //Ordonnée maximum de l'oval
    public static final int OVAL_Y_MAX = HAUT - OVAL_HAUT;

    //Taille du saut de l'oval
    public static final int SAUT_TAILLE = 50;

    //Initialisation de l'attribut etat
    public Etat etat = new Etat(this);

    //Initialisation de l'attribut control
    public Control ctl = new Control(etat);

    //Initialisation de l'attribut vueOiseau
    public VueOiseau vueOiseau = new VueOiseau();


    /**
     * Ça crée le panel du jeu.
     */
    public Affichage() throws IOException {

        //Définition des dimensions du panel
        setPreferredSize(new Dimension(LARG, HAUT));

        //Ajout de l'evenement clique de souris au panel
        this.addMouseListener(ctl);
    }


    /**
     * Ça permet de dessiner toute l'interface graphique du jeu.
     *
     * @param g Une instance de la classe Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Dessinage des oiseaux
        vueOiseau.dessiner(g);

        //Dessinage de l'oval
        drawOval(g);

        //Dessinage de la ligne brisée
        drawLines(g);
    }

    /**
     * Ça permet de dessiner l'oval.
     *
     * @param g Une instance de la classe Graphics
     */
    public void drawOval(Graphics g) {
        g.drawOval(OVAL_X, etat.getHauteur(), OVAL_LARG, OVAL_HAUT);
    }

    /**
     * Ça permet de dessiner la ligne brisée.
     *
     * @param g Une instance de la classe Graphics
     */
    public void drawLines(Graphics g) {

        //Couleur rouge pour la ligne
        g.setColor(Color.RED);

        //Liste temporaire des points pour le dessinage de la ligne brisée
        ArrayList<Point> tmp = etat.parcours.getPoints();

        //Ça dessine une ligne entre chaque pair de point de la liste progressivement
        for (int i = 0; i < tmp.size() - 1; i++) {
            g.drawLine(tmp.get(i).x, tmp.get(i).y, tmp.get(i + 1).x, tmp.get(i + 1).y);
        }
    }
}
