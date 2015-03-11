package uno.iut.fr.uno.modele.iStrategyCarte;

import uno.iut.fr.uno.modele.Board;
import uno.iut.fr.uno.modele.IStrategyCarte;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class ChangeSens implements IStrategyCarte{
    private static ChangeSens ourInstance = new ChangeSens();

    public static ChangeSens getInstance() {
        return ourInstance;
    }

    private ChangeSens() {
    }

    public void execute(Carte carte){
        Board.getInstance().changeOrderPlayer();
    }
}
