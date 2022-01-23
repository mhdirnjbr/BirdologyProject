package view;

import model.Parcours;

/**
 * C'est un thread permettant d'avancer la ligne brisée vers la gauche.
 *
 * @author Mahdi RANJBAR
 */
public class Avancer extends Thread {

    //Montant de chaque pause
    public static final int NB_MILLISECONDS = 200;

    //Attribut parcours
    public Parcours parcours;

    //Attribut affichage
    public Affichage affichage;

    /**
     * Ça crée un thread Avancer sur la ligne brisée et l'affichage spécifique du jeu.
     *
     * @param parcours  Un objet de la classe Parcours représentant la ligne brisée.
     * @param affichage Un objet de la classe Affichage représentant l’affichage actuel de l’interface graphique.
     */
    public Avancer(Parcours parcours, Affichage affichage) {
        this.parcours = parcours;
        this.affichage = affichage;
    }

    /**
     * Ça permet infiniment de faire avancer la ligne brisée vers la gauche avec une pause de quelque millisecondes.
     */
    @Override
    public void run() {
        while (true) {
            parcours.updatePosition();
            affichage.updateLines();
            try {
                Thread.sleep(NB_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}