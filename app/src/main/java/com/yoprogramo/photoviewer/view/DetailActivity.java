package com.yoprogramo.photoviewer.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.yoprogramo.photoviewer.R;
import com.yoprogramo.photoviewer.entities.Photo;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();


        String s = intent.getStringExtra("picture_detail");

        Log.d("Yes", "onCreate: " + s);



        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.myLinearLayOut);


    }
}
