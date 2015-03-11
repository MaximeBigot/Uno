package uno.iut.fr.uno.modele.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Max on 10/03/2015.
 */
public class AcceptServerThread extends Thread{
    public static final String BT_SERVER_UUID_INSECURE = "8ce255c0-200a-11e0-ac64-0800200c9a66";
    private final BluetoothServerSocket mServerSocket;

    public AcceptServerThread (String name, BluetoothAdapter mAdapter){
        BluetoothServerSocket tmp = null;
        try{
            tmp = mAdapter.listenUsingRfcommWithServiceRecord(name, UUID.fromString(BT_SERVER_UUID_INSECURE));
        }catch (IOException io){}
        mServerSocket = tmp;
    }

    public void run (){
        BluetoothSocket socket = null;
        while (true){
            try{
                socket = mServerSocket.accept();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeChars("hello");
                Log.i("Connection", "Effectuée");
                if(socket != null){
                    mServerSocket.close();
                    break;
                }
            }catch (IOException ioe){
                break;
            }
        }
    }
}
