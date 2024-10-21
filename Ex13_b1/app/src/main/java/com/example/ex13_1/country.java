package com.example.ex13_1;

public class country {
    private int flag;
    private String countryName;
    private String countryCapital

    public country(int flag, String countryName, String countryCapital) {
        this.flag = flag;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }
}
