package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Adminview extends AppCompatActivity {
    TextView t1;
    String user;
    int i=0;
    String []list=new String[200];
    String []costlist=new String[200];
    String demo="";
    String demo2="";
    String currentrestaurantname;
    Button b1,b2;
    EditText e1,e2;


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.share )
        {

            Intent intent=new Intent(Adminview.this,Adminview2.class);
            intent.putExtra("currentrestaurant",currentrestaurantname);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.CustomerReviews )
        {

            Intent intent=new Intent(Adminview.this,AdminReview.class);
            intent.putExtra("currentrestaurant",currentrestaurantname);
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);
        Intent in = getIntent();
        b1=(Button)findViewById(R.id.button2) ;
        b2=(Button)findViewById(R.id.button10) ;
        //b2=(Button)findViewById(R.id.button5) ;
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText19);
        user = ParseUser.getCurrentUser().getUsername();
        currentrestaurantname = in.getStringExtra("restaurantslist");
        t1 = (TextView) findViewById(R.id.textView9);
        query();


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject object=new ParseObject(e2.getText().toString());
                object.saveInBackground();
                Toast.makeText(Adminview.this, "A New Restaurant is added", Toast.LENGTH_SHORT).show();
                ParseObject object1=new ParseObject("Restaurantsnearme");
                object1.put("Name",e2.getText().toString());
                object1.saveInBackground();


            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String s=e1.getText().toString();
                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("user1");
                query1.whereEqualTo("userid",e1.getText().toString());

                query1.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            Log.i("find in background", "retrived" + objects.size() + "objects");

                            if (objects.size() > 0) {
                                for (final ParseObject object : objects) {

                                    object.deleteEventually();
                                    object.saveInBackground();


                                }

                            }
                        }
                    }
                });

                query();
            }
        });





    }

        void query() {
            demo="";
            i=0;
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("user1");
        query.whereEqualTo("restaurant", currentrestaurantname);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("find in background", "retrived" + objects.size() + "objects");

                    if (objects.size() > 0) {
                        for (final ParseObject object : objects) {


                            list[i] = object.getString("userid");
                            costlist[i] = object.getString("order1");
                            Log.i("find in background", list[i]);
                            demo = demo + list[i] + " Ordered " + costlist[i] + "\n";




                            i++;

                        }
                        t1.setText(demo + "\n\n\n" + "Total number of orders for " + currentrestaurantname + "  are  " + objects.size());
                    }
                }
            }
        });
        }

    }

