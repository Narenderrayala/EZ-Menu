package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class example extends AppCompatActivity {
    String user;
    double value=0.1;
    String []list=new String[200];
    String []costlist=new String[200];
    int i=0,j=0;
    TextView t1;
    String demo="";
    String demo2="";
    ListView ls;
    String currentrestaurant;
    Csview1 csview1;


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.review,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.review )
        {


        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);
        Intent in=getIntent();
        currentrestaurant=in.getStringExtra("restaurantslist1");


        user= ParseUser.getCurrentUser().getUsername();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("user1");
        query.whereEqualTo("userid",user);
        t1=(TextView)findViewById(R.id.textView15);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    //

                    if (objects.size() > 0) {
                        for (final ParseObject object : objects) {
                            list[i]=object.getString("order1");
                            costlist[i]=object.getString("cost");


                            i++;


                        }
                        for(j=0;j<i;j++){
                            value=value+Double.parseDouble(costlist[j]);
                        }
                        //float value=Float.parseFloat(costlist[0]);
                        Log.i("find in background", String.valueOf(value-0.1));
                        //String.format("%.2f", value-0.1);

                        t1.setText(String.format("%.2f",value-0.1)+ "$");







                    }
                    ls=(ListView)findViewById(R.id.ls1);
                    // Log.i("In bitmap"+j+"value", object.getString("Recepie"));
                    ls.setAdapter(csview1);
                }
            }
        });
        csview1=new Csview1(this,list,costlist,currentrestaurant);
    }



}

