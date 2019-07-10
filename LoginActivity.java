package com.example.peace;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v)
    {

        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);

        boolean st=true;
        if(t1.getText().toString().length()==0)
        {
            t1.setError("Enter Username");
            st=false;
        }
        if(t2.getText().toString().length()==0)
        {
            t2.setError("Enter Password");
            st=false;
        }

        if(st==true)
        {

            if(CheckConnection.isNetworkAvailable(LoginActivity.this))
            {
                AdminLoginTask l = new AdminLoginTask(this);
                l.execute(t1.getText().toString(), t2.getText().toString());
            }
            else
            {
                Toast.makeText(LoginActivity.this, "Please turn on your network connection to avail services.", Toast.LENGTH_SHORT).show();

            }

        }

    }


    class AdminLoginTask extends AsyncTask<String,Void,String>
    {
        private ProgressDialog dialog;
        private Context context;
        public AdminLoginTask(Context c) {
            this.context = c;
            this.dialog=new ProgressDialog(context);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Processing");
            this.dialog.show();
        }
        @Override
        protected String doInBackground(String... params) {

            String user,p;
            user=params[0];
            p=params[1];

            String serverURL="https://phycho.000webhostapp.com/newfetch.php";
            InputStream is=null;
            List<NameValuePair> nameValuePairs=new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("t1",user));
            nameValuePairs.add(new BasicNameValuePair("t2",p));
            String result=null;

            try{

                HttpClient httpClient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost(serverURL);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response=httpClient.execute(httpPost);
                HttpEntity entity=response.getEntity();
                is=entity.getContent();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
                StringBuilder sb=new StringBuilder();
                String line=null;
                while ((line=reader.readLine())!=null){
                    sb.append(line+"\n");
                }
                result=sb.toString();

            }catch(Exception e){
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(s);

            String s=result.trim();
            if (s.equalsIgnoreCase("welcome")){
                Intent intent=new Intent(LoginActivity.this,WelcomeAdmin.class);
                intent.putExtra("username", t1.getText().toString());
                startActivity(intent);
            }
            else {

                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
            if (dialog.isShowing())
            {
                dialog.dismiss();
            }

        }
    }
}
