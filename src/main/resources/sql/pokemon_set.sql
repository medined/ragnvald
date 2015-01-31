-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 25, 2015 at 10:28 PM
-- Server version: 5.5.41-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ragnvald`
--

-- --------------------------------------------------------

--
-- Table structure for table `pokemon_set`
--

CREATE TABLE IF NOT EXISTS `pokemon_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL,
  `master` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=215 ;

--
-- Dumping data for table `pokemon_set`
--

INSERT INTO `pokemon_set` (`id`, `count`, `master`, `name`) VALUES
(144, 102, b'0', '01 Base Set'),
(145, 64, b'0', '02 Jungle'),
(146, 62, b'0', '03 Fossil'),
(147, 130, b'0', '04 Base Set 2'),
(148, 83, b'0', '05 Team Rocket'),
(149, 132, b'0', '06 Gym Heroes'),
(150, 132, b'0', '07 Gym Challenge'),
(151, 111, b'0', '08 Neo Genesis'),
(152, 75, b'0', '09 Neo Discovery'),
(153, 66, b'0', '10 Neo Revelation'),
(154, 113, b'0', '11 Neo Destiny'),
(155, 110, b'0', '12 Legendary Collection'),
(156, 165, b'0', '13 Expedition'),
(157, 150, b'0', '14 Aquapolis'),
(158, 150, b'0', '15 Skyridge'),
(159, 109, b'0', '16 EX Ruby & Sapphire'),
(160, 100, b'0', '17 EX Sandstorm'),
(161, 100, b'0', '18 EX Dragon'),
(162, 97, b'0', '19 EX Team Magma vs Team Aqua'),
(163, 102, b'0', '20 EX Hidden Legends'),
(164, 116, b'0', '21 EX FireRed & LeafGreen'),
(165, 111, b'0', '22 EX Team Rocket Returns'),
(166, 108, b'0', '23 EX Deoxys'),
(167, 107, b'0', '24 EX Emerald'),
(168, 117, b'0', '25 EX Unseen Forces'),
(169, 114, b'0', '26 EX Delta Species'),
(170, 93, b'0', '27 EX Legend Maker'),
(171, 111, b'0', '28 EX Holon Phantoms'),
(172, 100, b'0', '29 EX Crystal Guardians'),
(173, 101, b'0', '30 EX Dragon Frontiers'),
(174, 108, b'0', '31 EX Power Keepers'),
(175, 130, b'0', '32 Diamond & Pearl'),
(176, 124, b'0', '33 DP Mysterious Treasures'),
(177, 132, b'0', '34 DP Secret Wonders'),
(178, 106, b'0', '35 DP Great Encounters'),
(179, 100, b'0', '36 DP Majestic Dawn'),
(180, 146, b'0', '37 DP Legends Awakened'),
(181, 103, b'0', '38 DP Stormfront'),
(182, 130, b'0', '39 Platinum'),
(183, 114, b'0', '40 Platinum Rising Rivals'),
(184, 150, b'0', '41 Platinum Supreme Victors'),
(185, 99, b'0', '42 Platinum Arceus'),
(186, 123, b'0', '43 HeartGold & SoulSilver'),
(187, 95, b'0', '44 HS Unleashed'),
(188, 90, b'0', '45 HS Undaunted'),
(189, 102, b'0', '46 HS Triumphant'),
(190, 106, b'0', '47 HS Call of Legends'),
(191, 115, b'0', '48 Black & White'),
(192, 98, b'0', '49 BW Emerging Powers'),
(193, 102, b'0', '50 BW Noble Victories'),
(194, 103, b'0', '51 BW Next Destinies'),
(195, 111, b'0', '52 BW Dark Explorers'),
(196, 124, b'0', '53 BW Dragons Exalted'),
(197, 153, b'0', '54 BW Boundaries Crossed'),
(198, 138, b'0', '55 BW Plasma Storm'),
(199, 122, b'0', '56 BW Plasma Freeze'),
(200, 105, b'0', '57 BW Plasma Blast'),
(201, 138, b'0', '58 BW Legendary Treasures'),
(202, 146, b'0', '59 XY'),
(203, 106, b'0', '60 XY Flashfire'),
(204, 111, b'0', '61 XY Furious Fists'),
(205, 119, b'0', '62 XY Phantom Forces'),
(206, 0, b'0', 'POP Series 1 Promos'),
(207, 0, b'0', 'POP Series 2 Promos'),
(208, 17, b'0', 'POP Series 3 Promos'),
(209, 0, b'0', 'POP Series 4 Promos'),
(210, 0, b'0', 'POP Series 5 Promos'),
(211, 0, b'0', 'POP Series 6 Promos'),
(212, 0, b'0', 'POP Series 7 Promos'),
(213, 0, b'0', 'POP Series 8 Promos'),
(214, 0, b'0', 'POP Series 9 Promos');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
