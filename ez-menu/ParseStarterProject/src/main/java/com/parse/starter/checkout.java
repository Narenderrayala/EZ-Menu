package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class checkout extends AppCompatActivity {
    String user;
    double value=0.1;
    String []list=new String[200];
    String []costlist=new String[200];
    int i=0,j=0;
    TextView t1,t2,t3,t4;
    String demo="";
    String demo2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        user= ParseUser.getCurrentUser().getUsername();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("user1");
        query.whereEqualTo("userid",user);
        t1=(TextView)findViewById(R.id.textView11);
        t2=(TextView)findViewById(R.id.textView6);
        t3=(TextView)findViewById(R.id.textView7);
        t4=(TextView)findViewById(R.id.textView8);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    //

                    if (objects.size() > 0) {
                        for (final ParseObject object : objects) {
                            list[i]=object.getString("order1");
                            costlist[i]=object.getString("cost");
                            Log.i("find in background", list[i]);
                            demo=demo + list[i] + "\n";
                            demo2=demo2+costlist[i]+"$"+ "\n";
                            t1.setText(demo);
                            t2.setText(demo2);

                            i++;


                        }
                        for(j=0;j<i;j++){
                             value=value+Double.parseDouble(costlist[j]);
                        }
                        //float value=Float.parseFloat(costlist[0]);
                        Log.i("find in background", String.valueOf(value-0.1));

                        t4.setText(String.valueOf(value-0.1)+ "$");


                    }
                }
            }
        });

        Intent i=new Intent(checkout.this,example.class);
        startActivity(i);
    }



}
