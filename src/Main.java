import view.Affichage;
import view.FenetrePrincipale;
import javax.swing.*;


/**
 * Pour mettre en œuvre tout le projet, autrement dit le point de déclenchement.
 *
 * @author Mahdi RANJBAR
 */
public class Main {

    public static void main(String[] args) {


        FenetrePrincipale fentre = new FenetrePrincipale();

        fentre.add(new Affichage());

        fentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fentre.pack();
        fentre.setVisible(true);

    }
}