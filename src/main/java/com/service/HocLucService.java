package com.service;

public class HocLucService {

    public String xepLoaiHocLuc(double diem) {
        if (diem < 0.0 || diem > 10.0) {
            throw new IllegalArgumentException("Điểm phải nằm trong khoảng 0.0 đến 10.0");
        }
        if (diem >= 9.0) {
            return "Xuất sắc";
        } else if (diem >= 7.0) {
            return "Giỏi";
        } else if (diem >= 5.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }
}