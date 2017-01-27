package com.example.dell.mygreeting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by DELL on 1/25/2017.
 */

public class GalleryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent sentIntent =getIntent();
        Bundle sentBundle = sentIntent.getExtras();

       GalleryActivityFragment fragment= new GalleryActivityFragment();
        fragment.setArguments(sentBundle);
        getSupportFragmentManager().beginTransaction().add(R.id.flG, fragment).commit();
    }
}
