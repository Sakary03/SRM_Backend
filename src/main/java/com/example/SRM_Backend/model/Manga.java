package com.example.SRM_Backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    private String name;
    private int numChapter = 0;
    private double rating = 10.0;
    private String poster;

    @ManyToMany
    @JoinTable(
            name = "manga_category",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "categoryID"))
    @JsonManagedReference
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "manga")
    @JsonIgnore
    private Set<Chapter> chapters = new HashSet<>();

    @OneToMany(mappedBy = "manga")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();


    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumChapter() {
        return chapters != null ? chapters.size() : 0;
    }

    public void setNumChapter(int numChapter) {
        this.numChapter = numChapter;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}