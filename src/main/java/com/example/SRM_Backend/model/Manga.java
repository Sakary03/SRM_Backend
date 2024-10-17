package com.example.SRM_Backend.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    private String name;
    private int numChapter;
    private double rating;

    @ManyToMany
    @JoinTable(
            name = "manga_category",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "categoryID"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "manga")
    private Set<Chapter> chapters;

    @OneToMany(mappedBy = "manga")
    private Set<Comment> comments;

    // Getters and Setters

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
        return numChapter;
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
}

