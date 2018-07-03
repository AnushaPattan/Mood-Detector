package com.example.sumit.mood_detector;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayVideo extends AppCompatActivity {

    Spinner spnr;
    public static String res = "",str="";
    VideoView videoView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);
        spnr = (Spinner)findViewById(R.id.spnrVideos);
       // new getVideos().execute();
        String[]st = str.split(":");
        String[] str = new String[st.length + 1];
        str[0] = "--None--";

        for(int i = 1; i<str.length; i++){
            str[i] = st[i-1];
        }

        ArrayAdapter arr = new ArrayAdapter(DisplayVideo.this,R.layout.spinner_item,str);
        spnr.setAdapter(arr);
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String video = spnr.getSelectedItem().toString();
                   videoView = (VideoView)findViewById(R.id.vidVw);

                if(!video.equals("--None--")){

                    pDialog = new ProgressDialog(DisplayVideo.this);
                    // Set progressbar title
                    pDialog.setTitle("Watch Video");
                    // Set progressbar message
                    pDialog.setMessage("Buffering...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    // Show progressbar
                    pDialog.show();

                    try {
                        MediaController controller = new MediaController(DisplayVideo.this);
                        controller.setAnchorView(videoView);
                        final Uri vid = Uri.parse(Global.url + video);
                        videoView.setMediaController(controller);
                        videoView.setVideoURI(vid);

                        videoView.requestFocus();
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                pDialog.dismiss();
                                videoView.start();
                            }
                        });
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class getVideos extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL(Global.url + "GetVideo");
                JSONObject jsn = new JSONObject();
                jsn.put("task","task");
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
            String[]st = s.split(":");
            String[] str = new String[st.length + 1];
            str[0] = "--None--";

            for(int i = 1; i<str.length; i++){
                str[i] = st[i-1];
            }

            ArrayAdapter arr = new ArrayAdapter(DisplayVideo.this,R.layout.spinner_item,str);
            spnr.setAdapter(arr);
        }
    }
}
