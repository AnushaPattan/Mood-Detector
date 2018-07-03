package com.example.sumit.mood_detector;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class play_song extends AppCompatActivity {
    public static String video ="";
    VideoView videoView;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        videoView = (VideoView)findViewById(R.id.vidVw);

        if(!video.equals("--None--")){

            pDialog = new ProgressDialog(play_song.this);
            // Set progressbar title
            pDialog.setTitle("Watch Video");
            // Set progressbar message
            pDialog.setMessage("Buffering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            // Show progressbar
            pDialog.show();

            try {
                MediaController controller = new MediaController(play_song.this);
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
}
