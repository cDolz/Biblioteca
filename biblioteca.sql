-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-02-2023 a las 08:10:37
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

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
  `id` int(9) NOT NULL,
  `dni` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `dni`, `nombre`, `apellidos`) VALUES
(1, '50776812B', 'Carlos', 'Dolz Martín'),
(4, '65677875H', 'Paco', 'Gonzalez Perez'),
(5, '66558877M', 'Paco', 'Jimenez Los Santos'),
(6, '68123476Y', 'Basilio', 'Rodriguez Lopez'),
(7, '66557744E', 'Anastasia', 'Campos Lopez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` int(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `fechaPublicacion` date NOT NULL,
  `editorial` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `nombre`, `autor`, `fechaPublicacion`, `editorial`) VALUES
(1, 'Harry Potter y la piedra filosofal', 'J K Rowling', '1997-06-26', 'Salamandra'),
(5, 'Memorias de Idhun La Resistencia', 'Laura Gallego Garcia', '2004-12-23', 'SM'),
(6, 'Memorias de Idhun III Panteon', 'Laura Gallego Garcia', '2006-10-14', 'SM'),
(7, 'Harry Potter y el caliz de fuego', 'J K Rowling', '2000-07-08', 'Salamandra'),
(8, 'Cincuenta sombras de Grey', 'E L James', '2011-05-25', 'Grijalbo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `id` int(9) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaDevolucion` date NOT NULL,
  `id_cliente` int(9) NOT NULL,
  `id_libro` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_libro` (`id_libro`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
