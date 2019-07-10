package com.example.peace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Main5Activity extends AppCompatActivity {
    EditText e6,e7,e8,e9;
    String add,tn,tw,gr,em,cn,rno,fn,ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        rno=getIntent().getStringExtra("first");
        fn=getIntent().getStringExtra("fname");
        ln=getIntent().getStringExtra("lname");
        em=getIntent().getStringExtra("contact");
        cn=getIntent().getStringExtra("address");
        e6=findViewById(R.id.t6);
        e7=findViewById(R.id.t7);
        e8=findViewById(R.id.t8);
        e9=findViewById(R.id.t9);
    }

    public void test1(View v){
        add=e6.getText().toString();
        tn=e7.getText().toString();
        tw=e8.getText().toString();
        gr=e9.getText().toString();

        Intent i1=new Intent(this,Main6Activity.class);
        i1.putExtra("first",rno);
        i1.putExtra("fname",fn);
        i1.putExtra("lname",ln);
        i1.putExtra("email",em);
        i1.putExtra("contact",cn);
        i1.putExtra("address",add);
        i1.putExtra("tenth",tn);
        i1.putExtra("twelth",tw);
        i1.putExtra("graduation",gr);


        startActivityForResult(i1,101);

    }
}