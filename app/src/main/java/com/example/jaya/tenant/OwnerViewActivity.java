package com.example.jaya.tenant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerViewActivity extends AppCompatActivity {

    Button con,rej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view);

        con = (Button) findViewById(R.id.confirmmsg);
        rej = (Button) findViewById(R.id.rejectmsg);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_MAIN);

                intent.addCategory(Intent.CATEGORY_DEFAULT);

                intent.setType("vnd.android-dir/mms-sms");

                startActivity(intent);


            }
        });



        rej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nn = new Intent(OwnerViewActivity.this,LoginPage.class);
                startActivity(nn);


            }
        });


    }
}
