package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by naren on 15-10-2017.
 */

public class ViewPagerAdapter   extends PagerAdapter {
    Activity activity;
    String[] images;
    LayoutInflater inflater;

    public ViewPagerAdapter(Activity activity,String[]images){
        this.activity=activity;
        this.images=images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater=(LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview=inflater.inflate(R.layout.viewpager_item,container,false);
        ImageView image;
        image=(ImageView)itemview.findViewById(R.id.imageView3);
        DisplayMetrics dis=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        int height=dis.heightPixels;
        int width=dis.widthPixels;
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);
        try {
            Picasso.with(activity.getApplicationContext())
                    .load(images[position])
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(image);
        }
        catch (Exception ex){

        }
        container.addView(itemview);
        return itemview;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}
