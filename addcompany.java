package com.example.peace;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
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

public class addcompany extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    AsyncTask<Void,Void,Void> mytask;
    String cname,email,contact,director,tenth,twelth,graduation,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompany);
        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
        e6=findViewById(R.id.t6);
        e7=findViewById(R.id.t7);
        e8=findViewById(R.id.t8);

    }

    public void test(View v){


        cname=e1.getText().toString();
        email=e2.getText().toString();
        contact=e3.getText().toString();
        director=e4.getText().toString();
        tenth=e5.getText().toString();
        twelth=e6.getText().toString();
        graduation=e7.getText().toString();
        password=e8.getText().toString();

        if(cname.length()==0||email.length()==0||contact.length()==0||director.length()==0||tenth.length()==0||twelth.length()==0 )
        {
            Toast.makeText(this,"Please fill data",Toast.LENGTH_LONG).show();
            return;
        }
        mytask=new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Contact(addcompany.this,cname,email,contact,director,tenth,twelth,graduation,password);
                return null;
            }

        };
        mytask.execute(null,null,null);
    }

    static void Contact(Context c1, String cname, String email, String contact, String director, String tenth, String twelth, String graduation, String password)
    {
        String serverURL = "https://phycho.000webhostapp.com/insertcompany.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("t1", cname);
        params.put("t2", email);
        params.put("t3",contact );
        params.put("t4",director );
        params.put("t5",tenth);
        params.put("t6",twelth );
        params.put("t7",graduation );
        params.put("t8",password );

        post(serverURL, params,c1);
    }

    public static void post(String serverurl, Map<String, String> params,final Context c) {
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
            else
            {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(c,"New Company detail is uploaded",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        catch (Exception e) {
            Log.d("error",""+ e.getMessage());
        }

    }
}
