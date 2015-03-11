package uno.iut.fr.uno.modele;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

import uno.iut.fr.uno.modele.carte.BlackCarteEvent;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 04/03/2015.
 */
public class Board implements Serializable{
    ArrayList<Carte>pile = Game.getGame();
    Stack<Carte> tas = new Stack<>();
    LinkedList<Player>players;

    private static Board ourInstance = new Board();
    public static Board getInstance(){return ourInstance;}

    private Board(){}

    public void setPlayers(ArrayList<Player>players){this.players = new LinkedList<>(players);}
    private void shuffle (){
        Collections.shuffle(pile);
    }

    public void giveCarte(){
        this.shuffle();
        for(Player p : players){
            for(int i=0 ; i<7 ; i++){
                p.addCarte(pile.get(1));
                pile.remove(1);
            }
        }
        tas.push(pile.remove(1));
    }

    public void play(){
        while(true){
            Player player = players.removeFirst();
            player.play();
            players.push(player);
        }
    }

    public boolean pullCarte (Carte carte){
        if(carte instanceof BlackCarteEvent){
            tas.push(carte);
            return true;
        }
        else if (carte.equals(tas.firstElement())){
            tas.push(carte);
            return true;
        }
        return false;
    }

    public void changeOrderPlayer(){
            Collections.reverse(players);
    }

    @Override
    public String toString (){
        StringBuffer str = new StringBuffer();
        for(Player p : players){
            str.append(p.toString() + " ");
        }
        return str.toString();
    }
}
