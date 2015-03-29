package uno.iut.fr.uno.modele.Bluetooth;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Maxime on 11/03/2015.
 */
public class ClientThread extends Thread{
    private BluetoothSocket serverSocket = null;

    public ClientThread (BluetoothSocket socket){
        serverSocket = socket;
    }

    public void run(){
        try {
            DataInputStream dis = new DataInputStream(serverSocket.getInputStream());
            while(serverSocket.isConnected()){
                String msgRead = dis.readUTF();
            }
        }catch (IOException ioe){
            Log.e("ClientThread : IOException : ", "Lecture sur la socket", ioe);
        }
    }
}
