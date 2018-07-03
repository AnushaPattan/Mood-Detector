package com.example.sumit.mood_detector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class View_emo extends AppCompatActivity {
    public static String res = "",str="",mood="";
    Button b;
    ImageView iv;
    Intent next;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emo);
        b=(Button)findViewById(R.id.br);
        next=new Intent(this,DisplayVideo.class);
        iv=(ImageView)findViewById(R.id.imageView6);
        tv=(TextView)findViewById(R.id.textView);
        if(mood.equalsIgnoreCase("Happy"))
        {
            iv.setImageResource(R.drawable.happy);
            tv.setText("Happy");
        }
        else if(mood.equalsIgnoreCase("surprise"))
        {

            iv.setImageResource(R.drawable.shocked);
            tv.setText("Surprise");
        }
        else if(mood.equalsIgnoreCase("Angry"))
        {

            iv.setImageResource(R.drawable.angry);
            tv.setText("Angry");
        }
        else if(mood.equalsIgnoreCase("Sad"))
        {
            iv.setImageResource(R.drawable.sad);
            tv.setText("Sad");
        }
        else if(mood.equalsIgnoreCase("depression"))
        {
            iv.setImageResource(R.drawable.depression);
            tv.setText("Depression");
        }
        else if(mood.equalsIgnoreCase("stress"))
        {
            iv.setImageResource(R.drawable.stress);
            tv.setText("Stress");
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayVideo.str=str;
                startActivity(next);
            }
        });
    }
}
