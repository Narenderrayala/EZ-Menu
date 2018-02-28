package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Restaurantslist extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.details,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.details )
        {

            Intent intent=new Intent(Restaurantslist.this,updateuserdetails.class);
            //intent.putExtra("currentrestaurant",currentrestaurantname);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantslist);
        final ArrayList<String>restaurantslist=new ArrayList<String>();// a new array list with restaurant list is created
        final ListView restaurantlist=(ListView)findViewById(R.id.restaurantlist);//a restaurant listview created in xml is initiated
        final ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,restaurantslist);//a array adapter is created
        restaurantlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if((ParseUser.getCurrentUser().getUsername()).equals("admin"))
                {
                    Intent intent=new Intent(getApplicationContext(), Adminview.class);
                    intent.putExtra("restaurantslist", restaurantslist.get(position));
                    startActivity(intent);
                }
                else {
                    Intent in = new Intent(getApplicationContext(), listview1.class);
                    in.putExtra("restaurantslist1", restaurantslist.get(position));
                    startActivity(in);
                }
            }
        });


        ParseQuery<ParseObject> query=ParseQuery.getQuery("Restaurantsnearme");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    Log.i("find in background","retrived" +objects.size()+"objects");
                    if(objects.size()>0){
                        for(ParseObject object :objects){
                            restaurantslist.add(object.getString("Name"));//adding objects to the array list
                           // restaurantslist.add(object.getString("Recipie1"));

                            //Log.i("result retrived", object.getString("Name"));
                            //Log.i("result retrived", object.getString("Recipie1"));

                        }
                        restaurantlist.setAdapter(arrayAdapter);// adding to the list view
                    }
                }
            }
        });

    }
}
