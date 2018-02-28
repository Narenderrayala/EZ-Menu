package com.parse.starter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CustomerReview extends AppCompatActivity {

    String currentrestaurant;
    EditText e1, e2, e3, e4;
    Button b1,b2;

    public void getPhoto() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);//
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       // currentrestaurant = ParseUser.getCurrentUser().toString();
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);
        Intent in=getIntent();
       currentrestaurant= in.getStringExtra("currentrestaurant");
        e1 = (EditText) findViewById(R.id.editText6);
        b1 = (Button) findViewById(R.id.button7);
        b2 = (Button) findViewById(R.id.button9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                } else {
                    getPhoto();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOutInBackground();
                Intent in=new Intent(CustomerReview.this,ImageSlider.class);
                startActivity(in);

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                //ImageView imageView=(ImageView)findViewById(R.id.imageView3);
                //imageView.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                ParseFile file = new ParseFile("newimg.png", byteArray);
                ParseObject object = new ParseObject("UserReview");
                object.put("Image", file);
                object.put("Review", e1.getText().toString());
                object.put("userid", ParseUser.getCurrentUser().getUsername());
                object.put("Restaurant",currentrestaurant);
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(CustomerReview.this, "Imageshared", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CustomerReview.this, "ImageCouldnot be shared", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
