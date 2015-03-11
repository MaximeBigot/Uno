package uno.iut.fr.uno.modele.carte;

import android.media.Image;

import uno.iut.fr.uno.modele.Color;
import uno.iut.fr.uno.modele.ConstValue;
import uno.iut.fr.uno.modele.IStrategyCarte;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class CarteEvent extends Carte {
    private IStrategyCarte strategy = null;

    public CarteEvent(Color color, IStrategyCarte strategy){
        super(color, ConstValue.EVENTCARTEVALUE);
        this.strategy = strategy;
    }
}
