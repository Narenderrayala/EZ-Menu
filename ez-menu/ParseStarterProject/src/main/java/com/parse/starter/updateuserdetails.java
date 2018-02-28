package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class updateuserdetails extends AppCompatActivity {
    EditText e[]=new EditText[20];
    Button b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateuserdetails);
        e[0]=(EditText)findViewById(R.id.editText14);
        e[1]=(EditText)findViewById(R.id.editText13);
        e[2]=(EditText)findViewById(R.id.editText12);
        e[3]=(EditText)findViewById(R.id.editText11);
        e[4]=(EditText)findViewById(R.id.editText10);
        e[5]=(EditText)findViewById(R.id.editText9);
        e[6]=(EditText)findViewById(R.id.editText8);
        e[7]=(EditText)findViewById(R.id.editText7);
        e[8]=(EditText)findViewById(R.id.editText15);
        e[9]=(EditText)findViewById(R.id.editText16);
        e[10]=(EditText)findViewById(R.id.editText17);
        e[11]=(EditText)findViewById(R.id.editText18);

        b1=(Button)findViewById(R.id.button8);
        t1=(TextView)findViewById(R.id.textView20);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject object=new ParseObject("UserDetails");
                object.put("Name",e[0].getText().toString());
                object.put("Email",e[1].getText().toString());
                object.put("Phone",e[2].getText().toString());
                object.put("Address1",e[3].getText().toString());
                object.put("Address2",e[4].getText().toString());
                object.put("City",e[5].getText().toString());
                object.put("State",e[6].getText().toString());
                object.put("Zipcode",e[7].getText().toString());
                object.put("Lastname",e[11].getText().toString());
                object.put("Cardname",e[8].getText().toString());
                object.put("Cardno",e[9].getText().toString());
                object.put("cvv",e[10].getText().toString());
                object.put("userid", ParseUser.getCurrentUser().getUsername());
                object.saveInBackground();
                Toast.makeText(updateuserdetails.this, "Added to the database", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(updateuserdetails.this, Restaurantslist.class);
                startActivity(in);


            }
        });







    }
}
