package com.example.demo.entity;

import com.example.demo.entity.TrueCallerDataDto;

import java.util.List;

public class TrueCallerRespDto {

    private List<TrueCallerDataDto> data;

    public List<TrueCallerDataDto> getData() {
        return data;
    }

    public void setData(List<TrueCallerDataDto> data) {
        this.data = data;
    }

}