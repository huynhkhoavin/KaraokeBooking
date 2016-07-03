-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2016 at 01:04 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `karaoke_booking`
--

-- --------------------------------------------------------

--
-- Table structure for table `cua_hang`
--

CREATE TABLE `cua_hang` (
  `CH_ID` int(11) NOT NULL,
  `CH_TEN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `CH_DIA_CHI` varchar(20) NOT NULL,
  `TK_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cua_hang`
--

INSERT INTO `cua_hang` (`CH_ID`, `CH_TEN`, `CH_DIA_CHI`, `TK_ID`) VALUES
(1, 'Quán Karaoke Nhạc Sống', '222', 1);

-- --------------------------------------------------------

--
-- Table structure for table `danh_sach_dat_phong`
--

CREATE TABLE `danh_sach_dat_phong` (
  `TK_ID` int(11) NOT NULL,
  `PD_ID` int(11) NOT NULL,
  `GIO_BAT_DAU` datetime NOT NULL,
  `GIO_KET_THUC` datetime NOT NULL,
  `QR_STRING` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `danh_sach_dat_phong`
--

INSERT INTO `danh_sach_dat_phong` (`TK_ID`, `PD_ID`, `GIO_BAT_DAU`, `GIO_KET_THUC`, `QR_STRING`) VALUES
(1, 1, '2016-06-22 09:30:00', '2016-06-22 10:30:00', '1-1-UJAWFRMAF'),
(1, 2, '2016-06-22 06:30:00', '2016-06-22 09:00:00', '1-2-OJFAHAFPA');

-- --------------------------------------------------------

--
-- Table structure for table `dia_diem_yeu_thich`
--

CREATE TABLE `dia_diem_yeu_thich` (
  `TK_ID_KHACH_HANG` int(11) NOT NULL,
  `TK_ID_DIA_DIEM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `MN_ID` int(11) NOT NULL,
  `MN_TEN_MON` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `MN_GIA_TIEN` int(11) NOT NULL,
  `MN_LOAI_MON` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`MN_ID`, `MN_TEN_MON`, `MN_GIA_TIEN`, `MN_LOAI_MON`) VALUES
