package view;

import model.Etat;

/**
 * C'est un thread permettant de mettre à jour l'affichage de l'interface graphique du jeu.
 *
 * @author Mahdi RANJBAR
 */
public class DisplayThread extends Thread{

    //Montant de chaque pause
    public static final int NB_MILLISECONDS = 40;

    //Attribut Affichage
    public Affichage affichage;

    /**
     * Ça crée un thread DisplayThread sur un affichage spécifique.
     *
     * @param affichage Un objet de la classe Affichage représentant l'affichage actuel du jeu
     */
    public DisplayThread(Affichage affichage){
        this.affichage = affichage;
    }

    /**
     * Ça permet de mettre à jour l'affichage du jeu.
     */
    @Override
    public void run() {
        while (!Etat.exit) {
            affichage.revalidate();
            affichage.repaint();
            try {
                Thread.sleep(NB_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}