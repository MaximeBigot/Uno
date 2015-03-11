package uno.iut.fr.uno.modele;

import java.util.ArrayList;

import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class Player {
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


