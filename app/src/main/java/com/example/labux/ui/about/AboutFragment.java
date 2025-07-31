package com.example.labux.ui.about;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labux.R;
import com.example.labux.adapters.AnimeListAdapter;
import com.example.labux.data.AnimeRepository;
import com.example.labux.model.Anime;

import java.util.List;

public class AboutFragment extends Fragment {

    RecyclerView recyclerView;
    AnimeListAdapter animeListAdapter;
    List<Anime> animeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout first
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Color the Logo section
        View logoSection = view.findViewById(R.id.logoSection);
        View aboutSection = view.findViewById(R.id.aboutSection);
        View featuresSection = view.findViewById(R.id.featuresSection);
        View teamSection = view.findViewById(R.id.teamSection);

        if (logoSection != null && logoSection.getBackground() != null) {
            Drawable original = logoSection.getBackground().mutate();
            original.setColorFilter(Color.parseColor("#D89000"), PorterDuff.Mode.SRC_ATOP);
        }

        // Color the About Us section (EAA121)
        if (aboutSection != null && aboutSection.getBackground() != null) {
            Drawable original = aboutSection.getBackground().mutate();
            original.setColorFilter(Color.parseColor("#EAA121"), PorterDuff.Mode.SRC_ATOP);
        }

        // Color the Features section (F5C34A)
        if (featuresSection != null && featuresSection.getBackground() != null) {
            Drawable original = featuresSection.getBackground().mutate();
            original.setColorFilter(Color.parseColor("#F5C34A"), PorterDuff.Mode.SRC_ATOP);
        }

        // Color the Team section (FFD874)
        if (teamSection != null && teamSection.getBackground() != null) {
            Drawable original = teamSection.getBackground().mutate();
            original.setColorFilter(Color.parseColor("#FFD874"), PorterDuff.Mode.SRC_ATOP);
        }

        return view;
    }


}
