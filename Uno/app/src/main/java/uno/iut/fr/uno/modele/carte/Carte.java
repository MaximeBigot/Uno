package uno.iut.fr.uno.modele.carte;

import java.io.Serializable;
import java.util.Comparator;
import java.util.concurrent.CompletionService;

import uno.iut.fr.uno.modele.Color;

/**
 * Created by Max on 04/03/2015.
 */
public class Carte implements Serializable{
    private Color color;
    protected int carteValue;

    public Carte(Color color, int carteValue){
        this.color = color;
        this.carteValue = carteValue;
    }

    public Color getColor(){
        return color;
    }
    public int getValue() {return carteValue;}
    @Override
    public String toString(){
        return String.valueOf(carteValue) + color;
    }

    @Override
    public boolean equals (Object o){
        if(((Carte) o).getColor().equals(this.getColor()))
            return true;
        if(((Carte)o).getValue() == this.getValue())
            return true;
        return false;
    }
}
