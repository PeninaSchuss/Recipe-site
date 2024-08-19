-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: יוני 29, 2023 בזמן 12:34 PM
-- גרסת שרת: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ex5`
--

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `ingredient`
--

CREATE TABLE `ingredient` (
  `id` bigint(20) NOT NULL,
  `ingredient_name` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `recipe_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `ingredient`
--

INSERT INTO `ingredient` (`id`, `ingredient_name`, `quantity`, `recipe_id`) VALUES
(1, 'melon', '1', 1),
(2, 'sweet potato', '2', 1),
(52, 'water', '5 cups', 2),
(53, 'vegtables ', '3 ', 2),
(54, 'egg', '2', 3),
(55, 'water', '1 cup', 3),
(56, 'flour', '2 cups', 3),
(57, 'egg', '1', 4),
(58, 'cheese', '1 piece', 4),
(59, 'tomato', '2', 5),
(60, 'salt', '2 spoons', 5),
(61, 'humus', '2 cups', 6),
(62, 'peper', '1 spoon', 6),
(63, 'salt', '1/2 spoon', 6),
(102, 'wine', '1 buttle', 52),
(103, 'melon', '1', 54),
(152, 'meat', '1', 102),
(202, 'chek', '2', 152),
(252, 'egg', '1', 202);

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `ingredient_seq`
--

CREATE TABLE `ingredient_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `ingredient_seq`
--

INSERT INTO `ingredient_seq` (`next_val`) VALUES
(351);

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `recipe`
--

CREATE TABLE `recipe` (
  `id` bigint(20) NOT NULL,
  `creation_date` date NOT NULL,
  `difficulty_level` varchar(255) DEFAULT NULL,
  `dish_type` varchar(255) DEFAULT NULL,
  `prep_instructions` varchar(255) DEFAULT NULL,
  `prep_time` varchar(255) DEFAULT NULL,
  `recipe_name` varchar(255) DEFAULT NULL,
  `recipe_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `recipe`
--

INSERT INTO `recipe` (`id`, `creation_date`, `difficulty_level`, `dish_type`, `prep_instructions`, `prep_time`, `recipe_name`, `recipe_type`) VALUES
(1, '2023-06-29', 'easy', 'Salads', 'cut nicely and put in friedj', '10', 'fruit salad', 'RECIPES'),
(2, '2023-06-29', 'middle', 'Soups', 'cut the vegtables and put water in the pot ', '30', 'vegtables soup', 'RECIPES'),
(3, '2023-06-29', 'middle', 'Gluten Free', 'bake the cookies', '20', 'gluten free cookies', 'SPECIAL DIETS'),
(4, '2023-06-29', 'easy', 'Breakfast', 'cook the egg', '10', 'egg with cheese', 'RECIPES'),
(5, '2023-06-29', 'easy', 'Salads', 'cut nicely and put in ball', '20', 'Tomato Salad', 'RECIPES'),
(6, '2023-06-29', 'easy', 'Salads', 'mix the humus with the salt and water', '10', 'Humus', 'RECIPES'),
(52, '2023-06-29', 'easy', 'Drinks', 'drink it', '10', 'wine', 'RECIPES'),
(53, '2023-06-29', 'easy', 'Breakfast', 'cut nicely and put in friedj', '10', 'fruit salad', 'RECIPES'),
(54, '2023-06-29', 'easy', 'Vegan', 'cut nicely and put in friedj', '10', 'fruit salad', 'SPECIAL DIETS'),
(102, '2023-06-29', 'hard', 'Main Dishes', 'cook  the meat with water', '45', 'meat', 'RECIPES'),
(152, '2023-06-29', 'easy', 'Main Dishes', 'cook', '10', 'checken', 'RECIPES'),
(202, '2023-06-29', 'easy', 'Sugar Free', 'mix and bake', '20', 'cake', 'SPECIAL DIETS');

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `recipe_categories`
--

CREATE TABLE `recipe_categories` (
  `id` bigint(20) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `recipe_categories`
--

INSERT INTO `recipe_categories` (`id`, `category_name`) VALUES
(102, 'RECIPES'),
(103, 'SPECIAL DIETS');

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `recipe_categories_seq`
--

CREATE TABLE `recipe_categories_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `recipe_categories_seq`
--

INSERT INTO `recipe_categories_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `recipe_seq`
--

CREATE TABLE `recipe_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `recipe_seq`
--

INSERT INTO `recipe_seq` (`next_val`) VALUES
(301);

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `recipe_type`
--

CREATE TABLE `recipe_type` (
  `id` bigint(20) NOT NULL,
  `recipe_type` varchar(255) DEFAULT NULL,
  `recipe_categories_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `recipe_type`
--

INSERT INTO `recipe_type` (`id`, `recipe_type`, `recipe_categories_id`) VALUES
(52, 'Breakfast', 102),
(53, 'Soups', 102),
(54, 'Salads', 102),
(55, 'Main Dishes', 102),
(56, 'Side Dishes', 102),
(57, 'Desserts', 102),
(58, 'Drinks', 102),
(59, 'Vegan', 103),
(60, 'Gluten Free', 103),
(61, 'Sugar Free', 103),
(62, 'Low Calorie', 103);

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `recipe_type_seq`
--

CREATE TABLE `recipe_type_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `recipe_type_seq`
--

INSERT INTO `recipe_type_seq` (`next_val`) VALUES
(201);

--
-- Indexes for dumped tables
--

--
-- אינדקסים לטבלה `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj0s4ywmqqqw4h5iommigh5yja` (`recipe_id`);

--
-- אינדקסים לטבלה `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`id`);

--
-- אינדקסים לטבלה `recipe_categories`
--
ALTER TABLE `recipe_categories`
  ADD PRIMARY KEY (`id`);

--
-- אינדקסים לטבלה `recipe_type`
--
ALTER TABLE `recipe_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKllptgp29eon6i43lqgmsogqxg` (`recipe_categories_id`);

--
-- הגבלות לטבלאות שהוצאו
--

--
-- הגבלות לטבלה `ingredient`
--
ALTER TABLE `ingredient`
  ADD CONSTRAINT `FKj0s4ywmqqqw4h5iommigh5yja` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`);

--
-- הגבלות לטבלה `recipe_type`
--
ALTER TABLE `recipe_type`
  ADD CONSTRAINT `FKllptgp29eon6i43lqgmsogqxg` FOREIGN KEY (`recipe_categories_id`) REFERENCES `recipe_categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
