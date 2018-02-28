package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class listview1 extends AppCompatActivity {
    ListView lst;
    String copy;
    int i=0,j=0,k=0;
    Customlistview customlistview;


    //final ArrayList<String> menulists = new ArrayList<String>();



    final String []menunames=new String[200];
    final String []menudescription=new String[200];
    final String[]cost=new String[200];
    final Integer[]imgid={R.drawable.roti,R.drawable.vegbir};

    final ImageView[]img={};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview1);
        Intent in=getIntent();
        String currentrestaurantname=in.getStringExtra("restaurantslist1");
        setTitle(currentrestaurantname + "'s Menu");
        final Bitmap[] bit=new Bitmap[200];




        ParseQuery<ParseObject> query = ParseQuery.getQuery(currentrestaurantname);
        query.addAscendingOrder("Recepie");
        //Toast.makeText(this, "A username and password are required.", Toast.LENGTH_SHORT).show();

        //query.whereEqualTo("Name",currentrestaurantname);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("find in background", "retrived" + objects.size() + "objects");

                        if (objects.size() > 0) {
                            ///Log.i("going to enter bitmap1", "retrived" + objects.size() + "objects");
                            for (final ParseObject object : objects) {
                                //Log.i("going to enter  bitmap2", object.getString("Recepie"));


                                final ParseFile file=(ParseFile)object.get("Image");
                                //Log.i("gotparsefile bitmap", object.getString("Recepie"));

                                file.getDataInBackground(new GetDataCallback() {

                                    @Override


                                    public void done(byte[] data, ParseException e) {
                                        Log.i("getting data bitmap", object.getString("Recepie"));

                                        copy=object.getString("Recepie");
                                        while(true) {

                                            if (copy.equals(menunames[k])) {
                                                j = k;
                                                k=0;
                                                break;
                                            }
                                            else
                                            {
                                                k++;
                                            }
                                        }
                                        if(e==null&&data!=null){
                                            //Log.i("in if loop bitmap", object.getString("Recepie"));

                                             bit[j] = BitmapFactory.decodeByteArray(data,0,data.length);
                                            j++;

                                        }
                                        //MediaStore.Images.Media.insertImage(getContentResolver(), bit[0], "" , "");
                                        lst=(ListView)findViewById(R.id.lst);
                                       // Log.i("In bitmap"+j+"value", object.getString("Recepie"));
                                        lst.setAdapter(customlistview);


                                    }
                                });
                                menunames[i] = (object.getString("Recepie"));//adding objects to the array list
                                menudescription[i]=(object.getString("Description"));
                                Log.i("In textfields", object.getString("Recepie"));

                                cost[i]=(object.getString("cost"));


                                //Log.i("result got is", menunames[i]);
                                i++;

                        }
                            //this should be called in background function only...because background
                            //function works after all other work is done
                            //need to read that vid once again




                        }
                }
            }
        });
        customlistview=new Customlistview(this,menunames,menudescription,cost,imgid,bit,currentrestaurantname);




    }
}
