package com.example.labux.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labux.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Button btnNews, btnManga;
    private FrameLayout carouselContainer;

//    private androidx.core.widget.NestedScrollView newsScrollView;
    private RecyclerView recyclerViewManga;
    private AnimeAdapter adapter;
    private List<Anime> animeList;

    // >>> Tambahan
    private FrameLayout carouselContainer2;
    private TextView sectionTitle, sectionTitle2;
    // <<< Tambahan

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnNews = view.findViewById(R.id.btnNews);
        btnManga = view.findViewById(R.id.btnManga);
        carouselContainer = view.findViewById(R.id.carouselContainer);
        recyclerViewManga = view.findViewById(R.id.recyclerViewManga);

        // >>> Tambahan
        carouselContainer2 = view.findViewById(R.id.carouselContainer2);
        sectionTitle = view.findViewById(R.id.sectionTitle);
        sectionTitle2 = view.findViewById(R.id.sectionTitle2);
//        newsScrollView = view.findViewById(R.id.newsScrollView);

        // <<< Tambahan

        // Setup RecyclerView MANGA
        recyclerViewManga.setLayoutManager(new LinearLayoutManager(getContext()));
        animeList = new ArrayList<>();
        loadAnimeData();
        adapter = new AnimeAdapter(animeList);
        recyclerViewManga.setAdapter(adapter);

        // Default: tampilkan NEWS
        showNewsTab();

        btnNews.setOnClickListener(v -> showNewsTab());
        btnManga.setOnClickListener(v -> showMangaTab());

        return view;
    }
    private void updateTabColors(boolean isNewsActive) {
        if (getContext() == null) return;

        int activeColor = Color.parseColor("#B71C1C");   // merah gelap (dark red)
        int inactiveColor = Color.parseColor("#620000"); // merah keungu gelap, beda kontras

        if (isNewsActive) {
            btnNews.setBackgroundColor(activeColor);
            btnNews.setTextColor(Color.WHITE);

            btnManga.setBackgroundColor(inactiveColor);
            btnManga.setTextColor(Color.WHITE);
        } else {
            btnManga.setBackgroundColor(activeColor);
            btnManga.setTextColor(Color.WHITE);

            btnNews.setBackgroundColor(inactiveColor);
            btnNews.setTextColor(Color.WHITE);
        }
    }

    private void showNewsTab() {
//        if (newsScrollView != null) newsScrollView.setVisibility(View.VISIBLE);
        carouselContainer.setVisibility(View.VISIBLE);
        recyclerViewManga.setVisibility(View.GONE);

        if (sectionTitle != null) sectionTitle.setVisibility(View.VISIBLE);
        if (carouselContainer2 != null) carouselContainer2.setVisibility(View.VISIBLE);
        if (sectionTitle2 != null) sectionTitle2.setVisibility(View.VISIBLE);

        // Carousel pertama (NEWS)
        getChildFragmentManager().beginTransaction()
                .replace(R.id.carouselContainer, FragmentCarousel.newInstance("NEWS"))
                .commit();

        // Carousel kedua (Anime Movie)
        getChildFragmentManager().beginTransaction()
                .replace(R.id.carouselContainer2, FragmentCarousel.newInstance("MOVIE"))
                .commit();

        // 🔹 Update warna tab → NEWS aktif
        updateTabColors(true);
    }


    private void showMangaTab() {
//        if (newsScrollView != null) newsScrollView.setVisibility(View.GONE);
        carouselContainer.setVisibility(View.GONE);
        recyclerViewManga.setVisibility(View.VISIBLE);

        // >>> Tambahan
        if (carouselContainer2 != null) carouselContainer2.setVisibility(View.GONE);
        if (sectionTitle != null) sectionTitle.setVisibility(View.GONE);
        if (sectionTitle2 != null) sectionTitle2.setVisibility(View.GONE);
        // <<< Tambahan

        // 🔹 Update warna tab → MANGA aktif
        updateTabColors(false);
    }


    private void loadAnimeData() {
        animeList.add(new Anime("Yuukoku no Moriarty",
                "In the late 19th century, Great Britain rules over a quarter of the world. Nobles sit in their fancy homes in comfort and luxury, while the working class slaves away at their jobs.",
                R.drawable.moriarty));

        animeList.add(new Anime("Boku no Hero Academia",
                "Middle school student Izuku Midoriya wants to be a hero more than anything, but he hasn’t got an ounce of power in him. With no chance of ever getting into the prestigious U.A. High School for budding heroes.",
                R.drawable.bokunohero));

        animeList.add(new Anime("Oshi no Ko",
                "Sixteen-year-old Ai Hoshino is a talented and beautiful idol who is adored by her fans. She is the personification of a pure, young maiden. But all that glitters is not gold.",
                R.drawable.oshinoko));

        animeList.add(new Anime("Mayoiga",
                "A shady bus tour of young men and women is headed to an elusive village called Nanakimura. A destination where people can partake in a utopian existence, free of the world’s obstacles... or so goes the rumor.",
                R.drawable.mayoiga));

        animeList.add(new Anime("Angel Beats",
                "In a world after death, angels fight for their fate and their future. Yuri, the leader of the Shinda Sekai Sensen, rebels against the god who destined her to have an unreasonable life.",
                R.drawable.angelbeats));

        animeList.add(new Anime("Black Clover",
                "Asta and Yuno were abandoned together at the same church, and have been inseparable since. As children, they promised that they would compete against each other to see who would become the next sorcerous emperor.",
                R.drawable.blackclover));

        updateTabColors(false);
    }


    // =====================
    // MODEL
    // =====================
    public static class Anime {
        String title;
        String description;
        int imageRes;

        public Anime(String title, String description, int imageRes) {
            this.title = title;
            this.description = description;
            this.imageRes = imageRes;
        }
    }

    // =====================
    // ADAPTER RecyclerView
    // =====================
    public static class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {

        private List<Anime> animeList;

        public AnimeAdapter(List<Anime> animeList) {
            this.animeList = animeList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_manga, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Anime anime = animeList.get(position);
            holder.textTitle.setText(anime.title);
            holder.textDescription.setText(anime.description);
            holder.imageAnime.setImageResource(anime.imageRes);
        }

        @Override
        public int getItemCount() {
            return animeList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageAnime;
            TextView textTitle, textDescription;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageAnime = itemView.findViewById(R.id.imageAnime);
                textTitle = itemView.findViewById(R.id.textTitle);
                textDescription = itemView.findViewById(R.id.textDescription);
            }
        }

    }
}
