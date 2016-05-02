-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2016 at 06:22 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `karaoke_booking`
--

-- --------------------------------------------------------

--
-- Table structure for table `bai_hat`
--

CREATE TABLE IF NOT EXISTS `bai_hat` (
  `BH_ID` int(11) NOT NULL,
  `BH_TIEU_DE` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `BH_LOI_BAI_HAT` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `BH_TAC_GIA` int(11) NOT NULL,
  `BH_MA_SO` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `BH_CA_SI` int(11) NOT NULL,
  `BH_THE_LOAI` int(11) NOT NULL,
  `BH_QUOC_GIA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bh_yeu_thich`
--

CREATE TABLE IF NOT EXISTS `bh_yeu_thich` (
  `TK_ID` int(11) NOT NULL,
  `BH_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `danh_sach_dat_phong`
--

CREATE TABLE IF NOT EXISTS `danh_sach_dat_phong` (
  `TK_ID` int(11) NOT NULL,
  `PD_ID` int(11) NOT NULL,
  `GIO_BAT_DAU` datetime NOT NULL,
  `GIO_KET_THUC` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dia_diem_yeu_thich`
--

CREATE TABLE IF NOT EXISTS `dia_diem_yeu_thich` (
  `TK_ID_KHACH_HANG` int(11) NOT NULL,
  `TK_ID_DIA_DIEM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `MN_ID` int(11) NOT NULL,
  `MN_TEN_MON` varchar(50) NOT NULL,
  `MN_GIA_TIEN` int(11) NOT NULL,
  `MN_LOAI_MON` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `menu_yeu_thich`
--

CREATE TABLE IF NOT EXISTS `menu_yeu_thich` (
  `TK_ID` int(11) NOT NULL,
  `MN_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nguoi`
--

CREATE TABLE IF NOT EXISTS `nguoi` (
  `NG_ID` int(11) NOT NULL,
  `NG_TEN` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `NG_NGHE_NGHIEP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phong_dat`
--

CREATE TABLE IF NOT EXISTS `phong_dat` (
  `PD_ID` int(11) NOT NULL,
  `PD_TEN_PHONG` varchar(11) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `PD_LOAI_PHONG` int(11) NOT NULL,
  `PD_TRANG_THAI` int(11) NOT NULL,
  `PD_GIO_BAT_DAU` datetime NOT NULL,
  `PD_GIO_KET_THUC` datetime NOT NULL,
  `PD_GIA_TIEN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE IF NOT EXISTS `tai_khoan` (
  `TK_ID` int(11) NOT NULL,
  `TK_USER` varchar(30) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TK_PASS` varchar(32) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TK_TRANG_THAI` int(11) NOT NULL,
  `TK_QUYEN_HAN` int(11) NOT NULL,
  `TK_DIA_DIEM` int(11) NOT NULL,
  `TK_SO_DU` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`TK_ID`, `TK_USER`, `TK_PASS`, `TK_TRANG_THAI`, `TK_QUYEN_HAN`, `TK_DIA_DIEM`, `TK_SO_DU`) VALUES
(1, 'khoavin@gmail.com', '123456', 0, 0, 0, 0),
(2, 'khoavin@gmail.com', '123456', 1, 1, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bai_hat`
--
ALTER TABLE `bai_hat`
  ADD PRIMARY KEY (`BH_ID`), ADD KEY `BH_TAC_GIA` (`BH_TAC_GIA`), ADD KEY `BH_CA_SI` (`BH_CA_SI`);

--
-- Indexes for table `bh_yeu_thich`
--
ALTER TABLE `bh_yeu_thich`
  ADD PRIMARY KEY (`TK_ID`,`BH_ID`), ADD KEY `bh_yeu_thich_ibfk_2` (`BH_ID`);

--
-- Indexes for table `danh_sach_dat_phong`
--
ALTER TABLE `danh_sach_dat_phong`
  ADD PRIMARY KEY (`TK_ID`,`PD_ID`), ADD KEY `danh_sach_dat_phong_ibfk_1` (`PD_ID`);

--
-- Indexes for table `dia_diem_yeu_thich`
--
ALTER TABLE `dia_diem_yeu_thich`
  ADD PRIMARY KEY (`TK_ID_KHACH_HANG`,`TK_ID_DIA_DIEM`), ADD KEY `dia_diem_yeu_thich_ibfk_2` (`TK_ID_DIA_DIEM`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`MN_ID`);

--
-- Indexes for table `menu_yeu_thich`
--
ALTER TABLE `menu_yeu_thich`
  ADD PRIMARY KEY (`TK_ID`,`MN_ID`), ADD KEY `menu_yeu_thich_ibfk_2` (`MN_ID`);

--
-- Indexes for table `nguoi`
--
ALTER TABLE `nguoi`
  ADD PRIMARY KEY (`NG_ID`);

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bai_hat`
--
ALTER TABLE `bai_hat`
  MODIFY `BH_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `MN_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `nguoi`
--
ALTER TABLE `nguoi`
  MODIFY `NG_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `phong_dat`
--
ALTER TABLE `phong_dat`
  MODIFY `PD_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  MODIFY `TK_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bai_hat`
--
ALTER TABLE `bai_hat`
ADD CONSTRAINT `bai_hat_ibfk_1` FOREIGN KEY (`BH_TAC_GIA`) REFERENCES `nguoi` (`NG_ID`),
ADD CONSTRAINT `bai_hat_ibfk_2` FOREIGN KEY (`BH_CA_SI`) REFERENCES `nguoi` (`NG_ID`);

--
-- Constraints for table `bh_yeu_thich`
--
ALTER TABLE `bh_yeu_thich`
ADD CONSTRAINT `bh_yeu_thich_ibfk_1` FOREIGN KEY (`TK_ID`) REFERENCES `tai_khoan` (`TK_ID`),
ADD CONSTRAINT `bh_yeu_thich_ibfk_2` FOREIGN KEY (`BH_ID`) REFERENCES `bai_hat` (`BH_ID`);

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
