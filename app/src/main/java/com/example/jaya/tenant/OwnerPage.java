package com.example.jaya.tenant;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class OwnerPage extends Activity implements SearchView.OnQueryTextListener {
    ArrayAdapter<String> adapter;
    Toolbar mToolbar;

    private EditText fname, addr, loc, adv, wat, rent;
    private ImageView pic;
    private Databasehandler db;
    private String f_name, address, loction, typ, water, rents;
    private ListView lv;
    private dataAdapter data;
    private Contact dataModel;
    private Bitmap bp;
    private byte[] photo;
    String cou[] = {"1", "2", "3"};
    ImageView img;
    Spinner sp, bed, bth, hall;
    Button save, display, logout;
    String type[] = {"Appartment", "Mansion", "House", "Office"};
    String input, name;
    LinearLayout one, two;
    String spinselect;
    EditText searchView;
    private Toolbar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_page);

        one = (LinearLayout) findViewById(R.id.firstll);
        two = (LinearLayout) findViewById(R.id.secondll);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        input = b.getString("login");

        if (input.equals("tenent")) {
            two.setVisibility(View.VISIBLE);
            one.setVisibility(View.GONE);
        } else {
            one.setVisibility(View.VISIBLE);
            two.setVisibility(View.GONE);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        sp = (Spinner) findViewById(R.id.typespin);
        bed = (Spinner) findViewById(R.id.bedroomspin);
        bth = (Spinner) findViewById(R.id.bathroomspin);
        hall = (Spinner) findViewById(R.id.hallspin);
       // searchView = (EditText) findViewById(R.id.search);
//        setupSearchView();

        //Instantiate database handler
        db = new Databasehandler(this);

        lv = (ListView) findViewById(R.id.list1);
        pic = (ImageView) findViewById(R.id.pic);
        fname = (EditText) findViewById(R.id.txt1);
        addr = (EditText) findViewById(R.id.txt2);
        loc = (EditText) findViewById(R.id.txt3);
        adv = (EditText) findViewById(R.id.txt4);
        wat = (EditText) findViewById(R.id.txt5);
        logout = (Button) findViewById(R.id.logout);
        lv.setTextFilterEnabled(true);


//        searchView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                // When user changed the Text
//                OwnerPage.this.adapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                          int arg3) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//        });


        ArrayAdapter aa = new ArrayAdapter(OwnerPage.this, R.layout.support_simple_spinner_dropdown_item, type);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(aa);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinselect = sp.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter as = new ArrayAdapter(OwnerPage.this, R.layout.support_simple_spinner_dropdown_item, cou);
        as.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        bed.setAdapter(as);
        bth.setAdapter(as);
        hall.setAdapter(as);


        save = (Button) findViewById(R.id.save);
        display = (Button) findViewById(R.id.display);
        img = (ImageView) findViewById(R.id.pic);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(OwnerPage.this, LoginPage.class);
                startActivity(i);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fname.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                } else {
                    addContact();
                }
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowRecords();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();

            }
        });

    }

//    private void setupSearchView() {
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(this);
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setQueryHint("Search Here");
//    }


    public void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }

    public boolean onQueryTextChange(String newText) {

        if (TextUtils.isEmpty(newText)) {
            lv.clearTextFilter();
        } else {
            lv.setFilterText(newText);
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    Uri choosenImage = data.getData();

                    if (choosenImage != null) {

                        bp = decodeUri(choosenImage, 400);
                        pic.setImageBitmap(bp);
                    }
                }
        }
    }

    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

    }

    // function to get values from the Edittext and image
    private void getValues() {
        f_name = fname.getText().toString();
        address = addr.getText().toString();
        loction = loc.getText().toString();
        typ = adv.getText().toString();
        water = wat.getText().toString();
        photo = profileImage(bp);
    }

    //Insert data to the database
    private void addContact() {
        getValues();
        db.addContacts(new Contact(f_name, address, loction, spinselect, typ, water, photo));
        Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_LONG).show();
    }

    //Retrieve data from the database and set to the list view
    private void ShowRecords() {
        final ArrayList<Contact> contacts = new ArrayList<>(db.getAllContacts());
        data = new dataAdapter(this, contacts);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = contacts.get(position);

                Toast.makeText(getApplicationContext(), String.valueOf(dataModel.getID()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        android.support.v7.widget.SearchView mSearchView = (android.support.v7.widget.SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }
}