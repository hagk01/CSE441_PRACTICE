package com.example.btth4;

public class public class Student {
    private String hoten;
    private String mssv;
    private String lop;
    private double diem;

    public Student() {}

    public Student(String hoten, String mssv, String lop, double diem) {
        this.hoten = hoten;
        this.mssv = mssv;
        this.lop = lop;
        this.diem = diem;
    }

    public String getHoten() {
        return hoten;
    }

    public String getMssv() {
        return mssv;
    }

    public String getLop() {
        return lop;
    }

    public double getDiem() {
        return diem;
    }
}
