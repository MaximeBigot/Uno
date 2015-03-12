package uno.iut.fr.uno.modele.iStrategyCommandeResolverServer;

import uno.iut.fr.uno.modele.Board;
import uno.iut.fr.uno.modele.IStrategyCommandeResolverServer;
import uno.iut.fr.uno.modele.Player;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 12/03/2015.
 */
public class PiocherCarte extends IStrategyCommandeResolverServer {
    private PiocherCarte(){}

    public static IStrategyCommandeResolverServer getInstance (){
        return ourInstance;
    }

    @Override
    public void executeCommande(Player player, Carte carte) {
        Board.getInstance().piocherCarte(player);
    }
}
