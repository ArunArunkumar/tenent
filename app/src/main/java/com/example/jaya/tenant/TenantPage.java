package com.example.jaya.tenant;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TenantPage extends Activity {

    ListView lv;
    TextView tx;
    private Databasehandler db;
    private dataAdapter data;
    private Contact dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenant_page);


        lv = (ListView) findViewById(R.id.listtt);

        tx=(TextView) findViewById(R.id.datashowtxt);

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowRecords();

            }
        });



    }

    private void ShowRecords() {
        final ArrayList<Contact> contacts = new ArrayList<>(db.getAllContacts());
        data = new dataAdapter(this, contacts);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = contacts.get(position);

                Toast.makeText(getApplicationContext(), String.valueOf(dataModel.getID()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
