package model;


import view.Affichage;

/**
 * Ça définit l’ensemble des données qui caractérisent l’état de l’interface.
 * @author Mahdi RANJBAR
 */
public class Etat {

    //Hauteur initiale
    public int hauteur=300;

    /**
     * Ça prend la positon y de l’ovale.
     * @return Un entier représentant la position y de l’ovale.
     */
    public int getHauteur(){
        return this.hauteur;
    }


    /**
     * Ça fait monter la position y de l’ovale de TAILLE_SAUT.
     */
    public void jump(){

        //Vérification pour ne pas dépasser la borne du panel
        if(hauteur- Affichage.SAUT_TAILLE < 0){
            System.out.println("ATTENTION À LA BORNE!");
        }
        else {
            hauteur -= Affichage.SAUT_TAILLE;
        }
    }
}
