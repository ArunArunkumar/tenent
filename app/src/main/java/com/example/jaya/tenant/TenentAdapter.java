package com.example.jaya.tenant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TenentAdapter extends ArrayAdapter {
    Context mCtx;
    int listLayoutRes;
    List<Tenent> employeeList;
    SQLiteDatabase mDatabase;

    public TenentAdapter(@NonNull Context context, int resource,List<Tenent> tenentViews, SQLiteDatabase sqLiteDatabase) {
        super(context, resource,tenentViews);

        this.mCtx=context;
        this.listLayoutRes=resource;
        this.employeeList=tenentViews;
        this.mDatabase=sqLiteDatabase;

    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);
        final Tenent employee = employeeList.get(position);

        final TextView textViewNameone = view.findViewById(R.id.owneradress);
        final TextView textViewNametwo = view.findViewById(R.id.ownerphno);
        final TextView textViewNamethree = view.findViewById(R.id.ownerrent);

        textViewNameone.setText(employee.getAddress());
        textViewNametwo.setText(employee.getPhno());
        textViewNamethree.setText(employee.getRent());


        return view;
    }





}
