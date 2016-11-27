package com.hackit.hackroom.person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.hackit.hackroom.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class ProfileActivity extends AppCompatActivity {

    @Extra
    String name;

    @Extra
    String url;

    @ViewById(R.id.image_id)
    ImageView image;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);



        Picasso.with(this).load(url).into(image);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
