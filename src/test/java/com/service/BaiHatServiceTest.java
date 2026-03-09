package com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Phần 2 - Kiểm tra chức năng Thêm BaiHat")
public class BaiHatServiceTest {

    private BaiHatService service;
    private static final float MAX_THOI_LUONG = 5.0f + 59.0f / 60.0f; // 5:59

    @BeforeEach
    void setUp() {
        service = new BaiHatService();
    }

    private BaiHat taoBaiHatHopLe(float thoiLuong) {
        return new BaiHat("BH001", "Tên Bài Test", "Ca Sĩ A", thoiLuong, "Pop");
    }

    @Test
    @DisplayName("TC01 - [P2] thoiLuong trung bình (3.5 phút) → Thêm thành công")
    void testThem_ThoiLuongTrungBinh() {
        assertTrue(service.them(taoBaiHatHopLe(3.5f)));
        assertEquals(1, service.getSoLuong());
    }

    @Test
    @DisplayName("TC02 - [P2] thoiLuong 4.0 phút → Thêm thành công")
    void testThem_ThoiLuong4Phut() {
        assertTrue(service.them(taoBaiHatHopLe(4.0f)));
    }

    @Test
    @DisplayName("TC03 - [Biên min] thoiLuong = 2:00 → Thêm thành công")
    void testThem_BienMin() {
        assertTrue(service.them(taoBaiHatHopLe(2.0f)));
    }

    @Test
    @DisplayName("TC04 - [Biên max] thoiLuong = 5:59 → Thêm thành công")
    void testThem_BienMax() {
        assertTrue(service.them(taoBaiHatHopLe(MAX_THOI_LUONG)));
    }

    @Test
    @DisplayName("TC05 - [Dưới biên min] thoiLuong = 1:59 → Exception")
    void testThem_DuoiBienMin() {
        assertThrows(IllegalArgumentException.class,
                () -> service.them(taoBaiHatHopLe(1.99f)));
    }

    @Test
    @DisplayName("TC06 - [Trên biên max] thoiLuong = 6:00 → Exception")
    void testThem_TrenBienMax() {
        assertThrows(IllegalArgumentException.class,
                () -> service.them(taoBaiHatHopLe(6.0f)));
    }

    @Test
    @DisplayName("TC07 - thoiLuong = 0 → Exception")
    void testThem_ThoiLuong0() {
        assertThrows(IllegalArgumentException.class,
                () -> service.them(taoBaiHatHopLe(0.0f)));
    }

    @Test
    @DisplayName("TC08 - thoiLuong âm → Exception")
    void testThem_ThoiLuongAm() {
        assertThrows(IllegalArgumentException.class,
                () -> service.them(taoBaiHatHopLe(-1.0f)));
    }

    @Test
    @DisplayName("TC09 - Thêm nhiều bài hát → Số lượng đúng")
    void testThem_NhieuBaiHat() {
        service.them(new BaiHat("BH001", "Bài 1", "Ca Sĩ A", 3.0f, "Pop"));
        service.them(new BaiHat("BH002", "Bài 2", "Ca Sĩ B", 4.5f, "Ballad"));
        service.them(new BaiHat("BH003", "Bài 3", "Ca Sĩ C", 2.5f, "EDM"));
        assertEquals(3, service.getSoLuong());
    }

    // ======== VALIDATE TRƯỜNG TRỐNG ========

    @Test
    @DisplayName("TC10 - Ma = null → Exception")
    void testThem_MaNull() {
        BaiHat baiHat = new BaiHat(null, "Tên Bài", "Ca Sĩ", 3.0f, "Pop");
        assertThrows(IllegalArgumentException.class, () -> service.them(baiHat));
    }

    @Test
    @DisplayName("TC11 - Ma = rỗng → Exception")
    void testThem_MaRong() {
        BaiHat baiHat = new BaiHat("", "Tên Bài", "Ca Sĩ", 3.0f, "Pop");
        assertThrows(IllegalArgumentException.class, () -> service.them(baiHat));
    }

    @Test
    @DisplayName("TC12 - Ten = null → Exception")
    void testThem_TenNull() {
        BaiHat baiHat = new BaiHat("BH001", null, "Ca Sĩ", 3.0f, "Pop");
        assertThrows(IllegalArgumentException.class, () -> service.them(baiHat));
    }

    @Test
    @DisplayName("TC13 - TenCaSi = khoảng trắng → Exception")
    void testThem_TenCaSiKhoangTrang() {
        BaiHat baiHat = new BaiHat("BH001", "Tên Bài", "   ", 3.0f, "Pop");
        assertThrows(IllegalArgumentException.class, () -> service.them(baiHat));
    }

    @Test
    @DisplayName("TC14 - TheLoai = null → Exception")
    void testThem_TheLoaiNull() {
        BaiHat baiHat = new BaiHat("BH001", "Tên Bài", "Ca Sĩ", 3.0f, null);
        assertThrows(IllegalArgumentException.class, () -> service.them(baiHat));
    }

    @Test
    @DisplayName("TC15 - BaiHat = null → Exception")
    void testThem_ObjectNull() {
        assertThrows(IllegalArgumentException.class, () -> service.them(null));
    }

    @Test
    @DisplayName("TC16 - Dữ liệu hợp lệ → Lưu đúng thông tin")
    void testThem_KiemTraDuLieuLuu() {
        BaiHat baiHat = new BaiHat("BH001", "Hoa Nở Không Màu", "Hoài Lâm", 3.25f, "Ballad");
        service.them(baiHat);
        BaiHat saved = service.getDanhSachBaiHat().get(0);
        assertAll(
                () -> assertEquals("BH001", saved.getMa()),
                () -> assertEquals("Hoa Nở Không Màu", saved.getTen()),
                () -> assertEquals("Hoài Lâm", saved.getTenCaSi()),
                () -> assertEquals(3.25f, saved.getThoiLuong()),
                () -> assertEquals("Ballad", saved.getTheLoai())
        );
    }
}