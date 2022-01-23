package view;

import control.Control;
import model.Etat;
import model.Voler;

import java.awt.*;
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
    public Control ctl = new Control(etat, this);

    //Initialisation de l'attribut voler
    public Voler voler = new Voler(etat);

    //Initialisation de l'attribut avancer
    public Avancer avancer = new Avancer(etat.parcours, this);

    /**
     * Ça crée le panel et active les deux threads pour l’évènement mouseClicked et l'avancement de la ligne brisée.
     */
    public Affichage() {
        setPreferredSize(new Dimension(LARG, HAUT));
        this.addMouseListener(ctl);
        voler.start();
        avancer.start();
    }

    /**
     * Ça permet de dessiner toute l'interface graphique du jeu.
     *
     * @param g Une instance de la classe Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawOval(g);
        drawLines(g);
    }

    /**
     * Ça permet de dessiner l'oval.
     *
     * @param g Une instance de la classe Graphics
     */
    void drawOval(Graphics g) {
        g.drawOval(OVAL_X, etat.getHauteur(), OVAL_LARG, OVAL_HAUT);
    }

    /**
     * Ça permet de dessiner la ligne brisée.
     *
     * @param g Une instance de la classe Graphics
     */
    void drawLines(Graphics g) {
        g.setColor(Color.RED); //Couleur rouge pour la ligne

        ArrayList<Point> tmp = etat.parcours.getParcours();

        //Ça dessine une ligne entre chaque pair de point de la liste progressivement
        for (int i = 0; i < tmp.size() - 1; i++) {
            g.drawLine(tmp.get(i).x, tmp.get(i).y, tmp.get(i + 1).x, tmp.get(i + 1).y);
        }
    }

    //Méthodes pour appel à repaint()

    /**
     * Ça permet de mettre à jour la nouvelle position de l’oval après chaque appel à la méthode jump().
     */
    public void updateHauteurOval_jump() {
        repaint(OVAL_X - 1, etat.getHauteur() - 1, OVAL_LARG + 2, OVAL_HAUT + SAUT_TAILLE + 2);
    }

    /**
     * Ça permet de mettre à jour la nouvelle position de l’oval après chaque appel à la méthode moveDown().
     */
    public void updateHauteurOval_moveDown() {
        repaint(OVAL_X - 1,
                etat.getHauteur() - Etat.DESC_TAILL - 1,
                OVAL_LARG + 2,
                OVAL_HAUT + Etat.DESC_TAILL + 2);
        revalidate();
    }

    /**
     * Ça permet de mettre à jour la nouvelle position de la ligne brisée.
     */
    public void updateLines() {
        repaint();
        revalidate();
    }

}
