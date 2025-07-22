package com.example.labux.model;

public class Anime {
    private String title;
    private String genre;
    private String description;
    private int imageResource;

    public Anime(String title, String genre, String description, int imageResource) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getDescription() { return description; }
    public int getImageResource() { return imageResource; }
}
