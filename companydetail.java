package com.example.peace;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class companydetail extends AppCompatActivity {

    String companyid;
EditText t1,t2,t3,t4,t5,t6;
    AsyncTask<Void,Void,Void> myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companydetail);
        t1=(EditText)findViewById(R.id.e1);
        t2=(EditText)findViewById(R.id.e2);
        t3=(EditText)findViewById(R.id.e3);
        t4=(EditText)findViewById(R.id.e4);
        t5=(EditText)findViewById(R.id.e5);
        t6=(EditText)findViewById(R.id.e6);
        fetch();
    }


    public void fetch()
    {

     // String companyid=getIntent().getStringExtra("companyid");
         companyid=getIntent().getStringExtra("companyid");
        TestTask obj1=new TestTask(this);
        obj1.execute(companyid);
    }


    class TestTask extends AsyncTask<String,Void,String>
    {
        private ProgressDialog dialog;
        private Context context;

        public TestTask(Context c)
        {
            this.context = c;
            this.dialog=new ProgressDialog(context);
        }


        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Processing");
            this.dialog.show();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONObject obj;
                obj = new JSONObject(s);
                JSONArray ja = obj.optJSONArray("details");
                for (int i = 0; i < ja.length(); i++) {


                    obj = ja.getJSONObject(i);
                    t1.setText(obj.getString("cname"));
                    t2.setText(obj.getString("contact"));
                    t3.setText(obj.getString("email"));
                    t4.setText(obj.getString("tenth"));
                    t5.setText(obj.getString("twelth"));
                    t6.setText(obj.getString("graduation"));


                }

                //  this.dialog.dismiss();
            }
            catch (Exception e)
            {
                Log.d("message",e.getMessage().toString());
            }

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            String serverURL="https://phycho.000webhostapp.com/viewcompanybyid.php";
            InputStream is=null;
            String r=strings[0];
            List<NameValuePair> nameValuePairs=new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("t1", r));

            String result=null;

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(serverURL);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity = response.getEntity();

                is = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
                return result+"";
            } catch (Exception e) {

                Log.d("message do in back ",e.getMessage().toString());

            }

            return "";
        }
    }

    public  void updatedata(View v)
    {
        myTask=new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                Contact(companydetail.this, t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),t5.getText().toString(),t6.getText().toString(),companyid);
                return null;
            }
        };


        myTask.execute(null, null, null);
    }


    void Contact(Context c, String cname, String email, String contact, String tenth, String twel, String graduation, String compid)
    {
        String serverURL = "https://phycho.000webhostapp.com/updatecompany.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("t1", cname);
        params.put("t2", email);
        params.put("t3", contact);
        params.put("t4", tenth);
        params.put("t5", twel);
        params.put("t6", graduation);
        params.put("t7", compid);

        post(serverURL, params,getBaseContext());
    }

    public static void post(String serverurl, Map<String, String> params,final Context c) {
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(c,"Changes are Updated",Toast.LENGTH_LONG).show();
                    }
                });

            }
        }
        catch (Exception e) {
            Log.d("error",""+ e.getMessage());
        }
    }
}
