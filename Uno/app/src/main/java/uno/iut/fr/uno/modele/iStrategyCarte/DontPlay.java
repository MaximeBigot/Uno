package uno.iut.fr.uno.modele.iStrategyCarte;

import uno.iut.fr.uno.modele.IStrategyCarte;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class DontPlay implements IStrategyCarte {
    private static DontPlay ourInstance = new DontPlay();

    public static DontPlay getInstance() {
        return ourInstance;
    }

    private DontPlay() {
    }

    public void execute (Carte carte){

    }
}
