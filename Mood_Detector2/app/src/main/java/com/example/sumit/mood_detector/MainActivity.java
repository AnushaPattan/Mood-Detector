package com.example.sumit.mood_detector;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPass;

    Button btnLogin;
    Button btnReset;

    TextView txt;
    TextView txtRes;

    String email = "";
    String pass = "";

    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WindowManager manager = getWindowManager();
//        Display display = manager.getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        int height = size.y;
//
//
//        txtRes = (TextView)findViewById(R.id.txt);
//
//        txtRes.setText("Width :" + String.valueOf(width) + "    Height :"
//                + String.valueOf(height));
//
//        int screenSize = getResources().getConfiguration().screenLayout
//                & Configuration.SCREENLAYOUT_SIZE_MASK;
//
//        switch (screenSize) {
//            case Configuration.SCREENLAYOUT_SIZE_LARGE:
//                Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
//                break;
//            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
//                Toast.makeText(this, "Normal screen", Toast.LENGTH_LONG).show();
//                break;
//            case Configuration.SCREENLAYOUT_SIZE_SMALL:
//                Toast.makeText(this, "Small screen", Toast.LENGTH_LONG).show();
//                break;
//            default:
//                Toast.makeText(this,
//                        "Screen size is neither large, normal or small",
//                        Toast.LENGTH_LONG).show();
//        }

        txtEmail = (EditText)findViewById(R.id.editEmail);
         txtPass = (EditText)findViewById(R.id.editPass);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnReset = (Button)findViewById(R.id.btnReset);

             txt = (TextView)findViewById(R.id.txtUserReg);


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = txtEmail.getText().toString();
                pass = txtPass.getText().toString();

                new UserLogin().execute();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail.setText("");
                txtPass.setText("");
            }
        });
    }

    private class UserLogin extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {

            try{
                URL url = new URL(Global.url + "UserLogin");
                JSONObject jsn = new JSONObject();
                jsn.put("txtEmail",email);
                jsn.put("txtPass",pass);

                String response = HttpCom.getResponse(url,jsn);
                res = response;
            }

            catch(MalformedURLException err){
                err.printStackTrace();
            }

            catch(JSONException err){
                err.printStackTrace();
            }

            catch(IOException err){
                err.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s.equals("Login successful"));
            if(!s.equals("Login successful")){
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DeviceListActivity.class);
                startActivity(intent);
            }

        }
    }
}
