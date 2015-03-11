package uno.iut.fr.uno.modele.iStrategyCarte;

import uno.iut.fr.uno.modele.IStrategyCarte;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class MoreCarteChangColor implements IStrategyCarte {
    private static MoreCarteChangColor ourInstance = new MoreCarteChangColor();

    public static MoreCarteChangColor getInstance() {
        return ourInstance;
    }

    private MoreCarteChangColor() {
    }

    public void execute(Carte carte){

    }
}
