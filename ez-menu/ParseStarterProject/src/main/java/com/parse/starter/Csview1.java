package com.parse.starter;

import android.widget.ArrayAdapter;
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

/**
 * Created by naren on 15-10-2017.
 */

public class Csview1 extends ArrayAdapter<String> {
    private String[] list;
    private String[] costlist;
    private Activity context;
    private String currentrestaurant;


    public Csview1(Activity context, String[] list, String[] costlist,String currentrestaurant) {
        super(context, R.layout.csview1, list);
        this.context = context;
        this.list = list;
        this.costlist = costlist;
        this.currentrestaurant=currentrestaurant;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View r = convertView;

        ViewHolder viewHolder=null;
        if(r==null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.csview1, null, true);
            viewHolder = new Csview1.ViewHolder(r);
            r.setTag(viewHolder);

            r.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,CustomerReview.class);
                    intent.putExtra("currentrestaurant",currentrestaurant);





                    context.startActivity(intent);




                }
            });




        }
        else
        {
            viewHolder= (ViewHolder) r.getTag();
        }
       // viewHolder.im.setImageBitmap(bit[position]);
        viewHolder.tv1.setText(list[position]);
        viewHolder.tv2.setText(costlist[position]);


        return r;
    }


    static class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        ImageView im;
        ViewHolder(View v)
        {
            tv1= (TextView) v.findViewById(R.id.textView14);
            tv2= (TextView) v.findViewById(R.id.textView16);


        }

    }

}
