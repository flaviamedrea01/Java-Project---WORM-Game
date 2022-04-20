-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 18, 2022 at 04:36 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `worm`
--

-- --------------------------------------------------------

--
-- Table structure for table `score_db`
--

CREATE TABLE `score_db` (
  `id` int(11) NOT NULL,
  `player` varchar(255) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `score_db`
--

INSERT INTO `score_db` (`id`, `player`, `score`) VALUES
(2, 'Maria', -15),
(3, 'Andrew', 0),
(4, 'Paula', 7),
(5, 'Test', 0),
(6, 'Flavia', -4),
(7, 'Flavia', 0),
(8, 'Hey', 0),
(9, 'Marius', 2),
(10, 'Flavia', -3),
(11, 'Flavia', -3),
(12, 'sdfgvhbn', -4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `score_db`
--
ALTER TABLE `score_db`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `score_db`
--
ALTER TABLE `score_db`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
