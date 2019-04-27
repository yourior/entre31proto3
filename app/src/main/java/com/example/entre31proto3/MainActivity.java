package com.example.entre31proto3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        MenuItem nav_Login;
        View navuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //activity_main_drawer Set
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Nav_Header_Main Set
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        nav_Login = menu.findItem(R.id.LogIn_Button);
        navuser = navigationView.getHeaderView(0);
        TextView usernameview = navuser.findViewById(R.id.Username_View);
        TextView usercrediteview = navuser.findViewById(R.id.InAppsCredit_View);


        if(StillLogin.getUserName(MainActivity.this).length() == 0) //cek masi login atau tidak
        {
            nav_Login.setTitle("Log In");

            usernameview.setText("Please Login");
            usercrediteview.setText("Credit : Coming Soon");
        }
        else
        {
            nav_Login.setTitle("Log off");

            usernameview.setText(StillLogin.getUserName(this));
            usercrediteview.setText("Credit : Coming Soon");
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        TextView usernameview = navuser.findViewById(R.id.Username_View);
        TextView usercrediteview = navuser.findViewById(R.id.InAppsCredit_View);



        if (id == R.id.Setting_Button) {

            Intent intent = new Intent(this, DisplayMessageActivity.class);
            startActivity(intent);

        } else if (id == R.id.Basket_Button) {

            Intent intent = new Intent(this, Basket.class);
            startActivity(intent);

        }else if(id == R.id.LogIn_Button)
        {

            if(StillLogin.getUserName(MainActivity.this).length() == 0) //cek masi login atau tidak
            {
                // call Login Activity
                Intent intent = new Intent(this, LogIn.class);
                startActivity(intent);
            }
            else
            {
                // Log out
                StillLogin.Logout(this);
                usernameview.setText("Please Login");
                usercrediteview.setText("Credit : Coming Soon");
                nav_Login.setTitle("Log In");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static final String Extra_Message = "com.example.myfirstapp.MESSAGE";
    




}
