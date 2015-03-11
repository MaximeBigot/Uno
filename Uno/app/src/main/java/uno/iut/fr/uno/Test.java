package uno.iut.fr.uno;

import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Picture;
import android.graphics.drawable.NinePatchDrawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

import uno.iut.fr.uno.modele.carte.Carte;

/**
 * Created by Max on 10/03/2015.
 */
public class Test extends ActionBarActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_image);
        tv = (TextView)findViewById(R.id.textView);
        Bundle extra = getIntent().getExtras();
        tv.setText(extra.getSerializable("carte").toString());
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

    public void setText (Carte carte){
        tv.setText(carte.toString());
    }
}
