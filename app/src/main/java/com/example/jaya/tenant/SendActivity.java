package com.example.jaya.tenant;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {

    TextView one, two, three, four, five;
    String name, addrs, loca, tpy, adv;
    Button bt;

    String content = "I like your House and place. So, need to more information about your House";
    SQLiteDatabase mDatabase, meddb;
    public static final String DATABASE_NAMEAA = "mymarkstoredb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        mDatabase = openOrCreateDatabase(DATABASE_NAMEAA, MODE_PRIVATE, null);

        one = (TextView) findViewById(R.id.oneview);
        two = (TextView) findViewById(R.id.twoview);
        three = (TextView) findViewById(R.id.threeview);
        four = (TextView) findViewById(R.id.fourview);
        five = (TextView) findViewById(R.id.fiveview);
        bt = (Button) findViewById(R.id.msgsend);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        name = b.getString("name");
        addrs = b.getString("address");
        loca = b.getString("location");
        tpy = b.getString("types");
        adv = b.getString("advance");

        one.setText(name);
        two.setText(addrs);
        three.setText(loca);
        four.setText(tpy);
        five.setText(adv);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(addrs, null, content, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent...", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(SendActivity.this, LoginPage.class);
                in.putExtra("login", "owner");
                startActivity(in);

                createmarktable();






            }
        });


    }

    private void createmarktable() {

    }
}
