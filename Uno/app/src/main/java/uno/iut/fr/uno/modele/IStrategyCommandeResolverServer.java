package uno.iut.fr.uno.modele;

import android.bluetooth.BluetoothSocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 12/03/2015.
 */
public abstract class IStrategyCommandeResolverServer {
    private String resolver = this.getClass().getSimpleName();
    private static HashMap<String, IStrategyCommandeResolverServer>commandes = new HashMap<>();
    protected static IStrategyCommandeResolverServer ourInstance;

    public static IStrategyCommandeResolverServer getInstance(String commande){
        return commandes.get(commande);
    }

    private static HashMap<BluetoothSocket, Player>players = new HashMap<>();
    public static void addPlayer(BluetoothSocket socket, Player player){
        players.put(socket, player);
    }
    public static ArrayList<Player> getPlayers(){
        return new ArrayList<>(players.values());
    }
    public static void delPlayer(BluetoothSocket socket, Player player){
        players.remove(socket);
    }
    public static Player getPlayer(BluetoothSocket socket){
        return players.get(socket);
    }

    public static void addCommande(IStrategyCommandeResolverServer commande){
        commandes.put(commande.getResolver(), commande);
    }

    public static void delCommande(IStrategyCommandeResolverServer commande){
        commandes.remove(commande.getResolver());
    }

    public abstract void executeCommande (Player player, Carte carte);

    public String getResolver(){
        return resolver;
    }
}
