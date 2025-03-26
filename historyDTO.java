package com.example.network.DTO;

import java.util.List;

public class historyDTO {
    private String name;

    private List<String> date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
