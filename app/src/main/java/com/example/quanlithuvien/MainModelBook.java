package com.example.quanlithuvien;

public class MainModelBook {
    Integer langLogo;
    String langName;

    public MainModelBook(Integer langLogo, String langName) {
        this.langLogo = langLogo;
        this.langName = langName;
    }

    public Integer getLangLogo() {
        return langLogo;
    }

    public void setLangLogo(Integer langLogo) {
        this.langLogo = langLogo;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }
}
