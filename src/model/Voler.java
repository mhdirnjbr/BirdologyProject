package model;

/**
 * C’est un thread permettant de faire redescendre progressivement la valeur de la hauteur, avec une pause de quelques
 * millisecondes entre chaque chute.
 *
 * @author Mahdi RANJBAR
 */
public class Voler extends Thread {

    //Montant de chaque pause
    public static final int NB_MILLISECONDS = 200;

    public Etat etat;

    /**
     * Ça crée un thread voler sur un état spécifique.
     *
     * @param etat Un objet de la classe Etat représentant l’etat actuel du jeu.
     */
    public Voler(Etat etat) {
        this.etat = etat;
    }

    /**
     * Ça permet d’appeler infiniment la méthode moveDown() pour faire descendre l’oval avec une pause de quelque
     * millisecondes.
     */
    @Override
    public void run() {
        while (true) {
            etat.moveDown();
            try {
                Thread.sleep(NB_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
