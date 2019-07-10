package com.example.peace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Test1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        ListView l1=(ListView)findViewById(R.id.l1);
        csstudent o1=new csstudent();
        o1.setRno(11);
        o1.setFname("kk");

        o1.setContact("333");
        ArrayList<csstudent>ldata=new ArrayList<>();
        ldata.add(o1);
        csstudentadapter adp=new csstudentadapter(ldata,R.layout.activity_student_layout1,this);
        l1.setAdapter(adp);

        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
    }
}
