package model;


/**
 * C'est un thread permettant d'avancer la ligne brisée vers la gauche.
 *
 * @author Mahdi RANJBAR
 */
public class Avancer extends Thread {

    //Montant de chaque pause
    public static final int NB_MILLISECONDS = 100;

    //Attribut parcours
    public Etat etat;


    /**
     * Ça crée un thread avancer sur un état spécifique.
     *
     * @param etat Un objet de la classe Etat representant l'etat actuel du jeu
     */
    public Avancer(Etat etat) {
        this.etat = etat;

    }

    /**
     * Ça permet constamment de faire avancer la ligne brisée vers la gauche avec une pause de quelque millisecondes.
     */
    @Override
    public void run() {
        while (!Etat.exit) {

            //Mise à jour le score
            etat.parcours.updateScore();

            //Avancer la ligne brisée
            etat.parcours.updateParcours();
            try {
                Thread.sleep(NB_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}