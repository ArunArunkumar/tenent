package com.example.jaya.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tenent  {

    int id;
    String address, phno, rent;
    Tenent(String address,String phno, String rent){

        this.address=address;
        this.phno=phno;
        this.rent=rent;

    }

    public String getAddress()
    {
        return address;
    }
    public String getPhno()
    {
        return phno;
    }
    public String getRent()
    {
        return rent;
    }



}
