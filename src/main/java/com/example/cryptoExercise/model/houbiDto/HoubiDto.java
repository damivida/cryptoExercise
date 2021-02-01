package com.example.cryptoExercise.model.houbiDto;

import java.util.List;

public class HoubiDto {

    private String ch;
    private String status;
    private Long ts;
    List<HoubiData> data;

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public List<HoubiData> getData() {
        return data;
    }

    public void setData(List<HoubiData> data) {
        this.data = data;
    }
}
