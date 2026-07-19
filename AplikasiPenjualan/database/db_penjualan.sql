-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2026 at 12:56 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_penjualan`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buat_transaksi_baru` (IN `p_no_faktur` VARCHAR(20), IN `p_id_user` INT, IN `p_id_pelanggan` INT, IN `p_total_harga` DECIMAL(15,2))   BEGIN
    INSERT INTO transaksi (no_faktur, id_user, id_pelanggan, total_harga) 
    VALUES (p_no_faktur, p_id_user, p_id_pelanggan, p_total_harga);
    
    SELECT LAST_INSERT_ID() AS id_transaksi;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_pendapatan_hari_ini` (`tgl` DATE) RETURNS DECIMAL(15,2) DETERMINISTIC BEGIN
    DECLARE total DECIMAL(15,2);
    SELECT SUM(total_harga) INTO total 
    FROM transaksi 
    WHERE DATE(tanggal) = tgl;
    RETURN IFNULL(total, 0);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `kode_barang`, `nama_barang`, `harga`, `stok`) VALUES
(2, 'B001', 'Mie Ayam', 10000.00, 15),
(3, 'B002', 'Martabak', 20000.00, 9),
(4, 'B003', 'Es Teh', 5000.00, 8);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `harga_satuan` decimal(10,2) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail`, `id_transaksi`, `id_barang`, `harga_satuan`, `jumlah`, `subtotal`) VALUES
(1, 2, 2, 10000.00, 2, 20000.00),
(2, 3, 2, 10000.00, 3, 30000.00),
(3, 4, 3, 20000.00, 1, 20000.00),
(4, 5, 4, 5000.00, 2, 10000.00);

--
-- Triggers `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `tr_kurangi_stok` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
    UPDATE barang 
    SET stok = stok - NEW.jumlah 
    WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `alamat` text DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat`, `no_telp`) VALUES
(2, 'Reza', 'Sukabumi', '0857'),
(3, 'Yana', 'Nagrak', '0812'),
(4, 'Bagas', 'Benteng', '0888');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `no_faktur` varchar(20) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `tanggal` datetime DEFAULT current_timestamp(),
  `total_harga` decimal(15,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `no_faktur`, `id_user`, `id_pelanggan`, `tanggal`, `total_harga`) VALUES
(2, 'INV-1784438530637', 1, 2, '2026-07-19 12:22:37', 20000.00),
(3, 'INV-1784457021420', 1, 2, '2026-07-19 17:31:11', 30000.00),
(4, 'INV-1784457143966', 1, 3, '2026-07-19 17:33:03', 20000.00),
(5, 'INV-1784458413979', 1, 4, '2026-07-19 17:53:51', 10000.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('Admin','Kasir') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin123', 'Admin');

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_laporan_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `v_laporan_penjualan` (
`no_faktur` varchar(20)
,`tanggal` datetime
,`nama_pelanggan` varchar(100)
,`nama_barang` varchar(100)
,`harga_satuan` decimal(10,2)
,`jumlah` int(11)
,`subtotal` decimal(15,2)
,`nama_kasir` varchar(50)
);

-- --------------------------------------------------------

--
-- Structure for view `v_laporan_penjualan`
--
DROP TABLE IF EXISTS `v_laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_laporan_penjualan`  AS SELECT `t`.`no_faktur` AS `no_faktur`, `t`.`tanggal` AS `tanggal`, `p`.`nama_pelanggan` AS `nama_pelanggan`, `b`.`nama_barang` AS `nama_barang`, `dt`.`harga_satuan` AS `harga_satuan`, `dt`.`jumlah` AS `jumlah`, `dt`.`subtotal` AS `subtotal`, `u`.`username` AS `nama_kasir` FROM ((((`transaksi` `t` left join `pelanggan` `p` on(`t`.`id_pelanggan` = `p`.`id_pelanggan`)) join `detail_transaksi` `dt` on(`t`.`id_transaksi` = `dt`.`id_transaksi`)) join `barang` `b` on(`dt`.`id_barang` = `b`.`id_barang`)) join `users` `u` on(`t`.`id_user` = `u`.`id_user`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD UNIQUE KEY `kode_barang` (`kode_barang`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD UNIQUE KEY `no_faktur` (`no_faktur`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
