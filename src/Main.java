import model.Avancer;
import model.BirdGenerator;
import view.DisplayThread;
import model.Voler;
import view.Affichage;
import view.FenetrePrincipale;

import javax.swing.*;
import java.io.IOException;


/**
 * Pour mettre en œuvre tout le projet, autrement dit le point de déclenchement.
 *
 * @author Mahdi RANJBAR
 */
public class Main {

    public static void main(String[] args) throws IOException {

        //Création du panel du jeu
        Affichage affichage = new Affichage();

        //Création de la fenetre principale du jeu
        FenetrePrincipale fentre = new FenetrePrincipale();

        //Ajout du panel à la fenetre principale du jeu
        fentre.add(affichage);


        //Fermer le jeu en cliquant sur le bouton fermer de la fenetre
        fentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Rendre visible la fenetre
        fentre.pack();
        fentre.setVisible(true);


        //Création d'un thread voler
        Voler voler = new Voler(affichage.etat, affichage);

        //Création d'un thread avancer
        Avancer avancer = new Avancer(affichage.etat);

        //Création d'un thread birdGenerator
        BirdGenerator birdGenerator = new BirdGenerator(affichage.vueOiseau);

        //Création d'un thread displayThread
        DisplayThread displayThread = new DisplayThread(affichage);


        //Activation du thread voler
        voler.start();

        //Activation du thread avancer
        avancer.start();

        //Activation du thread birdGenerator
        birdGenerator.start();

        //Activation du thread displayThread
        displayThread.start();
    }
}