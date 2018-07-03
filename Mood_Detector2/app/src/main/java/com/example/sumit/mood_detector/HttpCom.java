package com.example.sumit.mood_detector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Prateek on 2/17/2018.
 */

public class HttpCom {

    public static String getResponse(URL url, JSONObject jsn) throws JSONException,UnsupportedEncodingException,IOException{

        String strSend = "";
        Iterator<String> itr = jsn.keys();
        boolean first = true;

        while(itr.hasNext()){

            String key = itr.next();
            String value = jsn.get(key).toString();

            if(first){
                first = false;
            }

            else{
                strSend += "&";
            }

            strSend += URLEncoder.encode(key,"UTF-8") + "=" + URLEncoder.encode(value,"UTF-8");
        }

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        writer.write(strSend);
        writer.close();

        int code = con.getResponseCode();

        if(code == 200){

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String strRecv = "";
            int c = reader.read();

            while(c != -1) {
                char ch = (char)c;
                strRecv += String.valueOf(ch);
                c = reader.read();
            }


            return strRecv;
        }

        else{
            return "Error Code : " + code;
        }

    }

}
