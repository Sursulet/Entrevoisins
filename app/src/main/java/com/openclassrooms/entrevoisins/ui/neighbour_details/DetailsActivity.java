package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.details_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.details_avatar)
    ImageView mAvatar;
    @BindView(R.id.details_name)
    TextView mName;
    @BindView(R.id.details_website)
    TextView mWebsite;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("neighbour");

        mCollapsingToolbarLayout.setTitle(neighbour.getName());

        Glide.with(mAvatar.getContext()).load(neighbour.getAvatarUrl()).into(mAvatar);
        mName.setText(neighbour.getName());
        mWebsite.setText(String.format("www.facebook.fr/%s", neighbour.getName().toLowerCase()));
        mFab.setImageResource((neighbour.getFavorite() ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp));

        mFab.setOnClickListener(view -> {
            neighbour.setFavorite(mApiService.changeStatus(neighbour));
            mFab.setImageResource((neighbour.getFavorite() ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp));
        });
    }

    @Override
    public void onBackPressed() { super.onBackPressed(); }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) { this.finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}