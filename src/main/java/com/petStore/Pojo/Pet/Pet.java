package com.petStore.Pojo.Pet;

import java.util.ArrayList;
import java.util.List;

public class Pet {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotUrls() {
        return photUrls;
    }

    public void setPhotUrls(String[] photUrls) {
        this.photUrls = photUrls;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public int id;
    public Category category;
    public String name;
    public String[] photUrls;
    public String status;
    public List<Tags> tags;
}
