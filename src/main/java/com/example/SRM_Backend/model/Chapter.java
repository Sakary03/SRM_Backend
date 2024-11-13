package com.example.SRM_Backend.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterID;

    private int chapterIndex;
    private Date dateUpdate;
    private int numPages=0;
    @ElementCollection
    private List<String> pages=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bookID")
    private Manga manga;

    // Getters and Setters

    public Long getChapterID() {
        return chapterID;
    }

    public void setChapterID(Long chapterID) {
        this.chapterID = chapterID;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public int getNumPages() {
        numPages=pages.size();
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }
}
