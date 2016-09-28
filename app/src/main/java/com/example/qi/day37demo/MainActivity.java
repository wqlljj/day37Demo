package com.example.qi.day37demo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FragmentManager manager;
    private MD5 md5;
    private Base64 base64;
    private Des des;
    private Rsa rsa;
    private Signed signed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.activity_drawer);
        ((NavigationView) findViewById(R.id.main_menu)).setNavigationItemSelectedListener(this);
        manager = getSupportFragmentManager();
        md5 = new MD5();
        base64 = new Base64();
        des = new Des();
        rsa = new Rsa();
        signed = new Signed();
        manager.beginTransaction().replace(R.id.frame,md5).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (item.getItemId()){
            case R.id.menu_md5:
                transaction.replace(R.id.frame,md5);
                break;
            case R.id.menu_base64:
                transaction.replace(R.id.frame,base64);
                break;
            case R.id.menu_des:
                transaction.replace(R.id.frame,des);
                break;
            case R.id.menu_rsa:
                transaction.replace(R.id.frame,rsa);
                break;
            case R.id.menu_signed:
                transaction.replace(R.id.frame,signed);
                break;
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
