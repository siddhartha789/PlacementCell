package com.example.peace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin);
    }

    public  void test1(View v)
    {
        Intent i=new Intent(this,Main4Activity.class);
        startActivity(i);

    }
    public  void test2(View v)
    {
        Intent i=new Intent(this,addcompany.class);
        startActivity(i);

    }
    public  void test3(View v)
    {
        Intent i=new Intent(this,vieweligiblecompany.class);
        startActivity(i);
    }

    public  void test4(View v)
    {
        Intent i=new Intent(this,vieweligiblestd.class);
        startActivity(i);
    }

    public  void test5(View v)
    {
        Intent i=new Intent(this,StudentData.class);
        startActivity(i);
    }

    public  void test6(View v)
    {
        Intent i=new Intent(this,ViewStudentDetails.class);
        startActivity(i);
    }
    public  void test7(View v)
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}
