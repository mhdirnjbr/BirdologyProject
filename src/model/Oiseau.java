package model;

import view.Affichage;
import view.VueOiseau;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Ça un thread representant l'oiseau dans le jeu.
 *
 * @author Mahdi RANJBAR
 */
public class Oiseau extends Thread {

    //Ordonnée maximum de l'oiseau
    public static final int HAUTEUR_OISEAU_MAX = 300;

    //Delai minimum
    public static final int DELAI_MIN = 50;

    //Delai maximum
    public static final int DELAI_MAX = 100;

    //Vitesse d'avancement
    public static final int AVANCEMENT_SPEED = 10;

    //Temps entre chaque mise à jour de l’affichage pour l’oiseau
    public int delai;

    //Etat initial de l'oiseau
    public int etat = 0;

    //Hauteur de l'oiseau
    public int hauteur;

    //Abscisse initial de l'oiseau
    public int position = -50;

    //Liste des images de different etat de l'oiseau
    public ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();

    //Variable boolean pour arreter le thread d'oiseau
    public boolean exit = false;

    //Attribut vueOiseau
    public VueOiseau vueOiseau;

    //Instance de la classe Random permettant de générer des nombres aléatoires
    public Random random = new Random();


    /**
     * Ça crée un oiseau.
     *
     * @param vueOiseau Un objet de la classe VueOiseau
     * @throws IOException
     */
    public Oiseau(VueOiseau vueOiseau) throws IOException {

        this.vueOiseau = vueOiseau;
        this.delai = DELAI_MIN + random.nextInt(DELAI_MAX);
        this.hauteur = random.nextInt(HAUTEUR_OISEAU_MAX);

        //On ajoute les images un par un dans la liste
        for (int i = 1; i < 9; i++) {
            imgs.add(ImageIO.read(
                    new File(
                            "/Users/mahdiranjbar/IdeaProjects/BirdologyProject/src/pics/animate-bird-" + i + ".jpg")));
        }


    }

    /**
     * Ça permet de faire avancer l'oiseau constamment tout en changeant son etat à chaque fois. Ainsi qu'une fois
     * l'oiseau sort du panel du jeu, on le supprime de la liste des oiseaux.
     */
    @Override
    public void run() {
        while (!exit) {
            position += AVANCEMENT_SPEED;
            etat = (etat + 1) % 8;

            //Verification pour savoir si l'oiseau sort du panel du jeu
            if (position > Affichage.LARG) {
                exit = true;
                vueOiseau.birdRemover(this);
            }
            try {
                Thread.sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}