package control;

import model.Etat;

import java.awt.event.MouseEvent;


/**
 * Ça définit la manière dont l’état du modèle change. Il effectue les changements dans le modèle et il s’occupe ainsi
 * de la gestion des évènements de la souris.
 *
 * @author Mahdi RANJBAR
 */
public class Control extends MouseControler {

    //Attribut etat
    public Etat etat;


    /**
     * Ça crée un contrôle sur un etat spécifique.
     *
     * @param etat Un objet de la classe Etat représentant l’etat actuel du jeu.
     */
    public Control(Etat etat) {
        this.etat = etat;
    }

    /**
     * Ça définit la gestion de la clique de souris de façon que lorsqu’on clique dans l’interface, la position y de
     * l’ovale est monté en appelant la méthode jump.
     *
     * @param e Un objet de la classe MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Etat.exit) {
            etat.jump();
        }
    }
}