(1, 'Cơm Gà', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `menu_yeu_thich`
--

CREATE TABLE `menu_yeu_thich` (
  `TK_ID` int(11) NOT NULL,
  `MN_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phong_dat`
--

CREATE TABLE `phong_dat` (
  `PD_ID` int(11) NOT NULL,
  `PD_LOAI_PHONG` int(11) NOT NULL,
  `PD_TRANG_THAI` int(11) NOT NULL,
  `PD_GIO_BAT_DAU` time NOT NULL,
  `PD_GIO_KET_THUC` time NOT NULL,
  `PD_GIA_TIEN` int(11) NOT NULL,
  `CH_ID` int(11) NOT NULL,
  `PD_TEN` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `phong_dat`
--

INSERT INTO `phong_dat` (`PD_ID`, `PD_LOAI_PHONG`, `PD_TRANG_THAI`, `PD_GIO_BAT_DAU`, `PD_GIO_KET_THUC`, `PD_GIA_TIEN`, `CH_ID`, `PD_TEN`) VALUES
(1, 8, 5, '09:00:00', '00:00:00', 100, 1, 'Phòng 1'),
(2, 9, 5, '09:00:00', '00:00:00', 100, 1, 'Phòng 2'),
(3, 8, 7, '09:00:00', '00:00:00', 100, 1, 'Phòng 3'),
(4, 2, 0, '06:19:37', '00:00:00', 0, 1, 'Phòng 4');

-- --------------------------------------------------------

--
-- Table structure for table `quan_ly_cua_hang`
--

CREATE TABLE `quan_ly_cua_hang` (
  `TK_ID` int(11) NOT NULL,
  `CH_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE `tai_khoan` (
  `TK_ID` int(11) NOT NULL,
  `TK_USER` varchar(30) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TK_TEN_HIEN_THI` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TK_PASS` varchar(32) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TK_TRANG_THAI` int(11) NOT NULL,
  `TK_QUYEN_HAN` int(11) NOT NULL,
  `TK_DIA_DIEM` int(11) NOT NULL,
  `TK_GIA_TRUNG_BINH` int(11) NOT NULL,
  `TK_SO_DU` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`TK_ID`, `TK_USER`, `TK_TEN_HIEN_THI`, `TK_PASS`, `TK_TRANG_THAI`, `TK_QUYEN_HAN`, `TK_DIA_DIEM`, `TK_GIA_TRUNG_BINH`, `TK_SO_DU`) VALUES
(1, 'khoavin@gmail.com', 'Quán Karaoke B', '123456', 0, 3, 0, 0, 0),
(2, 'khoavin2@gmail.com', 'Huỳnh Khoa Vin', '123456', 0, 4, 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tham_so`
--

CREATE TABLE `tham_so` (
  `TS_ID` int(11) NOT NULL,
  `TS_NOI_DUNG` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TS_GIAI_THICH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tham_so`
--

INSERT INTO `tham_so` (`TS_ID`, `TS_NOI_DUNG`, `TS_GIAI_THICH`) VALUES
(1, 'TK_TRANG_THAI_CHUA_DANG_NHAP', 'Trạng thái tài khoản chưa đăng nhập'),
(2, 'TK_TRANG_THAI_DA_DANG_NHAP', 'Trạng thái tài khoản đã đăng nhập'),
(3, 'TK_QUYEN_HAN_ADMIN', 'Quyền hạn admin của tài khoản.'),
(4, 'TK_QUYEN_HAN_CLIENT', 'Quyền hạn client của tài khoản'),
(5, 'PD_TRANG_THAI_CO_SAN', 'Trạng thái phòng có sẵn.'),
(6, 'PD_TRANG_THAI_DA_DAT', 'Trạng thái phòng đã được đặt'),
(7, 'PD_TRANG_THAI_DANG_DUNG', 'Trạng thái phòng đang được sử dụng.'),
(8, 'PD_LOAI_PHONG_VIP', 'Phòng Vip'),
(9, 'PD_LOAI_PHONG_THUONG', 'Phòng Thường');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cua_hang`
--
ALTER TABLE `cua_hang`
  ADD PRIMARY KEY (`CH_ID`);

--
-- Indexes for table `danh_sach_dat_phong`
--
ALTER TABLE `danh_sach_dat_phong`
  ADD PRIMARY KEY (`TK_ID`,`PD_ID`),
  ADD KEY `danh_sach_dat_phong_ibfk_1` (`PD_ID`);

--
-- Indexes for table `dia_diem_yeu_thich`
--
ALTER TABLE `dia_diem_yeu_thich`
  ADD PRIMARY KEY (`TK_ID_KHACH_HANG`,`TK_ID_DIA_DIEM`),
  ADD KEY `dia_diem_yeu_thich_ibfk_2` (`TK_ID_DIA_DIEM`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`MN_ID`);

--
-- Indexes for table `menu_yeu_thich`
--
ALTER TABLE `menu_yeu_thich`
  ADD PRIMARY KEY (`TK_ID`,`MN_ID`),
  ADD KEY `menu_yeu_thich_ibfk_2` (`MN_ID`);

--
-- Indexes for table `phong_dat`
--
ALTER TABLE `phong_dat`
  ADD PRIMARY KEY (`PD_ID`);

--
-- Indexes for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD PRIMARY KEY (`TK_ID`);

--
-- Indexes for table `tham_so`
--
ALTER TABLE `tham_so`
  ADD PRIMARY KEY (`TS_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cua_hang`
--
ALTER TABLE `cua_hang`
  MODIFY `CH_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `MN_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `phong_dat`
--
ALTER TABLE `phong_dat`
  MODIFY `PD_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  MODIFY `TK_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tham_so`
--
ALTER TABLE `tham_so`
  MODIFY `TS_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `danh_sach_dat_phong`
--
ALTER TABLE `danh_sach_dat_phong`
  ADD CONSTRAINT `danh_sach_dat_phong_ibfk_1` FOREIGN KEY (`PD_ID`) REFERENCES `phong_dat` (`PD_ID`),
  ADD CONSTRAINT `danh_sach_dat_phong_ibfk_2` FOREIGN KEY (`TK_ID`) REFERENCES `tai_khoan` (`TK_ID`);

--
-- Constraints for table `dia_diem_yeu_thich`
--
ALTER TABLE `dia_diem_yeu_thich`
  ADD CONSTRAINT `dia_diem_yeu_thich_ibfk_1` FOREIGN KEY (`TK_ID_KHACH_HANG`) REFERENCES `tai_khoan` (`TK_ID`),
  ADD CONSTRAINT `dia_diem_yeu_thich_ibfk_2` FOREIGN KEY (`TK_ID_DIA_DIEM`) REFERENCES `tai_khoan` (`TK_ID`);

--
-- Constraints for table `menu_yeu_thich`
--
ALTER TABLE `menu_yeu_thich`
  ADD CONSTRAINT `menu_yeu_thich_ibfk_1` FOREIGN KEY (`TK_ID`) REFERENCES `tai_khoan` (`TK_ID`),
  ADD CONSTRAINT `menu_yeu_thich_ibfk_2` FOREIGN KEY (`MN_ID`) REFERENCES `menu` (`MN_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
