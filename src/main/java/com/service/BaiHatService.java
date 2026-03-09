package com.service;

import java.util.ArrayList;
import java.util.List;

public class BaiHatService {

    private List<BaiHat> danhSachBaiHat = new ArrayList<>();

    private static final float MIN_THOI_LUONG = 2.0f;
    private static final float MAX_THOI_LUONG = 5.0f + 59.0f / 60.0f;

    public boolean them(BaiHat baiHat) {
        if (baiHat == null) {
            throw new IllegalArgumentException("Bài hát không được null");
        }
        if (baiHat.getMa() == null || baiHat.getMa().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã bài hát không được để trống");
        }
        if (baiHat.getTen() == null || baiHat.getTen().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên bài hát không được để trống");
        }
        if (baiHat.getTenCaSi() == null || baiHat.getTenCaSi().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên ca sĩ không được để trống");
        }
        if (baiHat.getTheLoai() == null || baiHat.getTheLoai().trim().isEmpty()) {
            throw new IllegalArgumentException("Thể loại không được để trống");
        }
        if (baiHat.getThoiLuong() < MIN_THOI_LUONG || baiHat.getThoiLuong() > MAX_THOI_LUONG) {
            throw new IllegalArgumentException("Thời lượng bài hát phải từ 2:00 đến 5:59 phút");
        }

        danhSachBaiHat.add(baiHat);
        return true;
    }

    public List<BaiHat> getDanhSachBaiHat() { return danhSachBaiHat; }
    public int getSoLuong() { return danhSachBaiHat.size(); }
}