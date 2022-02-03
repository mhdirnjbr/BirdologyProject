package model;

import view.Affichage;

import javax.swing.*;

/**
 * C’est un thread permettant de faire redescendre progressivement l'oiseau.
 *
 * @author Mahdi RANJBAR
 */
public class Voler extends Thread {

    //Montant de chaque pause
    public static final int NB_MILLISECONDS = 150;

    //Attribut Etat
    public Etat etat;

    //Attribut Affichage
    public Affichage affichage;

    /**
     * Ça crée un thread voler sur un état et affichage spécifique.
     *
     * @param etat      Un objet de la classe Etat représentant l’etat actuel du jeu
     * @param affichage Un objet de la classe Affichage représentant l'affichage actuel du jeu
     */
    public Voler(Etat etat, Affichage affichage) {
        this.etat = etat;
        this.affichage = affichage;

    }

    /**
     * Ça permet de faire descendre l’oval tout en verifiant s'il n'est pas sorti de la ligne brisée.
     */
    @Override
    public void run() {
        while (!Etat.exit) {

            //Desente de l'oiseau
            etat.moveDown();

            //Verification si le joueur est perdu ou non
            Etat.exit = etat.testPerdu();
            try {
                Thread.sleep(NB_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Affichage d'un message pour le score du joueur lorsqu'il pert
        if (Etat.exit) {
            JOptionPane.showMessageDialog(affichage,
                    "Your score is " + etat.parcours.getScore() + " !",
                    "SCORE",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

}