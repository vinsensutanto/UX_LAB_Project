package com.example.labux;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labux.R;
import com.example.labux.model.Anime;
import com.example.labux.ui.detail.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ANIME = "extra_anime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Anime anime = (Anime) getIntent().getSerializableExtra(EXTRA_ANIME);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailContainer, DetailFragment.newInstance(anime))
                    .commit();
        }
    }
}
