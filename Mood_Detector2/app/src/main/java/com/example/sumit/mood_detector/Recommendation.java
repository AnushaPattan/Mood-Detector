package com.example.sumit.mood_detector;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Recommendation extends AppCompatActivity {
    String type = "";
    Intent nextvr;
    Handler bluetoothIn;
    final int handlerState = 0;                         //used to identify handler message
    private StringBuilder recDataString = new StringBuilder();
    private BluetoothAdapter btAdapter = null;
    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String for MAC address
    private static String address;
    private BluetoothSocket btSocket = null;
    private ConnectedThread mConnectedThread;
Button b;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    TextView tv, tvbt;
     String sgsr="",stemp="",motion="",shumi="";
    int oldx=0,oldy=0,oldz=0,gsr=0;
    double temp=0.0,humi=0.0;
    Intent next;
    Spinner spnr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        b=(Button)findViewById(R.id.button);
        spnr = (Spinner)findViewById(R.id.spnrVideos);
        next=new Intent(this,play_song.class);
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String video = spnr.getSelectedItem().toString();
                play_song.video=video;
                startActivity(next);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nextvr=new Intent(this,View_Result.class);
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {

                    //if message is what we want
                    String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                    //   Toast.makeText(getApplicationContext(), ">>" + readMessage + "", Toast.LENGTH_LONG).show();
                    tvbt = (TextView) findViewById(R.id.tvbt);
                    recDataString.append(readMessage);


                    boolean flag = false;
//                    String ss=recDataString.toString();
//String l[]=ss.split("\n");
//                    int ii=l.length;
//                    String p=l[ii-1];
                   if( readMessage.contains("G:") && readMessage.contains("T:") && readMessage.contains("H:")  && readMessage.contains("X:") && readMessage.contains("Y:") && readMessage.contains("Z:"))

                   {
                       try{
                       tvbt.setText(readMessage);
                       if (readMessage.contains("\n")) {
                           String ll[] = readMessage.split("\n");
                           readMessage = ll[0];
                       }
                       String line[] = readMessage.split("=");
                       String g[] = line[0].split(":");
                       sgsr = g[1];
                       Log.d("gsr", sgsr);
                       gsr = Integer.parseInt(sgsr);
                       String t[] = line[1].split(":");
                       stemp = t[1];
                       Log.d("stemp", stemp);
                       temp = Double.parseDouble(stemp);
                           Log.d(" line[2]", line[2]);
                           String h[] = line[2].split(":");
                           shumi = h[1];
                           Log.d("shumi", shumi);
                           humi = Double.parseDouble(shumi);
                       String m[] = line[3].split(",");
                       String xx[] = m[0].split(":");
                       String yy[] = m[1].split(":");
                       String zz[] = m[2].split(":");
                       int nx = Integer.parseInt(xx[1].trim());
                       int ny = Integer.parseInt(yy[1].trim());

                       int nz = Integer.parseInt(zz[1].trim());
                       if ((Math.abs(oldx - nx) > 5) || (Math.abs(oldy - ny) > 5) || (Math.abs(oldz - nz) > 5)) {
                           motion = "yes";
                           oldx = nx;
                           oldy = ny;
                           oldz = nz;
                       } else {

                           motion = "no";
                       }
                       Toast.makeText(getApplicationContext(), gsr + "-" + temp + "-" +humi+"-"+ motion, Toast.LENGTH_LONG).show();

                   }catch(Exception ex)
                    {

                        ex.printStackTrace();
                    }


                  }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new send_alert().execute();
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();

        //Get MAC address from DeviceListActivity via intent
        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA
        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

        //create device and set the MAC address
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();

        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();


        } catch (IOException e2) {
            //insert code to deal with this
        }
    }
    public class send_alert extends AsyncTask<String , Void, String>
    {


        @Override
        protected String doInBackground(String... params) {
            String res="";
            try {
                URL u=new URL(Global.url+"get_recm");
                JSONObject j=new JSONObject();

                j.put("gsr",gsr+"");

                j.put("temp",temp+"");
                j.put("humi",humi+"");
                j.put("motion",motion+"");

                res=HttpCom.getResponse(u,j);





            } catch (Exception e) {
                e.printStackTrace();
            }


            return res;
        }


        @Override

        protected  void onPostExecute(String status)

        {
             if(!status.equals("####")) {
                 String ss[] = status.split("####");
                 String mood = ss[0];
                 String str = ss[1];
                 View_Result.gsr = gsr + "";
                 View_Result.humi = humi + "";
                 View_Result.temp = temp + "";
                 View_Result.motion = motion + "";
                 View_Result.str = str;
                 View_Result.mood = mood;
                 startActivity(nextvr);
             }
//            String[]st = status.split(":");
//            String[] str = new String[st.length + 1];
//            str[0] = "--None--";
//
//            for(int i = 1; i<str.length; i++){
//                str[i] = st[i-1];
//            }
//
//            ArrayAdapter arr = new ArrayAdapter(Recommendation.this,R.layout.spinner_item,str);
//            spnr.setAdapter(arr);

        }
    }


    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {

        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }
}


