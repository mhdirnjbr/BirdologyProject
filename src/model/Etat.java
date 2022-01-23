package model;


import view.Affichage;

/**
 * Ça définit l’ensemble des données qui caractérisent l’état de l’interface.
 *
 * @author Mahdi RANJBAR
 */
public class Etat {

    //Hauteur initiale
    public int hauteur = Affichage.OVAL_Y;

    //Taille de pixel pour le dépalcement vers le bas
    public static final int DESC_TAILL = 10;

    //Attribut affichage
    public Affichage affichage;

    //Initialisation de l'attribut affichage
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
     * Ça fait monter l’ovale de TAILLE_SAUT.
     */
    public void jump() {

        if (hauteur >= Affichage.OVAL_Y_MIN) { //Vérification pour ne pas dépasser la borne
            hauteur -= Affichage.SAUT_TAILLE;
            ////Mettre à jour l’affichage de l’oval
            affichage.updateHauteurOval_jump();
        }
    }

    /**
     * Ça fait descendre l’oval de quelque pixel (DESC_TAILL).
     */
    public void moveDown() {
        if (hauteur < Affichage.OVAL_Y_MAX) { //Vérification pour ne pas dépasser la borne
            hauteur += DESC_TAILL;
        }
        //Mettre à jour l’affichage de l’oval
        affichage.updateHauteurOval_moveDown();
    }
}
