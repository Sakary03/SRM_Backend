package com.example.SRM_Backend.dto.request;


import java.util.Set;

public class MangaUpdateDTO {

    private String name;
    private String overview;
    private String author;
    private Double rating;
    private Integer viewed;
    private Set<Long> categories; // This will store the category IDs
    private String poster; // This will store the URL of the poster

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Set<Long> getCategories() {
        return categories;
    }

    public void setCategories(Set<Long> categories) {
        this.categories = categories;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
