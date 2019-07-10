package com.example.peace;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class mainwelcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainwelcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Placement Cell");
        toolbar.setTitleTextColor(Color.WHITE);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String sharebody="https://www.scribd.com/doc/99169140/Training-And-Placement";
                String shareSubject="Placement Cell";
                sharingIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                startActivity(Intent.createChooser(sharingIntent,"Share Using"));

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void admin(View v)
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    public void test1(View v)
    {
        Intent i=new Intent(this,studentlogin.class);
        startActivity(i);
    }

    public void test2(View v)
    {
        Intent i=new Intent(this,companylogin.class);
        startActivity(i);
    }
    public void test3(View v)
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainwelcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(this,StudentData.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {

            Intent i=new Intent(this,ViewStudentDetails.class);
            startActivity(i);

        } else if (id == R.id.nav_tools) {

            Intent i=new Intent(this,vieweligiblecompany.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent sharingIntent=new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String sharebody="https://www.scribd.com/doc/99169140/Training-And-Placement";
            String shareSubject="Placement Cell";
            sharingIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
            startActivity(Intent.createChooser(sharingIntent,"Share Using"));

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
