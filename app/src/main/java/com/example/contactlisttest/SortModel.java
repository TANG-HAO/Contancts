package com.example.contactlisttest;

public  class SortModel {
    private String name;  //显示数据
    private String sortLetters; //显示数据拼音的首字母
    public String getSortLetters() {
        return sortLetters;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getName() {
        return name;
    }
}
