package com.example.jaya.tenant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerDashboard extends AppCompatActivity {


    Button reg,views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        reg = (Button) findViewById(R.id.ownerregbt);
        views = (Button) findViewById(R.id.ownerviewbt);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ownerlog = new Intent (OwnerDashboard.this, OwnerPage.class);
ownerlog.putExtra("login","owner");
                startActivity(ownerlog);
            }
        });

        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ownerlog = new Intent (OwnerDashboard.this, OwnerViewActivity.class);
                startActivity(ownerlog);
            }
        });

    }
}
