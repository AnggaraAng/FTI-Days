-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2019 at 08:40 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ftidays`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin` varchar(5) DEFAULT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_pendaftar`
--

CREATE TABLE `data_pendaftar` (
  `nomor_pendaftaran` int(11) NOT NULL,
  `nim` varchar(9) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `progdi` varchar(20) NOT NULL,
  `jenis_kelamin` char(1) NOT NULL,
  `agama` varchar(12) DEFAULT NULL,
  `nomor_hp` varchar(12) DEFAULT NULL,
  `riwayat_penyakit` text NOT NULL,
  `ukuran_baju` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_pendaftar`
--

INSERT INTO `data_pendaftar` (`nomor_pendaftaran`, `nim`, `nama`, `progdi`, `jenis_kelamin`, `agama`, `nomor_hp`, `riwayat_penyakit`, `ukuran_baju`) VALUES
(1, '672018222', 'Kadek Widiana', 'TEKNIK INFORMATIKA', 'L', 'KRISTEN', '123', '', 'M'),
(6, '672018306', 'Bagong', 'TEKNIK INFORMATIKA', 'L', 'ISLAM', '123', '', 'M'),
(5, '672018338', 'Dandy', 'TEKNIK INFORMATIKA', 'L', 'Lain - lain', '123', '', 'M'),
(3, '682018000', 'GG', 'SISTEM INFORMASI', 'L', 'KONG HU CU', '123', '', 'L'),
(2, '702018006', 'Mr. Manuel', 'PTIK', 'L', 'Lain - lain', '123', '', 'M'),
(4, '702020123', 'MBOH', 'PTIK', 'P', 'HINDU', '123', '', 'L');

-- --------------------------------------------------------

--
-- Table structure for table `kelompok`
--

CREATE TABLE `kelompok` (
  `nomor_kelompok` int(11) NOT NULL,
  `nama_kelompok` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelompok`
--

INSERT INTO `kelompok` (`nomor_kelompok`, `nama_kelompok`) VALUES
(1, 'AIRI'),
(2, 'BALDUM'),
(3, 'LINDIS'),
(4, 'MURAD'),
(5, 'KAHLII');

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran`
--

CREATE TABLE `pendaftaran` (
  `nomor_pendaftaran` int(11) NOT NULL,
  `tagihan` varchar(7) NOT NULL,
  `kel_berkas` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pendaftaran`
--

INSERT INTO `pendaftaran` (`nomor_pendaftaran`, `tagihan`, `kel_berkas`) VALUES
(1, 'LUNAS', 'KST, KTM, FOTO'),
(2, 'LUNAS', 'KST, KTM, FOTO'),
(3, 'LUNAS', 'KST, KTM, FOTO'),
(4, 'LUNAS', 'KST, KTM, FOTO'),
(5, 'LUNAS', 'KST, KTM, FOTO'),
(6, 'LUNAS', 'KST, KTM, FOTO');

-- --------------------------------------------------------

--
-- Table structure for table `peserta`
--

CREATE TABLE `peserta` (
  `nim` varchar(9) NOT NULL,
  `nomor_kelompok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peserta`
--

INSERT INTO `peserta` (`nim`, `nomor_kelompok`) VALUES
('672018222', 1),
('702018006', 2),
('682018000', 3),
('702020123', 4),
('672018338', 5),
('672018306', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_pendaftar`
--
ALTER TABLE `data_pendaftar`
  ADD PRIMARY KEY (`nim`),
  ADD KEY `fk_nomor_pendaftaran` (`nomor_pendaftaran`);

--
-- Indexes for table `kelompok`
--
ALTER TABLE `kelompok`
  ADD PRIMARY KEY (`nomor_kelompok`);

--
-- Indexes for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  ADD PRIMARY KEY (`nomor_pendaftaran`);

--
-- Indexes for table `peserta`
--
ALTER TABLE `peserta`
  ADD KEY `fk_nim` (`nim`),
  ADD KEY `fk_kelompok` (`nomor_kelompok`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  MODIFY `nomor_pendaftaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_pendaftar`
--
ALTER TABLE `data_pendaftar`
  ADD CONSTRAINT `fk_nomor_pendaftaran` FOREIGN KEY (`nomor_pendaftaran`) REFERENCES `pendaftaran` (`nomor_pendaftaran`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `peserta`
--
ALTER TABLE `peserta`
  ADD CONSTRAINT `fk_kelompok` FOREIGN KEY (`nomor_kelompok`) REFERENCES `kelompok` (`nomor_kelompok`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_nim` FOREIGN KEY (`nim`) REFERENCES `data_pendaftar` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
