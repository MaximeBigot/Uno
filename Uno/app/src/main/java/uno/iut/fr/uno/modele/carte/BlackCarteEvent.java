package uno.iut.fr.uno.modele.carte;

import uno.iut.fr.uno.modele.Color;
import uno.iut.fr.uno.modele.ConstValue;
import uno.iut.fr.uno.modele.IStrategyCarte;

/**
 * Created by Max on 04/03/2015.
 */
public class BlackCarteEvent extends CarteEvent {
    private Color colorPlay;

    public BlackCarteEvent(IStrategyCarte strategy){
        super(Color.Black, strategy);
        this.carteValue = ConstValue.BLACKEVENTCARTEVALUE;
    }
    public void setColorPlay (Color color){
        if(color == Color.Black)
            throw new IllegalArgumentException();
        this.colorPlay = color;
    }
    public Color getColorPlay(){
        return this.colorPlay;
    }

    @Override
    public boolean equals(Object o){
        if(((Carte) o).getColor().equals(this.getColorPlay()))
            return true;
        return false;
    }


}
