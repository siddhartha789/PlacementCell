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

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    AsyncTask<Void,Void,Void> myTask;
    String id,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.t1);
        e2=(EditText)findViewById(R.id.t2);
        e3=(EditText)findViewById(R.id.t3);

    }

    public void test(View v)
    {

        Toast.makeText(this,"button ",Toast.LENGTH_LONG).show();
        id=e1.getText().toString();
        username=e2.getText().toString();
        password=e3.getText().toString();
        myTask=new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Contact(MainActivity.this, id,username,password);
                return null;
            }
        };


        myTask.execute(null, null, null);


    }


    static void Contact(Context c, String id, String username, String password)
    {
        String serverURL = "https://phycho.000webhostapp.com/testdata.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("t1", id);
        params.put("t2", username);
        params.put("t3", password);

        post(serverURL, params);
    }

    public static void post(String serverurl, Map<String, String> params) {
        URL u = null;
        try {
            u = new URL(serverurl);
        }catch (MalformedURLException e) {
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