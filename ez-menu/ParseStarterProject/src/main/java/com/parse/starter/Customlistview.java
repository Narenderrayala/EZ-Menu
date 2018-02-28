package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * Created by naren on 26-09-2017.
 */

public class Customlistview extends ArrayAdapter<String> {
    private String[] menunames;
    private String[] menudescription;
    private String []Cost;
    private Integer[]imgid;
    private Bitmap bit[];
    private ImageView[]img={};
    private String currentrestaurant;
    private Activity context;

    public Customlistview(Activity context, String []menunames,String[] menudescription,String[] Cost,Integer[]imgid,Bitmap bit[],String currentrestaurant) {
        super(context, R.layout.list2,menunames);
        this.context=context;
        this.menunames=menunames;
        this.menudescription=menudescription;
        this.Cost=Cost;
        this.bit=bit;
        this.currentrestaurant=currentrestaurant;



    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.list2,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
            r.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,OrderActivity.class);
                //intent.putExtra("menulist",menunames[position]);

                //ByteArrayOutputStream bs = new ByteArrayOutputStream();
                //bit[position].compress(Bitmap.CompressFormat.PNG, 100, bs);
                //intent.putExtra("byteArray", bs.toByteArray());
                intent.putExtra("menulist",menunames[position]);
                intent.putExtra("currentrestaurant",currentrestaurant);
                intent.putExtra("cost",Cost[position]);

               /* Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", bit[position]);
                extras.putString("menulist",menunames[position]);
                intent.putExtras(extras);
                //startActivity(intent);*/


                context.startActivity(intent);


                //context.startActivity(new Intent(context, OrderActivity.class));

            }
        });
        }
        else
        {
            viewHolder= (ViewHolder) r.getTag();
        }
    viewHolder.im.setImageBitmap(bit[position]);
        viewHolder.tv1.setText(menunames[position]);
        viewHolder.tv2.setText(menudescription[position]);
        viewHolder.tv3.setText(Cost[position]);


        return r;
    }


     static class ViewHolder{
         TextView tv1;
         TextView tv2;
         TextView tv3;
         ImageView im;
         ViewHolder(View v)
         {
            tv1= (TextView) v.findViewById(R.id.textView);
             tv2= (TextView) v.findViewById(R.id.textView2);
             tv3= (TextView) v.findViewById(R.id.textView3);
             im=(ImageView)v.findViewById(R.id.imageView);


         }

     }

}
