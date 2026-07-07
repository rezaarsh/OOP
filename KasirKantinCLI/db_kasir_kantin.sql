-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2026 at 04:48 PM
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
-- Database: `db_kasir_kantin`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `tambah_transaksi` (IN `p_id_produk` INT, IN `p_id_pelanggan` INT, IN `p_jumlah` INT, IN `p_total_harga` DECIMAL(10,2))   INSERT INTO transaksi
(
    id_produk,
    id_pelanggan,
    jumlah,
    total_harga
)
VALUES
(
    p_id_produk,
    p_id_pelanggan,
    p_jumlah,
    p_total_harga
)$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `hitung_total_penjualan` () RETURNS DECIMAL(10,2) READS SQL DATA RETURN (
    SELECT IFNULL(SUM(total_harga),0)
    FROM transaksi
)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `no_hp`) VALUES
(1, 'Andi', '081234567890'),
(2, 'Budi', '082112223333'),
(3, 'Citra', '085678901234'),
(4, 'Reza', '085793720089'),
(5, 'Boboiboy', '08588888888'),
(6, 'Ariansah', '085777777777'),
(7, 'Prabowo', '081234567890');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int(11) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `harga`, `stok`) VALUES
(1, 'Nasi Goreng', 15000.00, 13),
(2, 'Mie Ayam', 12000.00, 13),
(3, 'Es Teh', 5000.00, 29),
(4, 'Kopi Hitam', 8000.00, 10),
(5, 'Awug', 15000.00, 10),
(6, 'Martabak Manis', 17000.00, 7),
(7, 'Mie Ayam', 10000.00, 9),
(8, 'Kopi', 10000.00, 9),
(9, 'Donat', 5000.00, 10);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_produk` int(11) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total_harga` decimal(10,2) NOT NULL,
  `tanggal` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_produk`, `id_pelanggan`, `jumlah`, `total_harga`, `tanggal`) VALUES
(1, 1, 1, 2, 30000.00, '2026-07-06 17:45:30'),
(2, 1, 1, 2, 30000.00, '2026-07-06 17:52:33'),
(3, 1, 1, 2, 30000.00, '2026-07-06 20:05:14'),
(4, 1, 1, 2, 30000.00, '2026-07-06 20:16:44'),
(5, 2, 2, 2, 24000.00, '2026-07-06 20:17:15'),
(6, 3, 3, 1, 5000.00, '2026-07-06 20:18:16'),
(7, 1, 1, 1, 15000.00, '2026-07-06 20:18:39'),
(8, 6, 4, 2, 34000.00, '2026-07-06 20:58:38'),
(9, 6, 3, 1, 17000.00, '2026-07-06 20:59:26'),
(10, 7, 5, 1, 10000.00, '2026-07-06 21:13:16'),
(11, 8, 6, 1, 10000.00, '2026-07-06 21:27:06'),
(12, 9, 7, 10, 50000.00, '2026-07-06 21:35:07');

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `kurangi_stok` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
    UPDATE produk
    SET stok = stok - NEW.jumlah
    WHERE id_produk = NEW.id_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_transaksi`
-- (See below for the actual view)
--
CREATE TABLE `v_transaksi` (
`id_transaksi` int(11)
,`nama_produk` varchar(100)
,`nama_pelanggan` varchar(100)
,`jumlah` int(11)
,`total_harga` decimal(10,2)
,`tanggal` datetime
);

-- --------------------------------------------------------

--
-- Structure for view `v_transaksi`
--
DROP TABLE IF EXISTS `v_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_transaksi`  AS SELECT `t`.`id_transaksi` AS `id_transaksi`, `p`.`nama_produk` AS `nama_produk`, `pl`.`nama` AS `nama_pelanggan`, `t`.`jumlah` AS `jumlah`, `t`.`total_harga` AS `total_harga`, `t`.`tanggal` AS `tanggal` FROM ((`transaksi` `t` join `produk` `p` on(`t`.`id_produk` = `p`.`id_produk`)) join `pelanggan` `pl` on(`t`.`id_pelanggan` = `pl`.`id_pelanggan`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_produk` (`id_produk`),
  ADD KEY `fk_pelanggan` (`id_pelanggan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `fk_produk` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
