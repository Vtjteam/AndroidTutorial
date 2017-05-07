package vtj.ir.mcimtn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcMain;
    Button btnAll;
    ArrayList<ModelContact> contacts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        rcMain = (RecyclerView) findViewById(R.id.rcMain);

        btnAll = (Button) findViewById(R.id.btnAll);


        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncGetContact(MainActivity.this, new AsyncGetContact.delegate() {
                    @Override
                    public void endLoad(ArrayList<ModelContact> contactList) {
                        contacts.clear();
                        contacts.addAll(contactList);
                        AdapterContact adapter = new AdapterContact(contacts, new AdapterContact.ClickAction() {
                            @Override
                            public void click(ModelContact contact) {

                            }
                        });

                        rcMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rcMain.setAdapter(adapter);
                    }
                }).execute();

            }
        });
    }


}
