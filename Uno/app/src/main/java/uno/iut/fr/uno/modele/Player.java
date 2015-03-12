package uno.iut.fr.uno.modele;

import android.bluetooth.BluetoothSocket;

import java.util.ArrayList;

import uno.iut.fr.uno.modele.Bluetooth.ClientThread;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class Player{
    private String name;
    private ArrayList<Carte>cartes;

    public Player(String name){
        cartes = new ArrayList<>();
        this.name = name;
    }

    public void addCarte (Carte carte){
        cartes.add(carte);
    }

    public void play(){

    }

    public void poserCarte(Carte carte){
        for (Carte c : cartes){
            if(c.getValue() == carte.getValue() && c.getColor() == carte.getColor()){
                Board.getInstance().pullCarte(c);
                break;
            }
        }
    }

    @Override
    public String toString (){
        StringBuffer str = new StringBuffer();
        str.append(name + ":");
        for(Carte c : cartes){
            str.append(c.toString() + " ");
        }
        return str.toString();
    }

}


