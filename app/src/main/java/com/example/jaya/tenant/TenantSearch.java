package com.example.jaya.tenant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TenantSearch extends AppCompatActivity {

    List<Tenent> employeeList;
    SQLiteDatabase mDatabase;
    ListView listViewEmployees;
    TenentAdapter adapter;
EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenant_search);

        listViewEmployees = (ListView) findViewById(R.id.list_itemtenantsearch);
        employeeList = new ArrayList<>();
        //opening the database
        //mDatabase = openOrCreateDatabase(OwnerPage.DATABASE_NAMEE, MODE_PRIVATE, null);
        //this method will display the employees in the list
        showEmployeesFromDatabase();

        edt = (EditText) findViewById(R.id.searchedt);





        edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                TenantSearch.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


    }

    private void showEmployeesFromDatabase() {

        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM Owner", null);
        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new Tenent(
                        cursorEmployees.getString(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2)
                       ));

            } while (cursorEmployees.moveToNext());
            cursorEmployees.close();
            //creting the adapter object
            adapter = new TenentAdapter(this, R.layout.activity_tenent_adapter, employeeList, mDatabase);
            //adding the adapter to listview
            listViewEmployees.setAdapter(adapter);
        }


    }
}
