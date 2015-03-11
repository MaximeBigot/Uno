package uno.iut.fr.uno.modele.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.UUID;

import uno.iut.fr.uno.MainActivity;
import uno.iut.fr.uno.Test;
import uno.iut.fr.uno.modele.Board;
import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 10/03/2015.
 */
public class ConnectClientThread extends Thread{
    public static final String BT_SERVER_UUID_INSECURE = "8ce255c0-200a-11e0-ac64-0800200c9a66";
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private final MainActivity activity;

    public ConnectClientThread(BluetoothDevice device, MainActivity activity) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        this.activity = activity;
        BluetoothSocket tmp = null;
        mmDevice = device;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(BT_SERVER_UUID_INSECURE));
        } catch (IOException e) { }
        mmSocket = tmp;
    }

    public void run() {
        // Cancel discovery because it will slow down the connection
        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        // Do work to manage the connection (in a separate thread)
        Log.i("Reception", "Effectuée");
        try {
            /*byte[] buffer = new byte[1024];
            int bytes;

            bytes = mmSocket.getInputStream().read(buffer);
            String readMessage = new String(buffer, 0, bytes);*/
            ObjectInputStream ois = new ObjectInputStream(mmSocket.getInputStream());
            Carte carte = (Carte)ois.readObject();
            Intent intent = new Intent(activity, Test.class);
            intent.putExtra("carte", carte);
            activity.startActivity(intent);
        }catch (Exception e){
            Log.i("Reception", "Erreur !");
        }
    }

    /** Will cancel an in-progress connection, and close the socket */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
