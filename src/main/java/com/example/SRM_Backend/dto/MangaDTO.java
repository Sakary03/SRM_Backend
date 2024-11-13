package com.example.SRM_Backend.dto;

import com.example.SRM_Backend.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MangaDTO {
    public String name;
    public String posterName;
    public List<Long> category=new ArrayList<>();

    public MangaDTO(String name, String posterName, List<String> category) {
        this.name = name;
        this.posterName = posterName;
        for (String cate:category) {
            this.category.add(Long.valueOf(cate));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public List<Long> getCategory() {
        return category;
    }

    public void setCategory(List<Long> category) {
        this.category = category;
    }
}
