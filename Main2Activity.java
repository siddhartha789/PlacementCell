package com.example.peace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    String fn,ln,em,cn;
    String rno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
    }

    public void test(View v){
        rno=e1.getText().toString();
        fn=e2.getText().toString();
        ln=e3.getText().toString();
        em=e4.getText().toString();
        cn=e5.getText().toString();
        Intent i=new Intent(this,Main5Activity.class);
        i.putExtra("first",rno);
        i.putExtra("fname",fn);
        i.putExtra("lname",ln);
        i.putExtra("contact",em);
        i.putExtra("address",cn);
        startActivityForResult(i,101);

    }
}
