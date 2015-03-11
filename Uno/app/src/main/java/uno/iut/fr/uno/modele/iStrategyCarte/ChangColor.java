package uno.iut.fr.uno.modele.iStrategyCarte;

import uno.iut.fr.uno.modele.Board;
import uno.iut.fr.uno.modele.Color;
import uno.iut.fr.uno.modele.IStrategyCarte;
import uno.iut.fr.uno.modele.carte.BlackCarteEvent;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class ChangColor implements IStrategyCarte{
    private static ChangColor ourInstance = new ChangColor();

    public static ChangColor getInstance() {
        return ourInstance;
    }

    private ChangColor() {
    }

    public void execute(Carte carte){
        /*recupérer couleur du joueur*/
        ((BlackCarteEvent)carte).setColorPlay(Color.Green);
    }
}
