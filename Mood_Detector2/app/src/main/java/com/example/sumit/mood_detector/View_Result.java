package com.example.sumit.mood_detector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class View_Result extends AppCompatActivity {
public static String gsr="",temp="",humi="",motion="",str="",mood="";
    Button b;
    TextView tvg,tvt,tvh,tvm,tvs;//,tvr;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__result);


        tvg=(TextView)findViewById(R.id.tcgsr);
        tvt=(TextView)findViewById(R.id.tvtemp);
        tvh=(TextView)findViewById(R.id.tvhumi);
        tvm=(TextView)findViewById(R.id.tvmotion);
        tvs=(TextView)findViewById(R.id.tvsteady);
 //       tvr=(TextView)findViewById(R.id.tvres);
next=new Intent(this,View_emo.class);
        tvg.setText(gsr);
        tvt.setText(temp);
        tvh.setText(humi);
        if(motion.equals("yes"))
        {
            tvm.setText(motion);
        }
        else{

            tvs.setText(motion);
        }



       // tvs.setText(str);
      //  tvr.setText(mood);//mood
        b=(Button)findViewById(R.id.button2);
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // DisplayVideo.str=str;
                 View_emo.str=str;
                 View_emo.mood=mood;
                 startActivity(next);
             }
         });


    }
}
