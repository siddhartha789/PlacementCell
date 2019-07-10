package com.example.peace;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main8Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    AsyncTask<Void,Void,Void> mytask;
    String rno,email,contact,tenth,twelth,graduation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
        e6=findViewById(R.id.t6);
    }
    public void test(View v){
        Toast.makeText(this,"Data Transferred",Toast.LENGTH_LONG).show();
        rno=e1.getText().toString();
        email=e2.getText().toString();
        contact=e3.getText().toString();
        tenth=e4.getText().toString();
        twelth=e5.getText().toString();
        graduation=e6.getText().toString();
        mytask=new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Contact(Main8Activity.this,rno,email,contact,tenth,twelth,graduation);
                return null;
            }

        };
        mytask.execute(null,null,null);
    }

    static void Contact(Context c1, String rno,String email,String contact,String tenth,String twelth,String graduation)
    {
        String serverURL = "https://phycho.000webhostapp.com/updatestud.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("t1", rno);
        params.put("t2",email );
        params.put("t3",contact);
        params.put("t4",tenth );
        params.put("t5",twelth );
        params.put("t6",graduation);
        post(serverURL, params);
    }

    public static void post(String serverurl, Map<String, String> params) {
        URL u = null;
        try {
            u = new URL(serverurl);
        } catch (MalformedURLException e) {
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> p = it.next();
            sb.append(p.getKey()).append("=").append(p.getValue());
            if (it.hasNext()) {
                sb.append('&');
            }
        }
        String body = sb.toString();
        byte[] bytes = body.getBytes();
        HttpURLConnection uc = null;
        try {
            uc = (HttpURLConnection) u.openConnection();
            uc.setDoOutput(true);
            uc.setUseCaches(false);
            uc.setFixedLengthStreamingMode(bytes.length);
            uc.setRequestMethod("POST");
            uc.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStream out = uc.getOutputStream();
            out.write(bytes);
            out.close();
            int status = uc.getResponseCode();
            if (status != 200) {
                Log.d("invalid request code ", "status s " + status);
            }
        }
        catch (Exception e) {
            Log.d("error",""+ e.getMessage());
        }

    }
}