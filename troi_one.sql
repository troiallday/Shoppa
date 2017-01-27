-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 27, 2017 at 03:19 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `troi_one`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `details` varchar(255) NOT NULL,
  `image1` varchar(255) NOT NULL,
  `image2` varchar(255) NOT NULL,
  `image3` varchar(255) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`name`, `category`, `price`, `details`, `image1`, `image2`, `image3`, `id`) VALUES
('Dratell Hair Shampoo', 'Soaps', 2.1, 'Hair cleaning form', '/product_images/image1.jpg', '/product_images/image2.jpg', '/product_images/image3.jpg', 1),
('Trojan Children''s Stationery Pack', 'Stationery', 3.5, 'Full set for children''s stationery needs', '/product_images/aye.jpg', '/product_images/ayee.jpg', '/product_images/ayeee.jpg', 3),
('Zimcorn Maputi', 'Foods', 0.1, 'Popped corn from the rich and fertile lands of Mother Africa', '/product_images/image4.jpg', '/product_images/image5.jpg', '/product_images/image6.jpg', 4),
('Sandmine Wine Glass 2Pack', 'Kitchen Deco', 2.6, 'Classy wine glasses blasted from the rich desert sands of the Kalahari', '/product_images/image7.jpg', '/product_images/image8.jpg', '/product_images/image8.jpg', 5),
('Black Suede Men''s Deodorant', 'Deodorants', 3.6, 'something something something somethong something somehting', '/product_images/deo1.jpg', '/product_images/deo.png', '/product_images/deo.png3', 6),
('Bright''s Energy Saver Light Bulb', 'House something', 2.1, 'something something something something something', '/product_images/bulb.jpg', '/product_images/bulb2.jpg', '/product_images/bulb3.jpg', 7),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 8),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 9),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 11),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 12),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 13),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 14),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 15),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 16),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 17),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 18),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 19),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 20),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 21),
('Lobel''s Bread', 'Foods', 0.9, 'Premium freshly baked bread at affordable prices', '/product_images/deo.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 22),
('Jumbo Snacks', 'Foods', 0.1, 'Crispy snacks to power you up ', '/product_images/bulb.jpg', '/product_images/deo.image1', '/product_images/deo.image1', 23);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
