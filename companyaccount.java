package com.example.peace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class companyaccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyaccount);
        getSupportActionBar().setTitle("My company");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public  void  test1(View v)
    {
        Intent i=new Intent(this,Main7Activity.class);
        i.putExtra("username",getIntent().getStringExtra("username"));
        startActivity(i);
    }

    public  void  test2(View v)
    {
        Intent i=new Intent(this,vieweligiblestd.class);
        i.putExtra("username",getIntent().getStringExtra("username"));
        startActivity(i);
    }
    public  void  test3(View v)
    {
        Intent i=new Intent(this,companydetail.class);
        i.putExtra("companyid",getIntent().getStringExtra("username"));
        startActivity(i);
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
