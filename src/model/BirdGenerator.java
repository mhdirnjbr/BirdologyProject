package model;

import view.VueOiseau;

import java.io.IOException;

/**
 * C'est un thread permettant d'ajouter des oiseaux dans le jeu.
 *
 * @author Mahdi RANJBAR
 */
public class BirdGenerator extends Thread {

    //Montant de chaque pause
    public static final int NB_MILLISECONDS = 3000;

    //Attribut VueOiseau
    public VueOiseau vueOiseau;

    /**
     * Ça crée un thread BirdGenerator sur un vueOiseau spécifique.
     *
     * @param vueOiseau Un objet de la classe VueOiseau
     */
    public BirdGenerator(VueOiseau vueOiseau) {
        this.vueOiseau = vueOiseau;
    }

    /**
     * Ça permet constamment d'ajouter les oiseaux dans le jeu.
     */
    @Override
    public void run() {
        while (!Etat.exit) {
            try {
                vueOiseau.birdGenerator();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(NB_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}