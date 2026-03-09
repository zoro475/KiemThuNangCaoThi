package com.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VietJetAirSeleniumTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static JavascriptExecutor js;

    private static final String URL       = "https://www.vietjetair.com/vi";
    private static final String DIEM_DI   = "Ho Chi Minh";
    private static final String MA_DI     = "SGN";
    private static final String DIEM_DEN  = "Ha Noi";
    private static final String MA_DEN    = "HAN";
    private static final String NGAY_DI   = "20/07/2025";
    private static final String NGAY_VE   = "25/07/2025";
    private static final int    NGUOI_LON = 2;
    private static final int    TRE_EM    = 1;
    private static final int    EM_BE     = 0;

    public static void main(String[] args) throws InterruptedException {
        setUp();
        try {
            buoc1_MoTrang();
            buoc2_DongTatCaPopup();
            buoc3_ChonKhuHoi();
            buoc4_NhapDiemDi();
            buoc5_NhapDiemDen();
            buoc6_NhapNgayDi();
            buoc7_NhapNgayVe();
            buoc8_NhapHanhKhach();
            buoc9_TimKiem();
            System.out.println("Hoan thanh!");
        } catch (Exception e) {
            System.err.println("Loi: " + e.getMessage());
        } finally {
            Thread.sleep(4000);
            tearDown();
        }
    }

    static void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty("webdriver.chrome.silentOutput", "true");

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        opt.addArguments("--disable-notifications");
        opt.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(opt);
        wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
        js     = (JavascriptExecutor) driver;
        System.out.println("Chrome khoi dong xong");
    }

    static void tearDown() {
        if (driver != null) driver.quit();
        System.out.println("Dong trinh duyet");
    }

    static void buoc1_MoTrang() throws InterruptedException {
        driver.get(URL);
        Thread.sleep(3000);
        System.out.println("Mo: " + driver.getCurrentUrl());
    }

    static void buoc2_DongTatCaPopup() throws InterruptedException {
        Thread.sleep(1500);
        try {
            List<WebElement> closeButtons = driver.findElements(
                    By.xpath("//div[contains(@class,'MuiDialog')]" +
                            "//button[contains(@class,'MuiIconButton') or @aria-label='close']"));
            for (WebElement btn : closeButtons) {
                jsClick(btn);
                Thread.sleep(500);
            }
        } catch (Exception ignored) {}

        try {
            WebElement backdrop = driver.findElement(
                    By.cssSelector(".MuiBackdrop-root, .MuiDialog-backdrop"));
            jsClick(backdrop);
            Thread.sleep(500);
        } catch (Exception ignored) {}

        try {
            WebElement cookieOk = driver.findElement(
                    By.xpath("//button[contains(text(),'Dong y') or contains(text(),'Accept') " +
                            "or contains(text(),'OK')]"));
            jsClick(cookieOk);
            Thread.sleep(500);
        } catch (Exception ignored) {}

        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        Thread.sleep(800);
    }

    static void buoc3_ChonKhuHoi() throws InterruptedException {
        Thread.sleep(500);
        try {
            WebElement khuHoi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[normalize-space(.)='Khu hoi' and " +
                            "(self::label or self::span or self::button or self::div)]")));
            jsClick(khuHoi);
            System.out.println("Chon: Khu hoi");
        } catch (Exception e) {
            System.out.println("Khong tim thay nut Khu hoi: " + e.getMessage());
        }
    }

    static void buoc4_NhapDiemDi() throws InterruptedException {
        buoc2_DongTatCaPopup();
        try {
            WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiFormControl')]" +
                            "[.//label[contains(text(),'Diem di')]])[1]")));
            jsClick(wrapper);
            Thread.sleep(1200);

            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@id[contains(.,'input')] and not(@disabled)][1]")));
            input.clear();
            input.sendKeys(DIEM_DI);
            System.out.println("Diem di: " + DIEM_DI);
            Thread.sleep(1500);

            chonGoiY(DIEM_DI, MA_DI);
        } catch (Exception e) {
            System.out.println("Loi diem di: " + e.getMessage());
        }
    }

    static void buoc5_NhapDiemDen() throws InterruptedException {
        Thread.sleep(500);
        try {
            WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiFormControl')]" +
                            "[.//label[contains(text(),'Diem den')]])[1]")));
            jsClick(wrapper);
            Thread.sleep(1200);

            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@id[contains(.,'input')] and not(@disabled)][1]")));
            input.clear();
            input.sendKeys(DIEM_DEN);
            System.out.println("Diem den: " + DIEM_DEN);
            Thread.sleep(1500);

            chonGoiY(DIEM_DEN, MA_DEN);
        } catch (Exception e) {
            System.out.println("Loi diem den: " + e.getMessage());
        }
    }

    static void buoc6_NhapNgayDi() throws InterruptedException {
        Thread.sleep(500);
        try {
            WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiFormControl')]" +
                            "[.//label[contains(text(),'Ngay di')]])[1]")));
            jsClick(wrapper);
            Thread.sleep(1500);

            chonNgayTrenMuiPicker(NGAY_DI);
            System.out.println("Ngay di: " + NGAY_DI);
        } catch (Exception e) {
            System.out.println("Loi ngay di: " + e.getMessage());
        }
    }

    static void buoc7_NhapNgayVe() throws InterruptedException {
        Thread.sleep(500);
        try {
            WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiFormControl')]" +
                            "[.//label[contains(text(),'Ngay ve')]])[1]")));
            jsClick(wrapper);
            Thread.sleep(1500);

            chonNgayTrenMuiPicker(NGAY_VE);
            System.out.println("Ngay ve: " + NGAY_VE);

            try {
                WebElement ok = driver.findElement(
                        By.xpath("//button[contains(text(),'OK') or contains(text(),'Xong')]"));
                jsClick(ok);
            } catch (Exception ignored) {}
        } catch (Exception e) {
            System.out.println("Loi ngay ve: " + e.getMessage());
        }
    }

    static void buoc8_NhapHanhKhach() throws InterruptedException {
        buoc2_DongTatCaPopup();
        Thread.sleep(500);
        try {
            WebElement hkWrapper = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiFormControl')]" +
                            "[.//label[contains(text(),'Hanh khach') or " +
                            "contains(text(),'Nguoi lon') or contains(text(),'Passenger')]])[1]")));
            jsClick(hkWrapper);
            System.out.println("Mo hanh khach");
            Thread.sleep(1500);

            bam_NutTang(0, NGUOI_LON - 1, "Nguoi lon");
            bam_NutTang(1, TRE_EM, "Tre em");
            bam_NutTang(2, EM_BE, "Em be");

            try {
                WebElement done = driver.findElement(
                        By.xpath("//button[contains(text(),'Xong') or contains(text(),'Done') " +
                                "or contains(text(),'Ap dung')]"));
                jsClick(done);
            } catch (Exception ignored) {}

            System.out.println("Hanh khach: " + NGUOI_LON + " NL / " + TRE_EM + " TE / " + EM_BE + " EB");
        } catch (Exception e) {
            System.out.println("Loi hanh khach: " + e.getMessage());
        }
    }

    static void buoc9_TimKiem() throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement searchBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(@class,'MuiButton')][" +
                            "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝĂĐƠƯ'," +
                            "'abcdefghijklmnopqrstuvwxyzàáâãèéêìíòóôõùúýăđơư'),'tim kiem') or " +
                            "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz'),'search')]")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", searchBtn);
            Thread.sleep(500);
            jsClick(searchBtn);
            System.out.println("Da bam Tim kiem");
            Thread.sleep(4000);
            System.out.println("Ket qua: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Loi tim kiem: " + e.getMessage());
        }
    }

    static void jsClick(WebElement el) {
        js.executeScript("arguments[0].click();", el);
    }

    static void chonGoiY(String ten, String ma) throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement item = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@role='option' or contains(@class,'MuiListItem') " +
                            "or contains(@class,'suggestion') or contains(@class,'jss')]" +
                            "[contains(.,'" + ten + "') or contains(.,'" + ma + "')]")));
            jsClick(item);
            System.out.println("Chon goi y: " + ten + " (" + ma + ")");
        } catch (Exception e) {
            try {
                driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
                System.out.println("Chon bang ENTER: " + ten);
            } catch (Exception e2) {
                System.out.println("Khong tim thay goi y: " + ten);
            }
        }
    }

    static void chonNgayTrenMuiPicker(String ngayDDMMYYYY) throws InterruptedException {
        String ngay = String.valueOf(Integer.parseInt(ngayDDMMYYYY.split("/")[0]));
        try {
            WebElement cell = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'MuiPickersDay') or " +
                            "contains(@class,'MuiPickersCalendarDay')]" +
                            "[not(@disabled)][normalize-space(.)='" + ngay + "']")));
            jsClick(cell);
        } catch (Exception e) {
            WebElement cell = driver.findElement(
                    By.xpath("//div[contains(@class,'day') and not(contains(@class,'disabled'))]" +
                            "[normalize-space(text())='" + ngay + "']"));
            jsClick(cell);
        }
    }

    static void bam_NutTang(int index, int soLan, String label) throws InterruptedException {
        for (int i = 0; i < soLan; i++) {
            try {
                List<WebElement> plusBtns = driver.findElements(
                        By.xpath("//button[@aria-label='increase' or " +
                                "contains(@class,'increment') or " +
                                ".//span[normalize-space(text())='+']]"));
                if (index < plusBtns.size()) {
                    jsClick(plusBtns.get(index));
                    Thread.sleep(400);
                }
            } catch (Exception e) {
                System.out.println("Khong tim thay nut + cho " + label);
            }
        }
        if (soLan > 0) System.out.println("Tang " + label + " +" + soLan);
    }
}