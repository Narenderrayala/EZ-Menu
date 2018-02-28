package com.parse.starter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.jar.Manifest;

public class Adminview2 extends AppCompatActivity {
    String currentrestaurant;
    EditText e1,e2,e3,e4;
    Button b1;
    public void getPhoto() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);//
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getPhoto();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview2);
        Intent in=getIntent();
       currentrestaurant= in.getStringExtra("currentrestaurant");
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);
        e4=(EditText)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);

                }
                else
                {
                    getPhoto();
                }


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1  &&resultCode==RESULT_OK&&data!=null){
            Uri selectedImage=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                //ImageView imageView=(ImageView)findViewById(R.id.imageView3);
                //imageView.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[]byteArray=stream.toByteArray();
                ParseFile file=new ParseFile(e4.getText().toString()+".png",byteArray);
                ParseObject object=new ParseObject(currentrestaurant);
                object.put("Image",file);
                object.put("Recepie",e1.getText().toString());
                object.put("cost",e2.getText().toString());
                object.put("Description",e3.getText().toString());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                    if(e==null)
                    {
                        Toast.makeText(Adminview2.this, "Imageshared", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(Adminview2.this, "ImageCouldnot be shared", Toast.LENGTH_SHORT).show();
                    }
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
