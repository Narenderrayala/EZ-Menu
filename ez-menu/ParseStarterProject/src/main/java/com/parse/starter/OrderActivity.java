package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    String user;
    String cost,cost1;
    String menudescription;
    TextView t1,t2;
    ImageView im;
    Button b1,b2,b3;
   // Bitmap bit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent in=getIntent();

        //Bundle extras = getIntent().getExtras();
        //Bitmap bmp =  extras.getParcelable("imagebitmap");



      //  Bitmap b = BitmapFactory.decodeByteArray(
        //        getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);

        //im.setImageBitmap(b);

        final String currentrestaurant=in.getStringExtra("currentrestaurant");
        cost1=in.getStringExtra("cost");
        final String currentorder=in.getStringExtra("menulist");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        user=ParseUser.getCurrentUser().getUsername();
        t1=(TextView)findViewById(R.id.textView4);
        t2=(TextView)findViewById(R.id.textView5);
        t1.setText(currentrestaurant);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button3);
        b3=(Button)findViewById(R.id.button4);


        final ParseQuery<ParseObject> query = ParseQuery.getQuery(currentrestaurant);
        query.whereEqualTo("Recepie",currentorder);




        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("find in background", "retrived" + objects.size() + "objects");

                    if (objects.size() > 0) {
                        Log.i("going to enter bitmap1", "retrived" + objects.size() + "objects");
                        for (final ParseObject object : objects) {
                            Log.i("going to enter  bitmap2", object.getString("Recepie"));


                            final ParseFile file=(ParseFile)object.get("Image");
                            //file.whereEqualTo("Recepie",currentorder);
                            Log.i("gotparsefile bitmap", object.getString("Recepie"));

                            menudescription=(object.getString("Description"));
                            Log.i("menudescriptionis", menudescription);

                            cost=(object.getString("cost"));

                            Log.i("gotparsefile bitmap", object.getString("Description"));


                            file.getDataInBackground(new GetDataCallback() {


                                @Override


                                public void done(byte[] data, ParseException e) {
                                    Log.i("the menu is", object.getString("Recepie"));


                                    if(e==null&&data!=null){
                                        //Log.i("in if loop bitmap", object.getString("Recepie"));

                                        Bitmap bit = BitmapFactory.decodeByteArray(data,0,data.length);
                                        im=(ImageView)findViewById(R.id.imageView4);
                                        //j++;
                                        im.setImageBitmap(bit);
                                        t1.setText(menudescription + "\n");
                                        t2.setText(cost);


                                    }



                                }

                            });




                        }
                        //this should be called in background function only...because background
                        //function works after all other work is done
                        //need to read that vid once again

                    }
                }
            }
        });




        //Log.i("the menu is", cost);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button", "Clicked");
                Toast.makeText(OrderActivity.this, "Item Added to cart", Toast.LENGTH_SHORT).show();


                ParseObject storyActivity = new ParseObject("user1");
                storyActivity.put("order1",currentorder);
                storyActivity.put("userid",user);
                storyActivity.put("cost",cost1);
                storyActivity.put("restaurant",currentrestaurant);
                storyActivity.saveInBackground();


            }

            });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button3", "Clicked");
                Intent intent=new Intent(OrderActivity.this,example.class);
                intent.putExtra("restaurantslist1",currentrestaurant);
                startActivity(intent);

            }

        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button4", "Clicked");
                Intent intent=new Intent(OrderActivity.this,listview1.class);
                intent.putExtra("restaurantslist1",currentrestaurant);
                startActivity(intent);

            }

        });

        }



    }

