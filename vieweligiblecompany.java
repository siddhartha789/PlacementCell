package com.example.peace;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.List;

public class vieweligiblecompany extends AppCompatActivity {

    EditText t1;
    ListView l1;
    ArrayList<cscompany> ldata=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieweligiblecompany);
        t1=(EditText)findViewById(R.id.e1);
        l1=(ListView)findViewById(R.id.l1);
    }

    public  void  test(View v)
    {
        if(t1.getText().toString().length()==0)
        {
            t1.setError("enter roll number");
            return;
        }
        ldata.clear();
        String companyid=t1.getText().toString();
        TestTask obj=new TestTask(this);
        obj.execute(companyid);
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

                    cscompany stdobj=new cscompany();
                    obj = ja.getJSONObject(i);
                    stdobj.setCname(obj.getString("cname"));
                    stdobj.setEmail(obj.getString("email"));
                    stdobj.setContact(obj.getString("contact"));
                    ldata.add(stdobj);
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

            cscompanyadapter adp=new cscompanyadapter(ldata,R.layout.mylayout1,vieweligiblecompany.this);
            l1.setAdapter(adp);

        }

        @Override
        protected String doInBackground(String... strings) {
            String serverURL="https://phycho.000webhostapp.com/vieweligiblecompanies.php";
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
}
