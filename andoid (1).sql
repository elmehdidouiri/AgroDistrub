-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 26, 2024 at 05:44 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `andoid`
--

-- --------------------------------------------------------

--
-- Table structure for table `camion`
--

DROP TABLE IF EXISTS `camion`;
CREATE TABLE IF NOT EXISTS `camion` (
  `matricule` int NOT NULL,
  `chauffeur` varchar(30) NOT NULL,
  `vendeur` varchar(30) NOT NULL,
  `aide_vendeur` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `camion`
--

INSERT INTO `camion` (`matricule`, `chauffeur`, `vendeur`, `aide_vendeur`) VALUES
(0, '0', '0', '0'),
(595959, '0', '0', '0'),
(595, '', 'yassin', 'hassan'),
(654, '', 'yassin', 'hassan'),
(6564, 'nourdine', 'hassan', 'brahim'),
(1598, 'hassan ', 'nadir ', 'amiine');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `code` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `adress` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contact` int NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`code`, `name`, `adress`, `contact`) VALUES
(55, 'bouchta', 'tetouan', 534344343),
(0, 'ahmed', '', 635263516),
(12, 'anass', 'chaoun', 1);

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int NOT NULL,
  `gamme` varchar(30) NOT NULL,
  `produit` varchar(30) NOT NULL,
  `quantite` int NOT NULL,
  `prix` float NOT NULL,
  `n_commande` int NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`id`, `gamme`, `produit`, `quantite`, `prix`, `n_commande`, `total`) VALUES
(0, 'Lait', 'lait plastique 1/2', 25, 4, 42424, 88),
(1, 'Lait', 'lait carton 1/2', 20, 4, 42424, 80),
(2, 'Lait', 'lait carton 1/2', 12, 2.5, 456841, 30),
(3, 'Yaourt', 'Yaourt vanille', 5, 5, 42424, 25);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `gamme` varchar(30) NOT NULL,
  `produit` varchar(30) NOT NULL,
  `quantite` int NOT NULL,
  `matricule` int NOT NULL,
  `situation` varchar(30) NOT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`gamme`, `produit`, `quantite`, `matricule`, `situation`, `date`) VALUES
('Lait', 'lait carton 1', 61656526, 61656526, '0', '0000-00-00'),
('Lait', 'lait carton 1', 61656526, 61656526, 'entree', '2023-06-10'),
('Lait', 'lait carton 1', 61656526, 61656526, 'entree', '2023-06-10'),
('Yaourt', 'grand yaourt vanille', 50, 187441, 'entree', '2023-06-13'),
('Yaourt', 'grand yaourt vanille', 25, 187441, 'sortie', '2023-06-13'),
('Yaourt', 'Yaourt vanille', 15, 159, 'entree', '0000-00-00'),
('Yaourt', 'Yaourt vanille', 15, 987654, 'entree', '0000-00-00'),
('Jben', 'jben 1/2', 10, 987654, 'entree', '0000-00-00'),
('Lait', 'lait carton 1', 10, 987654, 'entree', '0000-00-00'),
('Lait', 'lait carton 1/2', 10, 1, 'entree', '0000-00-00'),
('Lait', 'lait carton 1/2', 5, 1, 'sortie', '0000-00-00'),
('Lait', 'lait carton 1', 15, 1, 'entree', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `vendeur`
--

DROP TABLE IF EXISTS `vendeur`;
CREATE TABLE IF NOT EXISTS `vendeur` (
  `matricule` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `contact` int NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vendeur`
--

INSERT INTO `vendeur` (`matricule`, `name`, `password`, `contact`) VALUES
(15, 'souhaila', 'bouras', 635152506),
(1234, 'amine', '123456789', 636240557);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
