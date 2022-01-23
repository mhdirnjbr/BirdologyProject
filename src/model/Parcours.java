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
    public static final int POINT_Y_MAX = 300;

    //Abscisse du premier point du jeu
    public static final int FIRST_POINT_X = 600;

    //Abscisse maximum d'un point
    public static final int POINT_X_MAX = Affichage.LARG + 100;

    //Rapidité d'avancement de la ligne brisée vers la gauche
    public static final int SPEED = 10;

    //Pente maximum
    public static final int SLOPE_MAX = 1;

    //Pente minimum
    public static final int SLOPE_MIN = -3;

    //Score du jouer
    public int position = 0;


    /**
     * Ça crée une liste des points.
     */
    public Parcours() {
        //Ajout du premier point
        points.add(new Point(FIRST_POINT_X, POINT_Y_MIN + random.nextInt(POINT_Y_MAX)));

        //Ajout du deuxieme point
        addNewPoint();
    }

    /**
     * Ça prend la liste des points représentant la ligne brisée du jeu.
     *
     * @return Une liste des points.
     */
    public ArrayList<Point> getParcours() {
        //Une liste de points temporaire
        ArrayList<Point> tmp = new ArrayList<Point>();

        //Initialisation de la liste temporaire à la liste des points, ayant comme abcisse, abcisse - position
        for (Point point : points) {
            tmp.add(new Point(point.x - position, point.y));
        }

        /*
         * Lorsque le dernier point rentre dans la fenêtre visible, on crée un point supplémentaire pour que la ligne
         * brisée ne s’interrompe pas.
         */
        if (tmp.get(tmp.size() - 1).x < Affichage.LARG + SPEED) {
            addNewPoint();
        }

        //Lorsque le deuxième point sort de la zone visible, on retire le premier point de la liste.
        if (tmp.get(1).x < 0) {
            points.remove(0);
        }
        return tmp;
    }

    /**
     * Ça permet dans un premier temps de générer un point aléatoire tout en vérifiant la valeur de la pente de la
     * ligne engendrée par ce point avec la dernière point de la liste et ensuite de l’ajouter à la liste des points.
     */
    public void addNewPoint() {
        //Position x du point
        int x2 = POINT_X_MAX + position;

        //Position y du point
        int y2 = POINT_Y_MIN + random.nextInt(POINT_Y_MAX);

        //Pente de ligne entre les coordonnées générés et le dernier point de la liste (slope = (y2-y1) / (x2 - x1))
        double slope = ((double) y2 - points.get(points.size() - 1).y) / (x2 - points.get(points.size() - 1).x);

        /*
         * On régularise les pentes qui ne sont pas appropriées en fonction de la vitesse de chute de l'oval. Donc pour
         * les lignes dont la pente est au-delà de la limite, on modifie la valeur de l'ordonée de façon que la pente
         * devienne égale à la limite.
         */
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

    /**
     * Ça récupère le score du joueur.
     *
     * @return Un entier représentant le score du joueur.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Ça augment la valeur de position (score du joueur) du montant du SPEED.
     */
    public void updatePosition() {
        this.position += SPEED;
    }
}
