package view;

import model.Oiseau;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ça représente l'affichage de l'oiseau du jeu.
 *
 * @author Mahdi RANJBAR
 */
public class VueOiseau {

    //Hauteur de l'oiseau
    public static final int HAUTEUR_OISEAU = 100;

    //Largeur de l'oiseau
    public static final int LARGEUR_OISEAU = 100;

    //Liste des oiseaux
    public ArrayList<Oiseau> oiseaux = new ArrayList<Oiseau>();

    /**
     * Ça crée une liste d'oiseau.
     *
     * @throws IOException
     */
    public VueOiseau() throws IOException {

        //Création d'un oiseau
        Oiseau oiseau = new Oiseau(this);

        //Ajout à la liste
        oiseaux.add(oiseau);

        //Activation de l'oiseau (thread)
        oiseau.start();
    }


    /**
     * Ça permet de dessiner des oiseaux dans le jeu.
     *
     * @param g Une instance de la classe Graphics
     */
    public void dessiner(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //Affichage de chaque oiseau un par un
        for (int i = 0; i < oiseaux.size(); i++) {
            Oiseau oiseau = oiseaux.get(i);
            g2d.drawImage(
                    oiseau.imgs.get(oiseau.etat),
                    oiseau.position, oiseau.hauteur,
                    LARGEUR_OISEAU, HAUTEUR_OISEAU,
                    null);
        }
    }

    /**
     * Ça permet d'ajouet des nouvels oiseaux dans le jeu.
     *
     * @throws IOException
     */
    public void birdGenerator() throws IOException {

        //Création d'un oiseau
        Oiseau oiseau = new Oiseau(this);

        //Ajout à la liste
        oiseaux.add(oiseau);

        //Activation de l'oiseau (thread)
        oiseau.start();
    }

    /**
     * Ça permet de supprimer un oiseau de la liste des oiseaux.
     *
     * @param oiseau Un objet de la classe Oiseau
     */
    public void birdRemover(Oiseau oiseau) {
        oiseaux.remove(oiseau);
    }
}
