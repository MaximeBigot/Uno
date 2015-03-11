package uno.iut.fr.uno.modele.iStrategyCarte;

import uno.iut.fr.uno.modele.IStrategyCarte;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class MoreCarte implements IStrategyCarte {
    private static MoreCarte ourInstance = new MoreCarte();

    public static MoreCarte getInstance() {
        return ourInstance;
    }

    private MoreCarte() {
    }

    public void execute(Carte carte){

    }
}
