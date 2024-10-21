package com.example.studen_view;

public class Student {
    public String hoten;
    public String mssv;
    public String lop;
    public double diem;

    public Student() { }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public Student(String hoten, String mssv, String lop, double diem) {
        this.hoten = hoten;
        this.mssv = mssv;
        this.lop = lop;
        this.diem = diem;
    }
}
