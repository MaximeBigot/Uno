package uno.iut.fr.uno.modele.iStrategyCommandeResolverServer;

import uno.iut.fr.uno.modele.IStrategyCommandeResolverServer;
import uno.iut.fr.uno.modele.Player;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 12/03/2015.
 */
public class PoserCarte extends IStrategyCommandeResolverServer {
    private PoserCarte(){}

    public static IStrategyCommandeResolverServer getInstance (){
        return ourInstance;
    }
    @Override
    public void executeCommande(Player player, Carte carte) {
        player.poserCarte(carte);
    }
}
