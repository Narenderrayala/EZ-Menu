package com.parse.starter;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class ImageSlider extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Button b1;


    String[]images={

            "https://www.godavarius.com/wp-content/uploads/2016/12/logo-light-01.png",
            "http://logok.org/wp-content/uploads/2015/02/Dominos-pizza-logo-old-1024x768.png",
            "http://res.cloudinary.com/simpleview/image/upload/v1438123960/clients/grandrapids/file_bcf11a47-7451-464f-8c4d-c9d3e85e9146.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/InNOut.svg/200px-InNOut.svg.png",
            "https://digitalmarketing.blob.core.windows.net/7279/skins/OscarsFamousPizzeria3/images/OscarsFamousPizza_design5b_home.png",
            "https://upload.wikimedia.org/wikipedia/en/thumb/8/8f/Levy_Restaurants_logo.svg/1280px-Levy_Restaurants_logo.svg.png"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ez-menu");
        setContentView(R.layout.activity_image_slider);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        adapter=new ViewPagerAdapter(ImageSlider.this,images);
        viewPager.setAdapter(adapter);
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
        b1=(Button)findViewById(R.id.button13);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ImageSlider.this,MainActivity.class);
                startActivity(i);
            }
        });

    }


    public class MyTimerTask extends TimerTask{
        @Override
        public void run(){
            ImageSlider.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);

                    }
                    else if(viewPager.getCurrentItem()==1)
                    {
                        viewPager.setCurrentItem(2);
                    }

                    else if(viewPager.getCurrentItem()==2)
                    {
                        viewPager.setCurrentItem(3);
                    }
                    else if(viewPager.getCurrentItem()==3)
                    {
                        viewPager.setCurrentItem(4);
                    }
                    else if(viewPager.getCurrentItem()==4)
                    {
                        viewPager.setCurrentItem(5);
                    }
                    else if(viewPager.getCurrentItem()==5)
                    {
                        viewPager.setCurrentItem(0);
                    }





                }
            });
        }
    }
}
