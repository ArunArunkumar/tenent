package com.example.jaya.tenant;

/**
 * Created by Sathish Raja on 18-09-2018.
 */

public class Contact {
    //private variables
    int _id;
    String _fname;
    String _address;
    String _location;
    String _type;
    String _advance;
    String _number;
    String _water;
    String _rent;
    byte[] _img;


    // Empty constructor
    public Contact() {

    }

    // constructor
    public Contact(int id, String fname, String address, String location, String type, String advance, String number, byte[] img) {
        this._id = id;
        this._fname = fname;
        this._address = address;
        this._location = location;
        this._type = type;
        this._advance = advance;
        this._number = number;
        this._img = img;

    }

    // constructor
    public Contact(String fname, String address, String location, String type, String advance, String number, byte[] img) {

        this._fname = fname;
        this._address = address;
        this._location = location;
        this._type = type;
        this._advance = advance;
        this._number = number;
        this._img = img;

    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting first name
    public String getFName() {
        return this._fname;
    }

    // setting first name
    public void setFName(String fname) {
        this._fname = fname;
    }

    public String get_address() {
        return this._address;
    }

    public void set_address(String address) {
        this._address = address;
    }

    public String get_location() {
        return this._location;
    }

    public void set_location(String location) {
        this._location = location;
    }

    public String get_type() {
        return this._type;
    }

    public void set_type(String type) {
        this._type = type;
    }

    public String get_advance() {
        return this._advance;
    }

    public void set_advance(String advance) {
        this._advance = advance;
    }

    public String get_number() {
        return this._number;
    }

    public void set_number(String number) {
        this._number = number;
    }


//    public String get_water() {
//        return this._water;
//    }
//
//    public void set_water(String water) {
//        this._water = water;
//    }

//    public String get_rent() {
//        return this._rent;
//    }
//
//    public void set_rent(String rent)
//
//    {
//        this._rent = rent;
//    }

    //getting profile pic
    public byte[] getImage() {
        return this._img;
    }

    //setting profile pic

    public void setImage(byte[] b) {
        this._img = b;
    }

}




