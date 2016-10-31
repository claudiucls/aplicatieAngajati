-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2016 at 10:13 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agrostroe`
--

-- --------------------------------------------------------

--
-- Table structure for table `angajat`
--

CREATE TABLE `angajat` (
  `id` bigint(20) NOT NULL,
  `cnp` varchar(13) DEFAULT NULL,
  `data_angajarii` date DEFAULT NULL,
  `data_nasterii` date DEFAULT NULL,
  `data_terminarii` date DEFAULT NULL,
  `nume` varchar(255) NOT NULL,
  `prenume` varchar(255) NOT NULL,
  `functia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `angajat`
--

INSERT INTO `angajat` (`id`, `cnp`, `data_angajarii`, `data_nasterii`, `data_terminarii`, `nume`, `prenume`, `functia`) VALUES
(1, '1510219211681', '2003-08-09', '1951-02-19', NULL, 'Stroe', 'Ion', 'Administrator'),
(2, '1801207525645', '2014-06-13', '1980-12-07', NULL, 'Paunescu', 'Constantin-Nicusor', 'Director Tehnic'),
(3, '1841209134141', '2015-01-04', '1984-12-09', NULL, 'Stroe', 'Claudiu-Florin', 'Director Comercial'),
(4, '2010101151512', '2009-02-05', '1901-01-01', NULL, 'Dober', 'Mirela', 'muncitor calificat'),
(5, '1010101010101', '2010-02-10', '1901-01-01', NULL, 'Gavrila', 'Gica-Cristian', 'Mecanizator'),
(8, '1010101010101', '2007-02-07', '1901-01-01', NULL, 'Cocarlea', 'Mirel', 'Mecanizator'),
(9, '1010101010101', '2007-01-31', '1901-01-01', NULL, 'Tigau', 'Costel', 'Mecanizator'),
(10, '1010101010101', '2010-01-14', '1901-01-01', NULL, 'Prunuta', 'Mihai', 'muncitor'),
(11, '2010101151512', '2009-07-15', '1901-01-01', NULL, 'Cocarlea', 'Mariana', 'muncitor'),
(12, '1010101010101', '2013-03-07', '1901-01-01', NULL, 'Ivan', 'Gheorghe', 'Muncitor');

-- --------------------------------------------------------

--
-- Table structure for table `calcul`
--

CREATE TABLE `calcul` (
  `id` bigint(20) NOT NULL,
  `luna_curenta` varchar(255) DEFAULT NULL,
  `persoane_intretinere` int(11) NOT NULL,
  `salariu_brut` double NOT NULL,
  `zile_lucrate` int(11) NOT NULL,
  `angajat_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calcul`
--

INSERT INTO `calcul` (`id`, `luna_curenta`, `persoane_intretinere`, `salariu_brut`, `zile_lucrate`, `angajat_id`) VALUES
(5, '2016-01', 0, 1100, 20, 4),
(6, '2016-01', 0, 2000, 20, 1),
(7, '2016-01', 0, 1100, 20, 5),
(8, '2016-01', 0, 1100, 20, 8),
(9, '2016-01', 0, 1100, 20, 9),
(10, '2016-01', 0, 2000, 20, 2),
(11, '2016-01', 0, 2000, 20, 3),
(12, '2016-02', 0, 2000, 21, 1),
(13, '2016-02', 0, 1100, 21, 4),
(14, '2016-02', 0, 1100, 21, 5),
(15, '2016-02', 0, 1100, 21, 8),
(16, '2016-02', 0, 1100, 21, 9),
(17, '2016-02', 0, 2000, 21, 2),
(18, '2016-02', 0, 2000, 21, 3),
(19, '2016-03', 0, 2000, 23, 1),
(20, '2016-03', 0, 1100, 23, 4),
(22, '2016-03', 0, 1100, 23, 5),
(23, '2016-03', 0, 1100, 23, 8),
(24, '2016-03', 0, 2000, 23, 2),
(25, '2016-03', 0, 2000, 23, 3),
(26, '2016-03', 0, 1100, 23, 12),
(27, '2016-03', 0, 1100, 23, 11),
(28, '2016-03', 0, 1100, 23, 9),
(29, '2016-03', 0, 1100, 23, 10);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_name` varchar(255) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_name`, `role_description`) VALUES
('ADMIN', 'Role Administrativ, poate accesa orice'),
('USER', 'Role de utilizator, poate accesa doar angajatii');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `actived` bit(1) NOT NULL,
  `password` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `actived`, `password`, `username`) VALUES
(1, b'1', 'admin', 'admin'),
(2, b'1', 'user', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_roleName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`users_id`, `roles_roleName`) VALUES
(2, 'USER'),
(1, 'USER'),
(1, 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `angajat`
--
ALTER TABLE `angajat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `calcul`
--
ALTER TABLE `calcul`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_8o8fbf1v5c9bem1h9cw40tar7` (`angajat_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FK_dicl6b09k1u3136jfsfxoowfg` (`roles_roleName`),
  ADD KEY `FK_3b2cl2u4ck187o21r4uhp6psv` (`users_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `angajat`
--
ALTER TABLE `angajat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `calcul`
--
ALTER TABLE `calcul`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `calcul`
--
ALTER TABLE `calcul`
  ADD CONSTRAINT `FK_8o8fbf1v5c9bem1h9cw40tar7` FOREIGN KEY (`angajat_id`) REFERENCES `angajat` (`id`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK_3b2cl2u4ck187o21r4uhp6psv` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_dicl6b09k1u3136jfsfxoowfg` FOREIGN KEY (`roles_roleName`) REFERENCES `roles` (`role_name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
