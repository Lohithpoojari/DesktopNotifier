package com.desktopnotifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class MainActivity extends AppCompatActivity {


    String mongoAppId= "desktopnotifier-fpyrk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        System.out.println("In Main");
        App app = new App(new AppConfiguration.Builder(mongoAppId).build());

    }
}
