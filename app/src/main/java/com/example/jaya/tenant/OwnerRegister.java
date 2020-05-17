package com.example.jaya.tenant;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OwnerRegister extends AppCompatActivity {

    EditText frstname, lstname, usrname, email, passwrd, cnfmpasswrd, dob, addrss, phno,aadharno,mention;
    Button reg;
    Calendar myCalender;
    Spinner gender;
    String str[] = {"Select Gender", "Male", "Female"};
    String genderlist;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_register);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        frstname = (EditText) findViewById(R.id.firstnameownreg);
        lstname = (EditText) findViewById(R.id.lastnameownreg);
        usrname = (EditText) findViewById(R.id.usernameownreg);
        email = (EditText) findViewById(R.id.emailownreg);
        passwrd = (EditText) findViewById(R.id.passwordownreg);
        cnfmpasswrd = (EditText) findViewById(R.id.confirmpasswordownreg);
        dob = (EditText) findViewById(R.id.dobownreg);
        addrss = (EditText) findViewById(R.id.addressownreg);
        phno = (EditText) findViewById(R.id.phnoownreg);
        aadharno=(EditText)findViewById(R.id.aadhaarnoownreg);
        mention=(EditText)findViewById(R.id.mentionownreg);

        gender = (Spinner) findViewById(R.id.genderownreg);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderlist = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        ArrayAdapter arrayAdapter = new ArrayAdapter(OwnerRegister.this, R.layout.support_simple_spinner_dropdown_item, str);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        gender.setAdapter(arrayAdapter);

        reg=(Button)findViewById(R.id.registerbtnownreg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a = frstname.getText().toString();
                String b = lstname.getText().toString();
                String c = usrname.getText().toString();
                String d = email.getText().toString();
                String e = passwrd.getText().toString();
                String f = cnfmpasswrd.getText().toString();
                String g = dob.getText().toString();
                String h = addrss.getText().toString();
                String i = phno.getText().toString();
                String j = aadharno.getText().toString();
                String k = mention.getText().toString();
                if ((a.equals("")) || (b.equals("")) || (c.equals("")) || (d.equals("")) || (g.equals("")) || (h.equals("")) || (i.equals(""))|| (j.equals(""))|| (k.equals(""))) {
                    Toast.makeText(getApplicationContext(), "please enter values", Toast.LENGTH_SHORT).show();
                } else if (genderlist.equals("Select Gender")) {

                    Toast.makeText(getApplicationContext(), "Please Choose Valid Gender", Toast.LENGTH_SHORT).show();


                } else if (!e.equals(f)) {
                    Toast.makeText(getApplicationContext(), "password and Confirm Password Must Same", Toast.LENGTH_SHORT).show();
                } else {
                    loginDataBaseAdapter.insertEntryowner(a,b,c,d,e,f,genderlist,g,h,i,j,k);

                    Intent nxt = new Intent(OwnerRegister.this, LoginPage.class);
                    startActivity(nxt);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();


                }

            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OwnerRegister.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }
}


