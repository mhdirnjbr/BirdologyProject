package model;

import view.Affichage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ça représente la ligne briśee du jeu.
 *
 * @author Mahdi RANJBAR
 */
public class Parcours {

    //Initialisation d'une liste des points
    public ArrayList<Point> points = new ArrayList<Point>();

    //Instance de la classe Random permettant de générer des nombres aléatoires
    public Random random = new Random();

    //Ordonnée minimum d'un point
    public static final int POINT_Y_MIN = 50;

    //Ordonnée maximum d'un point
    public static final int POINT_Y_MAX = 350;

    //Intervalle de l'ordonnée
    public static final int Y_RANGE = POINT_Y_MAX - POINT_Y_MIN + 1;

    //Distance minimum entre les abscisse de deux points
    public static final int DISTANCE_X_MIN = 10;

    //Abscisse maximum d'un point
    public static final int POINT_X_MIN = Affichage.LARG + DISTANCE_X_MIN;

    //Intervalle de l'abscisse
    public static final int X_RANGE = 100;

    //Rapidité d'avancement de la ligne brisée vers la gauche
    public static final int SPEED = 5;

    //Abscisse du premier point du jeu
    public static final int FIRST_POINT_X = 600;

    //Pente maximum
    public static final int SLOPE_MAX = 1;

    //Pente minimum
    public static final int SLOPE_MIN = -3;

    //Score du jouer
    public int score = 0;

    //Variable boolean pour savoir si ou non compter le score
    public boolean start_flag = false;


    /**
     * Ça crée une liste des points.
     */
    public Parcours() {
        //Ajout du premier point
        points.add(new Point(FIRST_POINT_X, POINT_Y_MIN + random.nextInt(Y_RANGE)));

        //Ajout du deuxieme point
        addNewPoint();
    }


    /**
     * Ça récupère la liste des points representant la ligne brisée.
     *
     * @return Liste des points representant la ligne brisée
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Ça met à jour la liste des points en diminuant l'abscisse de chaque point. Ainsi si le dernier point entre dans
     * la zone visible, il ajoute un nouveau point à la liste et si un point sort de la zone visible, il le supprime de
     * la liste.
     */
    public void updateParcours() {

        //Dernier point
        Point lastPoint = points.get(points.size() - 1);

        //Diminuant quelquel pixel (SPEED) de l'abscisse de chaque point
        for (Point point : points) {
            point.x -= SPEED;
        }

        //Si le dernier point entre dans la zone visible, il ajoute un nouveau point à la list
        if (lastPoint.x < Affichage.LARG + SPEED) {
            addNewPoint();
        }

        //Si le deuxieme point sort de la zone visible, il supprime le premier point de la liste
        if (points.get(1).x < 0) {
            points.remove(0);
        }
    }

    /**
     * Ça crée un nouveau point tout en respectant à la limite de la pente et l'ajoute à la liste.
     */
    public void addNewPoint() {

        //Pente
        double slope;

        //Coordonnees du nouveau point potentiel
        int x2, y2;

        //On prend les variables random pour x2 et y2 jusqu'a ce qu'on respecte la pente qui crée avec le dernier point
        do {
            x2 = POINT_X_MIN + random.nextInt(X_RANGE);
            y2 = POINT_Y_MIN + random.nextInt(Y_RANGE);
            slope = slope(x2, y2, points.get(points.size() - 1).x, points.get(points.size() - 1).y);
        } while (slope > SLOPE_MAX || slope < SLOPE_MIN);


        /*
         * Methode 2: Au lieu de prendre les variable random et tomber dans une boucle, on réduit nous-meme la pente
         *
         * Point lastPoint = points.get(points.size() - 1);
         * x2 = POINT_X_MIN + random.nextInt(X_RANGE);
         * y2 = POINT_Y_MIN + random.nextInt(Y_RANGE);
         * slope = slope(x2, y2, lastPoint.x, lastPoint.y);
         *
         * if (slope > SLOPE_MAX) {
         *    y2 = (SLOPE_MAX * (x2 - lastPoint.x)) + lastPoint.y;
         * }
         * if (slope < SLOPE_MIN) {
         *    y2 = (SLOPE_MIN * (x2 - lastPoint.x)) + lastPoint.y;
         *  }
         */

        //Ajout à la liste des points
        points.add(new Point(x2, y2));

    }

