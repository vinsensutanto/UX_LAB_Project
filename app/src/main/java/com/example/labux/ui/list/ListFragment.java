package com.example.labux.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labux.DetailActivity;
import com.example.labux.R;
import com.example.labux.adapters.AnimeListAdapter;
import com.example.labux.data.AnimeRepository;
import com.example.labux.model.Anime;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    AnimeListAdapter animeListAdapter;
    List<Anime> animeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        animeList = AnimeRepository.getAnimeList();

        animeListAdapter = new AnimeListAdapter(getContext(), animeList, anime -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ANIME, anime);
            startActivity(intent);
        });


        recyclerView.setAdapter(animeListAdapter);

        return view;
    }
}
