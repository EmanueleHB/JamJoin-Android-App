package com.hackit.hackroom.person;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.hackit.hackroom.PeopleActivity_;
import com.hackit.hackroom.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import eu.fiskur.chipcloud.ChipCloud;
import eu.fiskur.chipcloud.ChipListener;

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
    @ViewById(R.id.chip_cloud)
    ChipCloud chipCloud;

    private String[] keywords;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

        keywords = getResources().getStringArray(R.array.labels);
        context = this;

        Picasso.with(this).load(url).into(image);


        chipCloud.setChipListener(new ChipListener() {
            @Override
            public void chipSelected(int i) {
                PeopleActivity_.intent(context).query(keywords[i]).start();
            }

            @Override
            public void chipDeselected(int i) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
