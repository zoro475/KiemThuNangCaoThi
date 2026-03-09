package com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Phần 1 - Kiểm tra xếp loại học lực")
public class HocLucServiceTest {

    private HocLucService service;

    @BeforeEach
    void setUp() {
        service = new HocLucService();
    }

    @Test
    @DisplayName("TC01 - [P4] Điểm 9.5 → 'Xuất sắc'")
    void testXuatSac_Vung() {
        assertEquals("Xuất sắc", service.xepLoaiHocLuc(9.5));
    }

    @Test
    @DisplayName("TC02 - [P3] Điểm 8.0 → 'Giỏi'")
    void testGioi_Vung() {
        assertEquals("Giỏi", service.xepLoaiHocLuc(8.0));
    }

    @Test
    @DisplayName("TC03 - [P2] Điểm 6.0 → 'Trung bình'")
    void testTrungBinh_Vung() {
        assertEquals("Trung bình", service.xepLoaiHocLuc(6.0));
    }

    @Test
    @DisplayName("TC04 - [P1] Điểm 3.0 → 'Yếu'")
    void testYeu_Vung() {
        assertEquals("Yếu", service.xepLoaiHocLuc(3.0));
    }

    @Test
    @DisplayName("TC05 - [Biên dưới P4] Điểm = 9.0 → 'Xuất sắc'")
    void testXuatSac_BienDuoi() {
        assertEquals("Xuất sắc", service.xepLoaiHocLuc(9.0));
    }

    @Test
    @DisplayName("TC06 - [Biên trên P4] Điểm = 10.0 → 'Xuất sắc'")
    void testXuatSac_BienTren() {
        assertEquals("Xuất sắc", service.xepLoaiHocLuc(10.0));
    }

    @Test
    @DisplayName("TC07 - [Biên trên P3] Điểm = 8.9 → 'Giỏi'")
    void testGioi_BienTren() {
        assertEquals("Giỏi", service.xepLoaiHocLuc(8.9));
    }

    @Test
    @DisplayName("TC08 - [Biên dưới P3] Điểm = 7.0 → 'Giỏi'")
    void testGioi_BienDuoi() {
        assertEquals("Giỏi", service.xepLoaiHocLuc(7.0));
    }

    @Test
    @DisplayName("TC09 - [Biên trên P2] Điểm = 6.9 → 'Trung bình'")
    void testTrungBinh_BienTren() {
        assertEquals("Trung bình", service.xepLoaiHocLuc(6.9));
    }

    @Test
    @DisplayName("TC10 - [Biên dưới P2] Điểm = 5.0 → 'Trung bình'")
    void testTrungBinh_BienDuoi() {
        assertEquals("Trung bình", service.xepLoaiHocLuc(5.0));
    }

    @Test
    @DisplayName("TC11 - [Biên trên P1] Điểm = 4.9 → 'Yếu'")
    void testYeu_BienTren() {
        assertEquals("Yếu", service.xepLoaiHocLuc(4.9));
    }

    @Test
    @DisplayName("TC12 - [Biên dưới P1] Điểm = 0.0 → 'Yếu'")
    void testYeu_BienDuoi() {
        assertEquals("Yếu", service.xepLoaiHocLuc(0.0));
    }

    // ======== NGOÀI VÙNG HỢP LỆ ========

    @Test
    @DisplayName("TC13 - Điểm âm (-1.0) → Exception")
    void testDiemAm_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> service.xepLoaiHocLuc(-1.0));
    }

    @Test
    @DisplayName("TC14 - Điểm > 10 (10.1) → Exception")
    void testDiemQua10_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> service.xepLoaiHocLuc(10.1));
    }
}