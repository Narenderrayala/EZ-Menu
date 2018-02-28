package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class AdminReview extends AppCompatActivity {
    Button b1;
    TextView t1,t2;
    ImageView im;
    String Review;
    String Userid;
    Bitmap bit;


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.adminreview,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.adminreview )
        {

            ParseUser.logOutInBackground();

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_review);
        b1=(Button)findViewById(R.id.button5);
        t1=(TextView)findViewById(R.id.textView18);
        t2=(TextView)findViewById(R.id.textView19);
        im=(ImageView)findViewById(R.id.imageView5);
        t1.setText(ParseUser.getCurrentUser().getUsername());
        Intent in=getIntent();
        final String currentrestaurant=in.getStringExtra("currentrestaurant");
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UserReview");
        query.whereEqualTo("Restaurant",currentrestaurant);
        query.orderByAscending("createdAt");


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("find in background", "retrived" + objects.size() + "objects");

                    if (objects.size() > 0) {
                        Log.i("going to enter bitmap1", "retrived" + objects.size() + "objects");
                        for (final ParseObject object : objects) {



                            final ParseFile file=(ParseFile)object.get("Image");
                            //file.whereEqualTo("Recepie",currentorder);

                           Userid=(object.getString("userid"));
                            Review=(object.getString("Review"));
                            Log.i("menudescriptionis", Review);



                            file.getDataInBackground(new GetDataCallback() {



                                @Override
                                public void done(byte[] data, ParseException e) {



                                    if(e==null&&data!=null){


                                            if(Userid.equals(object.getString("userid")))
                                            {
                                                bit = BitmapFactory.decodeByteArray(data,0,data.length);

                                            }


                                       // im=(ImageView)findViewById(R.id.imageView4);
                                        //j++;



                                    }
                                    im.setImageBitmap(bit);
                                    t1.setText( Userid);
                                    t2.setText(Review);
                                    MediaStore.Images.Media.insertImage(getContentResolver(), bit, "" , "");




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




    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(AdminReview.this,emotion.class);
            startActivity(i);
        }
    });


    }
}
