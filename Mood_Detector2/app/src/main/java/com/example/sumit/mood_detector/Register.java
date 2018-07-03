package com.example.sumit.mood_detector;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Register extends AppCompatActivity {

    EditText txtName;
    EditText txtEmail;
    EditText txtPass;
    EditText txtCnfPass;

    Button btnRegister;
    Button btnReset;

     String name = "";
    String email = "";
     String pass = "";
    String cnf_pass = "";

    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

           txtName = (EditText)findViewById(R.id.editRegName);
          txtEmail = (EditText)findViewById(R.id.editRegEmail);
           txtPass = (EditText)findViewById(R.id.editRegPass);
        txtCnfPass = (EditText)findViewById(R.id.editRegCnfPass);

        btnRegister = (Button)findViewById(R.id.btnReg);
           btnReset = (Button)findViewById(R.id.btnRegReset);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    name = txtName.getText().toString();
                   email = txtEmail.getText().toString();
                    pass = txtPass.getText().toString();
                cnf_pass = txtCnfPass.getText().toString();

                if(name.equals("") || email.equals("") || pass.equals("") || cnf_pass.equals("")){
                    Toast.makeText(Register.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

                else
                    if(!pass.equals(cnf_pass)){
                        Toast.makeText(Register.this,"'Password' and 'Confirm Password' should be same",Toast.LENGTH_SHORT).show();
                    }

                    else{
                        new UserRegister().execute();
                    }

            }
        });
    }


    private class UserRegister extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {

            try{
                URL url = new URL(Global.url + "UserReg");
                JSONObject jsn = new JSONObject();
                jsn.put("txtName",name);
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
            Log.d("Response",s);
            System.out.println(s.equals("Registration successful"));

            Toast.makeText(Register.this,s,Toast.LENGTH_SHORT).show();

            if(!s.equals("Registration successful")){
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
