package com.example.SRM_Backend.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public class ChapterResponseDTO {
    int chapterIndex;
    List<MultipartFile> pages;
    Date dateUpdate;
    Long bookid;

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public List<MultipartFile> getPages() {
        return pages;
    }

    public void setPages(List<MultipartFile> pages) {
        this.pages = pages;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }
}
