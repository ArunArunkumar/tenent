package com.example.jaya.tenant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int FIRSTNAME_COLUMN = 1;
    public static final int LASTNAME_COLUMN = 1;
    public static final int USERNAME_COLUMN = 1;
    public static final int EMAIL_COLUMN = 1;
    public static final int PASSWORD_COLUMN = 1;
    public static final int CONFIRMPASSWORD_COLUMN = 1;
    public static final int GENDER_COLUMN = 1;
    public static final int DOB_COLUMN = 1;
    public static final int ADDR_COLUMN = 1;
    public static final int PHONENO_COLUMN = 1;
    public static final int AADHAARNO_COLUMN = 1;
    public static final int MENTION_COLUMN = 1;
    public static final int ADDRESS_COLUMN = 1;
    public static final int MOBILENO_COLUMN = 1;
    public static final int RENT_COLUMN = 1;

    // TODO: Create public field for each column in your table.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ,
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "LOGINOWNER" +
            "( " + "S_FIRSTNAME  text,S_LASTNAME  text,S_USERNAME  text, S_EMAIL text, S_PASSWORD text, S_CONFIRMPASSWORD text, S_GENDER text, S_DOB text,  S_ADDR text,S_PHONENO text,S_AADHAARNO text ,S_MENTION text); ";

    static final String DATABASE_CREATEE = "create table " + "LOGINTENANT" +
            "( " + "S_FIRSTNAMEE  text,S_LASTNAMEE  text,S_USERNAMEE  text, S_EMAILL text, S_PASSWORDD text, S_CONFIRMPASSWORDD text, S_GENDERR text, S_DOBB text,  S_ADDRR text,S_PHONENOO text,S_AADHAARNOO text ,S_MENTIONN text); ";

    static final String DATABASE_IMAGE = "create table " + "TABLEIMAGE" + "("+ "KEY_ID +  INTEGER PRIMARY KEY, + KEY_NAME +  TEXT,+ KEY_IMAGE +  BLOB + )";


    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public  void insertEntryowner(String firstname, String lastname, String username, String email, String password, String confirmpassword,String gender, String dob, String addr, String phoneno, String aadhaarno,String mention ) {

        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("S_FIRSTNAME", firstname);
        newValues.put("S_LASTNAME", lastname);
        newValues.put("S_USERNAME", username);
        newValues.put("S_EMAIL", email);
        newValues.put("S_PASSWORD", password);
        newValues.put("S_CONFIRMPASSWORD", confirmpassword);
        newValues.put("S_GENDER", gender);
        newValues.put("S_DOB", dob);
        newValues.put("S_ADDR", addr);
        newValues.put("S_PHONENO", phoneno);
        newValues.put("S_AADHAARNO", aadhaarno);
        newValues.put("S_MENTION", mention);

        // Insert the row into your table
        db.insert("LOGINOWNER", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public  void insertEntrytenent(String firstname, String lastname, String username, String email, String password, String confirmpassword,String gender, String dob, String addr, String phoneno, String aadhaarno,String mention ) {

        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("S_FIRSTNAMEE", firstname);
        newValues.put("S_LASTNAMEE", lastname);
        newValues.put("S_USERNAMEE", username);
        newValues.put("S_EMAILL", email);
        newValues.put("S_PASSWORDD", password);
        newValues.put("S_CONFIRMPASSWORDD", confirmpassword);
        newValues.put("S_GENDERR", gender);
        newValues.put("S_DOBB", dob);
        newValues.put("S_ADDRR", addr);
        newValues.put("S_PHONENOO", phoneno);
        newValues.put("S_AADHAARNOO", aadhaarno);
        newValues.put("S_MENTIONN", mention);

        // Imnsert the row into your table
        db.insert("LOGINTENANT", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String name) {
        //String id=String.valueOf(ID);
        String where = "S_FIRSTNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{name});
        // Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String username) {
        Cursor cursor = db.query("LOGINOWNER", null, " S_USERNAME=?", new String[]{username}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("S_PASSWORD"));

        cursor.close();
        return password;
    }
    public String getSinlgeEntryy(String username) {
        Cursor cursor = db.query("LOGINTENANT", null, " S_USERNAMEE=?", new String[]{username}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("S_PASSWORDD"));

        cursor.close();
        return password;
    }


    public String getSingleEntry1(String mobile) {
        Cursor cursor = db.query("LOGIN", null, " S_PHONENO=?", new String[]{mobile}, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex("S_NAME"));
        cursor.close();
        return name;
    }

    public String getSingleEntry2(String mobile) {
        Cursor c = db.query("LOGIN", null, " S_PHONENO=?", new String[]{mobile}, null, null, null);
        c.moveToFirst();
        String email = c.getString(c.getColumnIndex("S_EMAIL"));
        c.close();
        return email;
    }

    public String getSingleEntry3(String mobile) {
        Cursor c = db.query("LOGIN", null, " S_PHONENO=?", new String[]{mobile}, null, null, null);
        c.moveToFirst();
        String addr = c.getString(c.getColumnIndex("S_ADDR"));
        c.close();
        return addr;
    }


    public void updateEntry(String name, String email, String mobile, String addr, String password) {
        // Define the updated row content.
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("S_NAME", name);
        newValues.put("S_EMAIL", email);
        newValues.put("S_MOBILE", mobile);
        newValues.put("S_ADDR", addr);
        //newValues.put("S_YEAR",year);
        newValues.put("S_PASSWORD", password);


        String where = "S_NAME = ?";
        db.update("LOGIN", newValues, where, new String[]{name});
    }

    public String getSingleEntry4(String did) {
        // TODO Auto-generated method stub
        return null;
    }
}

