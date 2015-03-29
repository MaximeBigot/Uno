package uno.iut.fr.uno.modele.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

import uno.iut.fr.uno.MainActivity;
import uno.iut.fr.uno.R;
import uno.iut.fr.uno.Test;
import uno.iut.fr.uno.modele.Board;
import uno.iut.fr.uno.modele.Color;
import uno.iut.fr.uno.modele.IStrategyCommandeResolverServer;
import uno.iut.fr.uno.modele.Player;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 10/03/2015.
 */
public class AcceptServerThread extends Thread{
    public static final String BT_SERVER_UUID_INSECURE = "8ce255c0-200a-11e0-ac64-0800200c9a66";
    private final BluetoothServerSocket mServerSocket;
    private final MainActivity activity;

    public AcceptServerThread (String name, BluetoothAdapter mAdapter, MainActivity activity){
        BluetoothServerSocket tmp = null;
        this.activity = activity;
        try{
            tmp = mAdapter.listenUsingRfcommWithServiceRecord(name, UUID.fromString(BT_SERVER_UUID_INSECURE));
        }catch (IOException ioe){Log.e("AcceptServerThread : IOException : ", "Instanciation socket", ioe);}
        mServerSocket = tmp;
    }

    public void run (){
        BluetoothSocket socket = null;
        while (true){
            try{
                socket = mServerSocket.accept();
                ServerThread thread = new ServerThread(socket);
                Player player = new Player(socket.toString());
                IStrategyCommandeResolverServer.addPlayer(socket, player);
                player.setThread(thread);
                thread.start();
                if(socket != null){
                    mServerSocket.close();
                    break;
                }
            }catch (IOException ioe){
                Log.e("AcceptServerThread : IOException : ", "Accept socket", ioe);
                break;
            }
        }
    }
}
