package com.example.network.DTO;

import java.util.List;

public class homeDTO {
    private String name;
    private List<Double> total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getTotal() {
        return total;
    }

    public void setTotal(List<Double> total) {
        this.total = total;
    }
}
