package ir.vtj.lastmusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ActivityLastMusic extends AppCompatActivity {
    private RecyclerView rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rc = (RecyclerView)findViewById(R.id.rc);

        new GetMusic(getApplicationContext(), new GetMusic.delegate() {
            @Override
            public void getMdata(ArrayList<songArrayList> cursor) {
                setRecyclerMusic(cursor);
            }
        }).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_last_music, menu);
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

    private void setRecyclerMusic(ArrayList<songArrayList> suites) {
            AdapterMusic adapterSuiteExpense = new AdapterMusic(suites,getApplicationContext());
            rc.setHasFixedSize(false);
            GridLayoutManager llm = new GridLayoutManager(getApplicationContext(), 2);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rc.setLayoutManager(llm);
            rc.setAdapter(adapterSuiteExpense);
    }
}
