package com.example.peace;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;

public class StudentData extends AppCompatActivity {

    ArrayList<csstudent> ldata=new ArrayList<>();
ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);
        l1=(ListView)findViewById(R.id.l1);

       // csstudentadapter adp=new csstudentadapter(ldata,R.layout.activity_student_layout1,this);
        //l1.setAdapter(adp);
       // new StudentTask(StudentData.this).execute("");
       TestTask oo=new TestTask(this);
oo.execute("");

    }

    class TestTask extends  AsyncTask<String,Void,String>
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

                    csstudent stdobj=new csstudent();
                    obj = ja.getJSONObject(i);

                    stdobj.setFname(obj.getString("fname"));
                    stdobj.setContact(obj.getString("contact"));

                    stdobj.setRno(Integer.parseInt(obj.getString("rno")));

                    ldata.add(stdobj);

                }

                //  this.dialog.dismiss();
            }
            catch (Exception e)
            {
                Log.d("message",e.getMessage().toString());
            }

            csstudentadapter adp=new csstudentadapter(ldata,R.layout.mylayout1,StudentData.this);
            l1.setAdapter(adp);
            if (dialog.isShowing())
            {
                dialog.dismiss();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            String serverURL="https://phycho.000webhostapp.com/viewstudents.php";
            InputStream is=null;

            List<NameValuePair> nameValuePairs=new ArrayList<>();
            //    nameValuePairs.add(new BasicNameValuePair("catid", cati));

            String result=null;

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(serverURL);
                //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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
