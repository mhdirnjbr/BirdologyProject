package model;

import view.Affichage;

import java.awt.*;

/**
 * Ça définit l’ensemble des données qui caractérisent l’état de l’interface.
 *
 * @author Mahdi RANJBAR
 */
public class Etat {

    //Taille de pixel pour le dépalcement vers le bas
    public static final int DESC_TAILL = 10;

    //Hauteur initiale de l'oval
    public int hauteur = Affichage.OVAL_Y;

    //Variable boolean pour arreter les threads et eventuellemnt le jeu
    public static boolean exit;

    //Attribut affichage
    public Affichage affichage;

    //Initialisation de l'attribut parcours
    public Parcours parcours = new Parcours();


    /**
     * Ça crée un etat sur un affichage spécifique.
     *
     * @param affichage Un objet de la classe Affichage représentant l’affichage actuel de l’interface graphique.
     */
    public Etat(Affichage affichage) {
        this.affichage = affichage;
    }

    /**
     * Ça prend la positon y de l’ovale.
     *
     * @return Un entier représentant la position y de l’ovale.
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Ça fait monter l’ovale de quelques pixels (TAILLE_SAUT).
     */
    public void jump() {

        //Vérification pour ne pas dépasser la borne
        if (hauteur >= Affichage.OVAL_Y_MIN) {
            hauteur -= Affichage.SAUT_TAILLE;
        }
    }

    /**
     * Ça fait descendre l’oval de quelque pixel (DESC_TAILL).
     */
    public void moveDown() {

        //Vérification pour ne pas dépasser la borne
        if (hauteur < Affichage.OVAL_Y_MAX) {
            hauteur += DESC_TAILL;
        }
    }

    /**
     * Ça renvoie vrai si l'oval sort de la ligne brisée
     *
     * @return Un boolean representant si l'oval est sorti de la ligne brisée ou pas
     */
    public boolean testPerdu() {

        //L'abscisse dans lequel la ligne brisée arrive au milieu de l'oval
        int limite = Affichage.OVAL_X + 10;

        //Premier point de la liste des points (premier point de la ligne brisée)
        Point firstPoint = parcours.points.get(0);

        //Si le premier point n'est pas encore arrivé à l'oval on renvoie faux (debut du jeu)
        if (firstPoint.x > limite) {
            return false;
        }

        //2 point avant et après la limite (l'oval)
        Point p1, p2;

        //Compteur
        int i = 0;

        //On commence à trouver du debut de la liste des points, les 2 points qui sont avant et apres la limite
        do {
            p1 = parcours.getPoints().get(i);
            p2 = parcours.getPoints().get(i + 1);
            i++;
        } while (!(p1.x <= limite && p2.x > limite));

        //On calcule la pente de la ligne crée par ces 2 points
        double slope = parcours.slope(p1.x, p1.y, p2.x, p2.y);

        /*
         * Pour trouver la valeur de b (y = ax + b), on prend le premier point et on le met dans l'equation, une fois
         * b calculé, on mets le le milieu de l'oval dans l'equation pour savoir ou il est son ordonnée.
         */
        double b = p1.y - (slope * p1.x);
        double y = (slope * limite) + b;

        //On verifie si y est à l'interieur de l'oval
        return y < this.hauteur || y > this.hauteur + Affichage.OVAL_HAUT;
    }
}