    /**
     * Ça calcule la pente de la ligne crée par 2 points
     *
     * @param x1 Un entier representant l'abscisse du premier point
     * @param y1 Un entier representant l'ordonnee du premier point
     * @param x2 Un entier representant l'abscisse du deuxieme point
     * @param y2 Un entier representant l'ordonnee du deuxieme point
     * @return La pente crée par les deux points
     */
    public double slope(int x1, int y1, int x2, int y2) {
        //La formule slope = (y2-y1) / (x2 - x1))
        return ((double) y2 - y1) / (x2 - x1);
    }

    /**
     * Ça récupère le score du joueur.
     *
     * @return Un entier representant le score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Ça met à jour le score du joueur.
     */
    public void updateScore() {

        //Verification pour savoir si la ligne brisée est arrivé à l'oval
        if (points.get(0).x < Affichage.OVAL_X && !start_flag) {

            //Une fois la ligne brisée est arrivée à l'oval, on commence le score du joueur
            start_flag = true;
        }
        if (start_flag) {

            //On augmente le score du joueur
            this.score += SPEED;
        }
    }




    // Approche 2: la position est en meme temps le score du joueur.


    /*
     * Ça prend la liste des points représentant la ligne brisée du jeu.
     *
     * @return Une liste des points.
     *//*

    public ArrayList<Point> getParcours() {
        //Une liste de points temporaire
        ArrayList<Point> tmp = new ArrayList<Point>();

        //Initialisation de la liste temporaire à la liste des points, ayant comme abcisse, abcisse - position
        for (Point point : points) {
            tmp.add(new Point(point.x - position, point.y));
        }

        */
    /*
     * Lorsque le dernier point rentre dans la fenêtre visible, on crée un point supplémentaire pour que la ligne
     * brisée ne s’interrompe pas.
     *//*

        if (tmp.get(tmp.size() - 1).x < Affichage.LARG + SPEED) {
            addNewPoint();
        }

        //Lorsque le deuxième point sort de la zone visible, on retire le premier point de la liste.
        if (tmp.get(1).x < 0) {
            points.remove(0);
        }
        return tmp;
    }

    */
/*
 * Ça permet dans un premier temps de générer un point aléatoire tout en vérifiant la valeur de la pente de la
 * ligne engendrée par ce point avec la dernière point de la liste et ensuite de l’ajouter à la liste des points.
 *//*

    public void addNewPoint() {
        //Position x du point
        int x2 = POINT_X_MAX + position + random.nextInt(RANDOM_X_MAX);

        //Position y du point
        int y2 = POINT_Y_MIN + random.nextInt(POINT_Y_MAX);

        //Pente de ligne entre les coordonnées générés et le dernier point de la liste
        double slope = slope(x2, y2, points.get(points.size() - 1).x, points.get(points.size() - 1).y);

        */
    /*
     * On régularise les pentes qui ne sont pas appropriées en fonction de la vitesse de chute de l'oval. Donc pour
     * les lignes dont la pente est au-delà de la limite, on modifie la valeur de l'ordonée de façon que la pente
     * devienne égale à la limite.
     *//*

        if (slope > SLOPE_MAX) {
            //y2 = a * (x2-x1) + y1 (On considère la valeur de ‘a’ égale à SLOPE_MAX= 1 pour diminuer la pente)
            y2 = (SLOPE_MAX * (x2 - points.get(points.size() - 1).x)) + points.get(points.size() - 1).y;
        }
        if (slope < SLOPE_MIN) {
            //y2 = a * (x2-x1) + y1 (On considère la valeur de ‘a’ égale à SLOPE_MIN= -3 pour diminuer la pente)
            y2 = (SLOPE_MIN * (x2 - points.get(points.size() - 1).x)) + points.get(points.size() - 1).y;
        }
        points.add(new Point(x2, y2));
    }

    */
/*
 * Ça récupère le score du joueur.
 *
 * @return Un entier représentant le score du joueur.
 *//*

    public int getPosition() {
        return position;
    }

    */
/*
 * Ça augment la valeur de position (score du joueur) du montant du SPEED.
 *//*

    public void updatePosition() {
        this.position += SPEED;
    }
*/
}
