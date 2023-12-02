package com.example.assignment.Model;

import java.io.Serializable;

public class DAO_Staff implements Serializable {
    private  String MaNV,Name,PhongBan;

    public DAO_Staff(String maNV, String name, String phongBan) {
        MaNV = maNV;
        Name = name;
        PhongBan = phongBan;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String phongBan) {
        PhongBan = phongBan;
    }
}
