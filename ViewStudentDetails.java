package com.example.peace;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class ViewStudentDetails extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
EditText rno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_details);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
        rno=(EditText)findViewById(R.id.rno);
    }

    public void fetch(View v)
    {

        if(rno.getText().toString().length()==0)
        {
            rno.setError("enter roll number");
            return;
        }
String r=rno.getText().toString();
TestTask obj1=new TestTask(this);
obj1.execute(r);
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

                    csstudent stdobj=new csstudent();
                    obj = ja.getJSONObject(i);
                    t1.setText(obj.getString("rno"));
                    t2.setText(obj.getString("fname"));
                    t3.setText(obj.getString("lname"));
                    t4.setText(obj.getString("contact"));
                    t5.setText(obj.getString("email"));

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
            String serverURL="https://phycho.000webhostapp.com/viewstudentbyrno.php";
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
