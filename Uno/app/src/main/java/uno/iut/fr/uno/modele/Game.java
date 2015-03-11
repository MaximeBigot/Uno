package uno.iut.fr.uno.modele;

import java.util.ArrayList;

import uno.iut.fr.uno.modele.carte.BlackCarteEvent;
import uno.iut.fr.uno.modele.carte.Carte;
import uno.iut.fr.uno.modele.carte.CarteEvent;
import uno.iut.fr.uno.modele.iStrategyCarte.ChangColor;
import uno.iut.fr.uno.modele.iStrategyCarte.ChangeSens;
import uno.iut.fr.uno.modele.iStrategyCarte.MoreCarte;
import uno.iut.fr.uno.modele.iStrategyCarte.MoreCarteChangColor;

/**
 * Created by Max on 04/03/2015.
 */
public class Game {
    private static ArrayList<Carte> carteList = new ArrayList<>();

    public static ArrayList<Carte> getGame (){
        createCarteBundle(Color.Yellow);
        createCarteBundle(Color.Blue);
        createCarteBundle(Color.Red);
        createCarteBundle(Color.Green);
        createBlackCarte(MoreCarteChangColor.getInstance());
        createBlackCarte(ChangColor.getInstance());
        return carteList;
    }
    private static void createCarteBundle(Color color){
        carteList.add(new Carte(color,0));
        createCarteBundleDouble(color);
        createCarteBundleDouble(color);
    }
    private static void createCarteBundleDouble(Color color){
        carteList.add(new Carte(color, 1));
        carteList.add(new Carte(color, 2));
        carteList.add(new Carte(color, 3));
        carteList.add(new Carte(color, 4));
        carteList.add(new Carte(color, 5));
        carteList.add(new Carte(color, 6));
        carteList.add(new Carte(color, 7));
        carteList.add(new Carte(color, 8));
        carteList.add(new Carte(color, 9));
        carteList.add(new CarteEvent(color, ChangeSens.getInstance()));
        carteList.add(new CarteEvent(color, ChangColor.getInstance()));
        carteList.add(new CarteEvent(color, MoreCarte.getInstance()));
    }
    private static void createBlackCarte(IStrategyCarte strategy){
        for (int i=0 ; i<4 ; i++)
            carteList.add(new BlackCarteEvent(strategy));
    }
}
