package com.example.network.DTO;

import com.example.network.DTO.Response.totalResponseDTO;

import java.util.List;

public class totalDTO {
    private String name;

    private List<totalResponseDTO> total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<totalResponseDTO> getTotal() {
        return total;
    }

    public void setTotal(List<totalResponseDTO> total) {
        this.total = total;
    }
}

