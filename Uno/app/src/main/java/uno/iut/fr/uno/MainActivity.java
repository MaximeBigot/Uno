package uno.iut.fr.uno;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import uno.iut.fr.uno.modele.Bluetooth.AcceptServerThread;
import uno.iut.fr.uno.modele.Bluetooth.ConnectClientThread;
import uno.iut.fr.uno.modele.Board;
import uno.iut.fr.uno.modele.Game;
import uno.iut.fr.uno.modele.Player;
import uno.iut.fr.uno.modele.carte.Carte;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    private Button b1;
    public EditText t1;
    private ListView l1;
    private BluetoothAdapter ba;
    private ArrayAdapter mArrayAdapter;
    private ArrayList<BluetoothDevice>devices;
    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName());

                devices.add(device);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        t1 = (EditText)findViewById(R.id.editText);
        l1 = (ListView)findViewById(R.id.listView);

        ba = BluetoothAdapter.getDefaultAdapter();


        // Register the BroadcastReceiver
        mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        devices = new ArrayList<>();
        l1.setAdapter(mArrayAdapter);
        t1.setText("marche pas");
        l1.setOnItemClickListener(this);
        AcceptServerThread ast = new AcceptServerThread("Max",ba, this);
        ast.start();
    }

    @Override
    public  void onResume(){
        super.onResume();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop(){
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    public void onClickButtonRecherche (View view){
        //Verifier si le Bluetooth existe
        t1.setText("hello");
        if(ba == null){
            Toast.makeText(getApplicationContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show();
            t1.setText("Rien comprendre");
        }
        //Activer le bluetooth
        if (!ba.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on"
                    , Toast.LENGTH_LONG).show();
            t1.setText("block 2");
        }
        else{
            Toast.makeText(getApplicationContext(),"Already on",
                    Toast.LENGTH_LONG).show();
            t1.setText("block 3");
        }

        Set<BluetoothDevice> pairedDevices = ba.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
        /*Player p1 = new Player("george");
        Player p2 = new Player("charle");
        Player p3 = new Player("alband");
        Player p4 = new Player("Mark");

        ArrayList<Player>players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        Board.getInstance().setPlayers(players);
        Board.getInstance().giveCarte();
        t1.setText(Board.getInstance().toString());*/
    }

    public void onClickButton2 (View view){
        /*Board.getInstance().changeOrderPlayer();
        t1.setText(Board.getInstance().toString());*/
        //Mettre le télephone visible
        /*Intent getVisible = new Intent(BluetoothAdapter.
                ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);*/
        t1.setText(String.valueOf(mArrayAdapter.getCount()));
        Intent intent = new Intent(this, Test.class);
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
        ba.startDiscovery();
        //startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("On item click parent : ", parent.toString());
        Log.i("On item click parent : ", String.valueOf(parent.getCount()));
        Log.i("On item click parent : ", String.valueOf(position));
        Log.i("On item click parent : ", parent.getItemAtPosition(position).toString());
        Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        Log.i("Hello Device", "------------------------------");
        Log.i("Hello Device", devices.get(position).getName());
        Log.i("Hello Device", "------------------------------");
        try {
            ConnectClientThread cct = new ConnectClientThread(devices.get(position), this);
            cct.start();
        }catch (Exception e){
            Log.e("Connection : ", "Connection echec avec le divice");
        }
    }

    public void setText (Carte carte){
        t1.setText(carte.toString());
    }
}
