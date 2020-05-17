package com.example.jaya.tenant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class dataAdapter extends ArrayAdapter<Contact> {

    Context context;
    ArrayList<Contact> mcontact;


    public dataAdapter(Context context, ArrayList<Contact> contact) {
        super(context, R.layout.list, contact);
        this.context = context;
        this.mcontact = contact;
    }

    public class Holder {
        Button send;
        TextView rent;
        TextView number;
        TextView advance;
        TextView type;
        TextView location;
        TextView address;
        TextView nameFV;
        ImageView pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Contact data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        final Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtViewer);
            viewHolder.address = (TextView) convertView.findViewById(R.id.txtaddress);
            viewHolder.location = (TextView) convertView.findViewById(R.id.txtlocation);
            viewHolder.type = (TextView) convertView.findViewById(R.id.txttype);
            viewHolder.advance = (TextView) convertView.findViewById(R.id.txtadvance);
            viewHolder.number = (TextView) convertView.findViewById(R.id.txtwater);
            viewHolder.rent = (TextView) convertView.findViewById(R.id.txtrent);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgView);
            viewHolder.send = (Button) convertView.findViewById(R.id.sendbt);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }


        viewHolder.nameFV.setText("Name: " + data.getFName());
        viewHolder.address.setText( data.get_address());
        viewHolder.location.setText("Address & Location : " + data.get_location());
        viewHolder.type.setText("Type : " + data.get_type());
        viewHolder.advance.setText("Advance :" + data.get_advance());
        // viewHolder.rent.setText("Rent : " + data.get_rent());
        viewHolder.pic.setImageBitmap(convertToBitmap(data.getImage()));

        viewHolder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = viewHolder.nameFV.getText().toString();
                String addr = viewHolder.address.getText().toString();
                String loc = viewHolder.location.getText().toString();
                String typs = viewHolder.type.getText().toString();
                String adva = viewHolder.advance.getText().toString();

                Intent in= new Intent(context,SendActivity.class);
                in.putExtra("name",name);
                in.putExtra("address",addr);
                in.putExtra("location",loc);
                in.putExtra("types",typs);
                in.putExtra("advance",adva);

                context.startActivity(in);



            }
        });






        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(byte[] b) {

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }

}