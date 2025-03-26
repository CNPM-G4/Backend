package com.example.network.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class requestUsedDTO {
    @JsonProperty("ID")
    private int ID;

    @JsonProperty("time")
    private double time;

    @JsonProperty("total")
    private double total;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
