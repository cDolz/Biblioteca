-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-11-2022 a las 18:07:43
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni`, `nombre`, `apellidos`) VALUES
('10101010P', 'Juandi', 'Apruébame Por Favor'),
('13121312L', 'Isabel', 'Díaz Ayuso'),
('50455327C', 'Jose Luis', 'Martinez Almeida'),
('50673456V', 'Carmina', 'Barrios López'),
('50864325D', 'Maria del Carmen', 'Lopez Gutierrez'),
('50876325A', 'Juana', 'López Álvarez'),
('50896538E', 'Carlos', 'Dolz Martín'),
('50976845J', 'Rodrigo', 'Fiu Fiu'),
('66666666Q', 'Juan Carlos', 'de Borbón y Borbón');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` int(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `fechapublicacion` varchar(50) NOT NULL,
  `editorial` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `nombre`, `autor`, `fechapublicacion`, `editorial`) VALUES
(1, 'Crepúsculo', 'Stephenie Meyer', '5 de octubre de 2005', 'Alfaguara'),
(2, 'Luna nueva', 'Stephenie Meyer', '21 de agosto de 2006', 'Alfaguara'),
(3, 'Eclipse', 'Stephenie Meyer', '7 de agosto de 2007', 'Alfaguara'),
(4, 'Amanecer', 'Stephenie Meyer', '2 de agosto de 2008', 'Alfaguara'),
(5, 'Memorias de Idhún I: La Resistencia', 'Laura Gallego García', '23 de septiembre de 2004', 'SM'),
(6, 'Memorias de Idhún II: Tríada', 'Laura Gallego García', '7 de octubre de 2005', 'SM'),
(7, 'Memorias de Idhún III: Panteón', 'Laura Gallego García', '14 de octubre de 2006', 'SM'),
(8, 'La Comunidad del Anillo', 'J. R. R. Tolkien', '29 de julio de 1954', 'Ediciones Minotauro'),
(9, 'Las dos torres', 'J. R. R. Tolkien', '11 de noviembre de 1954', 'Ediciones Minotauro'),
(10, 'El retorno del Rey', 'J. R. R. Tolkien', '20 de octubre de 1955', 'Ediciones Minotauro');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
