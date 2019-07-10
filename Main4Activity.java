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

public class Main4Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
    AsyncTask<Void,Void,Void> mytask;
    String rno,fname,lname,email,contact,address,tenth,twelth,graduation,technical,fathername,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
        e6=findViewById(R.id.t6);
        e7=findViewById(R.id.t7);
        e8=findViewById(R.id.t8);
        e9=findViewById(R.id.t9);
        e10=findViewById(R.id.t10);
        e11=findViewById(R.id.t11);
        e12=findViewById(R.id.t8);
    }
    public void test(View v){


        rno=e1.getText().toString();
                fname=e2.getText().toString();
        lname=e3.getText().toString();
                email=e4.getText().toString();
        contact=e5.getText().toString();
                address=e6.getText().toString();
        tenth=e7.getText().toString();
                twelth=e8.getText().toString();
        graduation=e9.getText().toString();
                technical=e10.getText().toString();
        fathername=e11.getText().toString();
        password=e12.getText().toString();
        if(rno.length()==0||fname.length()==0||lname.length()==0||email.length()==0||contact.length()==0||address.length()==0 )
        {
            Toast.makeText(this,"Please fill data",Toast.LENGTH_LONG).show();
            return;
        }
        mytask=new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Contact(Main4Activity.this,rno,fname,lname,email,contact,address,tenth,twelth,graduation,technical,fathername,password);
                return null;
            }

        };
        mytask.execute(null,null,null);
    }

    static void Contact(Context c1, String rno, String fname, String lname,String email,String contact,String address,String tenth,String twelth,String graduation,String technical,String fathername,String password)
    {
        String serverURL = "https://phycho.000webhostapp.com/insstud.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("t1", rno);
        params.put("t2", fname);
        params.put("t3",lname );
        params.put("t4",email );
        params.put("t5",contact);
        params.put("t6",address );
        params.put("t7",tenth );
        params.put("t8",twelth );
        params.put("t9",graduation );
        params.put("t10",technical );
        params.put("t11",fathername );
        params.put("t12",password );
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
                        Toast.makeText(c,"New Student detail is uploaded",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        catch (Exception e) {
            Log.d("error",""+ e.getMessage());
        }

    }
}