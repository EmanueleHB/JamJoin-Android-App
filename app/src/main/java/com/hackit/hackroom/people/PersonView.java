package com.hackit.hackroom.people;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hackit.hackroom.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public class PersonView extends RelativeLayout {
    @BindView(R.id.tagsText)
    TextView tags;
    @BindView(R.id.imageView)
    ImageView image;
    @BindView(R.id.nameTextView)
    TextView nameTxt;
    private PersonVM viewModel;


    public PersonView(Context context) {
        this(context, null);
    }

    public PersonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PersonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        View v = inflate(getContext(), R.layout.person_view, this);
        ButterKnife.bind(this, v);
    }


    public void setViewModel(PersonVM viewModel) {
        this.viewModel = viewModel;
        nameTxt.setText(viewModel.getName());
        tags.setText(viewModel.getInterests());
        Picasso.with(getContext()).load(viewModel.getImage()).into(image);

    }
}