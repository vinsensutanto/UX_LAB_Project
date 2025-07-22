package com.example.labux.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.labux.R;
import com.example.labux.model.Anime;

import java.util.List;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder> {

    private Context context;
    private List<Anime> animeList;

    public AnimeListAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.anime_item, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.title.setText(anime.getTitle());
        holder.genre.setText(anime.getGenre());
        holder.description.setText(anime.getDescription());
        holder.image.setImageResource(anime.getImageResource());

        holder.readMore.setOnClickListener(v -> {
            // TODO: Tambahkan aksi "Read More"
            // Contoh: Toast.makeText(context, "Read More: " + anime.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, genre, description;
        Button readMore;

        public AnimeViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.animeImage);
            title = itemView.findViewById(R.id.animeTitle);
            genre = itemView.findViewById(R.id.animeGenre);
            description = itemView.findViewById(R.id.animeDescription);
            readMore = itemView.findViewById(R.id.readMoreButton);
        }
    }
}

