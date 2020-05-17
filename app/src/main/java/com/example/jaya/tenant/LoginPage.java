package com.example.jaya.tenant;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class LoginPage extends AppCompatActivity {

    Button tenantlogin, ownerlogin, tenantsignup, ownersignup;
    EditText userid, password;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        tenantlogin = (Button) findViewById(R.id.tenantloginbtn);
        ownerlogin = (Button) findViewById(R.id.ownerloginbtn);
        tenantsignup = (Button) findViewById(R.id.tenantsignupbtn);
        ownersignup = (Button) findViewById(R.id.ownersignupbtn);

        userid = (EditText) findViewById(R.id.usernameloginpage);
        password =(EditText) findViewById(R.id.passwordloginpage);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


    tenantlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String a =userid.getText().toString();
            String b =password.getText().toString();

            String ab = loginDataBaseAdapter.getSinlgeEntryy(a);

            if (b.equals(ab)) {
                Intent tenantlog = new Intent(LoginPage.this, OwnerPage.class);
                tenantlog.putExtra("login","tenent");
                startActivity(tenantlog);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"User Name or Password doesn't match",Toast.LENGTH_SHORT).show();
            }
        }
    });
    ownerlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String a = userid.getText().toString();
            String b = password.getText().toString();

            String ab = loginDataBaseAdapter.getSinlgeEntry(a);
            if (b.equals(ab)) {
                Intent ownerlog = new Intent (LoginPage.this, OwnerDashboard.class);
                ownerlog.putExtra("login","owner");
                startActivity(ownerlog);
            }else {
                Toast.makeText(getApplicationContext(),"User Name or Password doesn't match",Toast.LENGTH_SHORT).show();
            }
        }
    });
    tenantsignup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent tenantsgn=new Intent(LoginPage.this,TenantRegister.class);
            startActivity(tenantsgn);
        }
    });
    ownersignup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ownersgn=new Intent(LoginPage.this,OwnerRegister.class);
            startActivity(ownersgn);
        }
    });









    }
}

