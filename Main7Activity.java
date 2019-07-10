package com.example.peace;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

public class Main7Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    AsyncTask<Void,Void,Void> myTask;
    String qname,opt1,opt2,opt3,opt4,answer,compid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        getSupportActionBar().setTitle("Upload Questions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
        e6=findViewById(R.id.t6);
        e7=findViewById(R.id.t7);

        e7.setText(getIntent().getStringExtra("username"));
    }

    public void test(View v)
    {

      //  Toast.makeText(this,"button ",Toast.LENGTH_LONG).show();
        qname=e1.getText().toString();
        opt1=e2.getText().toString();
        opt2=e3.getText().toString();
        opt3=e4.getText().toString();
        opt4=e5.getText().toString();
        answer=e6.getText().toString();
        compid=e7.getText().toString();
        boolean sendstatus=true;
        if(qname.length()==0)
        {
            e1.setError("enter question ");
            sendstatus=false;

        }

        if(opt1.length()==0)
        {
            e2.setError("enter option 1");
            sendstatus=false;
        }
        if(opt2.length()==0)
        {
            e3.setError("enter oprion 2");
            sendstatus=false;
        }
        if(answer.length()==0)
        {
            e6.setError("enter answer");
            sendstatus=false;
        }

        if(sendstatus==false)
        {
            return;
        }
        myTask=new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Contact(Main7Activity.this, qname,opt1,opt2,opt3,opt4,answer,compid);
                return null;
            }
        };


        myTask.execute(null, null, null);


    }


    void Contact(Context c, String qname, String opt1, String opt2, String opt3, String opt4, String answer, String compid)
    {
        String serverURL = "https://phycho.000webhostapp.com/insquest.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("t1", qname);
        params.put("t2", opt1);
        params.put("t3", opt2);
        params.put("t4", opt3);
        params.put("t5", opt4);
        params.put("t6", answer);
        params.put("t7", compid);

        post(serverURL, params,c);
    }

    public static void post(String serverurl, Map<String, String> params,Context c) {
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
            else
            {
                Toast.makeText(c,"Question is uploaded",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Log.d("error",""+ e.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}