-- phpMyAdmin SQL Dump
-- version 5.2.1-dev+20221207.5a6b5e731f
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2023 at 03:03 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `acp_semester_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `Id` int(11) NOT NULL,
  `SenderIP` varchar(20) NOT NULL,
  `ReciverIp` varchar(20) NOT NULL,
  `Message` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`Id`, `SenderIP`, `ReciverIp`, `Message`) VALUES
(1, '192.168.1.121', '192.168.1.121', 'Assalamoalaikum'),
(2, '192.168.1.121', '192.168.1.121', 'walaikumsalam'),
(3, '192.168.1.121', '192.168.1.121', 'ksa ho'),
(4, '192.168.1.121', '192.168.1.121', 'ma theik'),
(5, '192.168.1.121', '192.168.1.121', '???'),
(6, '192.168.1.121', '192.168.1.121', 'hiii'),
(7, '192.168.1.121', '192.168.1.121', '???????'),
(8, '192.168.1.121', '192.168.1.1', 'hi'),
(9, '192.168.1.121', '192.168.1.1', 'ksa ho'),
(10, '192.168.1.121', '192.168.1.255', '?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????'),
(11, '192.168.1.121', '192.168.1.121', 'salam'),
(12, '192.168.1.121', '192.168.1.121', 'salam'),
(13, '192.168.1.121', '192.168.1.1', 'wsalam'),
(14, '192.168.1.121', '192.168.1.121', '..'),
(15, '192.168.1.121', '192.168.1.121', '..'),
(16, '', '192.168.1.121', 'hi'),
(17, '', '192.168.1.121', 'ksa ho'),
(18, '192.168.1.121', '192.168.1.121', 'Bismillah'),
(19, '192.168.1.121', '192.168.1.121', 'Bismillah'),
(20, '192.168.1.121', '192.168.1.1', '?????????'),
(21, '192.168.1.121', '192.168.1.103', 'hi .103'),
(22, '192.168.1.121', '192.168.1.1', '.......'),
(23, '192.168.1.121', '192.168.1.121', 'heloooooooooooooooooooooo'),
(24, '192.168.1.121', '', ''),
(25, '192.168.1.121', '', ''),
(26, '192.168.1.121', '', ''),
(27, '192.168.1.121', '192.168.1.121', 'helo'),
(28, '192.168.1.121', '192.168.1.121', 'helo'),
(29, '192.168.1.121', '192.168.1.121', 'Bismillah'),
(30, '192.168.1.121', '192.168.1.121', '...................'),
(31, '192.168.1.121', '192.168.1.121', '??????????'),
(32, '192.168.1.121', '192.168.1.121', ''),
(33, '192.168.1.121', '192.168.1.121', 'hiiiiiiiiiiiii'),
(34, '192.168.1.121', '192.168.1.121', 'hiiiiiiiiiiiii'),
(35, '192.168.1.121', '192.168.1.121', 'heloo'),
(36, '192.168.1.121', '192.168.1.121', 'heloo'),
(37, '192.168.1.121', '192.168.1.121', 'heloo'),
(38, '192.168.1.121', '192.168.1.121', 'je'),
(39, '192.168.1.121', '192.168.1.121', 'je'),
(40, '192.168.1.121', '192.168.1.121', 'whatup'),
(41, '192.168.1.121', '192.168.1.121', 'whatup'),
(42, '192.168.1.121', '192.168.1.121', 'whatup'),
(43, '192.168.1.121', '192.168.1.121', '?'),
(44, '192.168.1.121', '192.168.1.121', '?'),
(45, '192.168.1.121', '192.168.1.121', '?'),
(46, '192.168.1.121', '', 'jsdnd'),
(47, '192.168.1.121', '192.168.1.121', 'hdh'),
(48, '192.168.1.121', '192.168.1.121', 'hdh');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Id`, `Name`, `Email`, `Password`) VALUES
(1, 'Muhammad Ahmad', 'm', '1'),
(2, 'Muhammad Ahmad', 'a@gmail.com', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
