package com.example.peace;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CompanyCorrection extends AppCompatActivity {
    ArrayList<cscompany> ldata = new ArrayList<cscompany>();
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_correction);
        l1 = (ListView) findViewById(R.id.l1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TestTask oo = new TestTask(this);
        oo.execute("");
    }

    class TestTask extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog;
        private Context context;

        public TestTask(Context context) {

            this.context = context;
            this.dialog = new ProgressDialog(context);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Processing");
            this.dialog.show();
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject obj;
                obj = new JSONObject(s);
                JSONArray ja = obj.optJSONArray("details");
                for (int i = 0; i < ja.length(); i++) {
                    cscompany cmobj = new cscompany();
                    obj = ja.getJSONObject(i);
                    cmobj.setCname(obj.getString("cname"));
                    cmobj.setEmail(obj.getString("email"));
                    cmobj.setContact(obj.getString("contact"));
                    ldata.add(cmobj);


                }

                //  this.dialog.dismiss();
            } catch (Exception e) {
                Log.d("message", e.getMessage().toString());
            }

            cscompanyadapter adp = new cscompanyadapter(ldata, R.layout.companyall,CompanyCorrection.this);
            l1.setAdapter(adp);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            String serverURL = "https://phycho.000webhostapp.com/viewallcompany.php";
            InputStream is = null;

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            //nameValuePairs.add(new BasicNameValuePair("catid", cati));

            String result = null;

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
                return result + "";
            } catch (Exception e) {

                Log.d("message do in back ", e.getMessage().toString());

            }

            return "";
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);

    }
}
