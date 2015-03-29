package uno.iut.fr.uno.modele.Bluetooth;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import uno.iut.fr.uno.modele.IStrategyCommandeResolverServer;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Maxime on 11/03/2015.
 */
public class ServerThread extends Thread{

    private BluetoothSocket clientSocket = null;

    public ServerThread (BluetoothSocket socket){
        clientSocket = socket;
    }

    public void run(){
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream((clientSocket.getInputStream()));
            while(clientSocket.isConnected()){
                String msgRead = ois.readUTF();
                Carte carte = null;
                if(msgRead.equals("PoserCarte")){
                    carte =(Carte)ois.readObject();
                }
                IStrategyCommandeResolverServer strategy = IStrategyCommandeResolverServer.getInstance(msgRead);
                if (strategy != null){
                    strategy.executeCommande(IStrategyCommandeResolverServer.getPlayer(clientSocket), carte);
                }
            }

        }catch (IOException ioe){Log.e("ServerThread : IOException : ", "Lecture sur la socket", ioe);
        }catch (ClassNotFoundException cnfe){Log.e("ServerThread : CassNotFoundException : ", "Lecture sur la socket", cnfe);
        }finally {
            try {
                ois.close();
            } catch (IOException ioe) {Log.e("ServerThread : IOException : ", "Fermeture lecture sur la socket", ioe);}
        }
    }
}
