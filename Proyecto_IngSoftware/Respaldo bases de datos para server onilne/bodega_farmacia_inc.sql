-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-12-2015 a las 02:30:29
-- Versión del servidor: 10.0.17-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bodega_farmacia_inc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `npedido` int(11) NOT NULL,
  `medicamento` varchar(50) NOT NULL,
  `cantidad_requerida` int(5) NOT NULL,
  `medida` varchar(10) NOT NULL,
  `estado_entrega` varchar(10) NOT NULL,
  `fecha_solicitud` varchar(10) NOT NULL,
  `unidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`npedido`, `medicamento`, `cantidad_requerida`, `medida`, `estado_entrega`, `fecha_solicitud`, `unidad`) VALUES
(1, 'bolsa nutricion', 0, '500ml', 'Entregado', '2015-11-16', 'Preparación de Drogas'),
(3, 'HOLA', 0, '10ml', 'Entregado', '2015-11-16', 'Preparación de Drogas'),
(4, 'bolsa nutricion', 0, '500ml', 'Entregado', '2015-11-16', 'Preparación de Drogas'),
(6, 'bolsa suero', 0, '100ml', 'Entregado', '2015-11-17', 'Preparación de Drogas'),
(7, 'parasetelamol', 10, '15cm', 'Pendiente', '2015-11-18', 'Farmacia Ambulatoria'),
(8, 'morfina', 15, '200ml', 'Pendiente', '2015-11-18', 'Farmacia Ambulatoria'),
(10, 'HOLA', 0, '1 grm', 'Entregado', '2015-11-18', 'Farmacia Hospitalizados'),
(11, 'parasetelamol', 2, '15cm', 'Pendiente', '2015-11-18', 'Farmacia Hospitalizados'),
(12, 'bolsa nutricion', 0, '250ml', 'Entregado', '2015-12-08', 'Farmacia Hospitalizados');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `num_registro` int(240) NOT NULL,
  `id_registro` int(30) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fecha` varchar(10) NOT NULL,
  `medida` varchar(25) NOT NULL,
  `stock` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`num_registro`, `id_registro`, `nombre`, `fecha`, `medida`, `stock`) VALUES
(1, 312002, 'bolsa nutricion', '2015-10-02', '250ml', 460),
(2, 311002, 'bolsa suero', '2010-04-23', '100ml', 30),
(3, 310002, 'HOLAsas', '2015-10-30', '100ml', 90),
(5, 666999666, 'parasetelamol', '2015-11-16', '15cm', 5),
(6, 123, 'morfina', '2015-11-18', '200ml', 20),
(7, 90240, 'coditidina', '2015-12-08', '125ml', 20),
(8, 12345, 'coditidina', '2015-12-08', '500ml', 120),
(11, 321, 'cualquiera', '2015-12-08', '1000ml', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `num_usuario` int(240) NOT NULL,
  `nick_usuario` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `cargo_administrativo` varchar(30) NOT NULL,
  `pregunta_seguridad` varchar(200) NOT NULL,
  `respuesta_seguridad` varchar(100) NOT NULL,
  `unidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`num_usuario`, `nick_usuario`, `nombre`, `apellidos`, `contraseña`, `cargo_administrativo`, `pregunta_seguridad`, `respuesta_seguridad`, `unidad`) VALUES
(2, '176801670', 'Nicolas', 'Aguirre Tobar', '17680167', 'registro bodega', '¿Cual es el nombre de tu abuela materna?', 'Gula', 'Farmacia Hospitalizados'),
(3, '12345', 'Juan', 'Perez', '12345', 'total', '¿Cual es el nombre de tu abuela materna?', 'Gula', 'Ninguna'),
(5, 'lemusx', 'Alonso', 'Aguirre Tobar', '12345', 'registro usuarios', '¿Cual es el nombre de tu abuela materna?', 'Gula', 'Farmacia Hospitalizados'),
(13, '3', '3', '2', '3', 'pedidos de bodega', '¿De que modelo era tu primer vehiculo a motor?', '3', 'Farmacia Hospitalizados'),
(15, 'Desintala', 'Juan', 'Tramolao', '123', 'pedidos de bodega', '¿Como se llamaba mi primera mascota?', 'cachupin', 'Banco de Drogas'),
(16, 'hola', 'Marco', 'Guzman', '123', 'registro usuarios', '¿Ocupacion del abuelo?', 'carpintero', 'Banco de Drogas'),
(17, 'aweonao', 'sacowea', 'ctm', 'ctm', 'pedidos de bodega', '¿Cual es el nombre de tu abuela materna?', 'vieja culia', 'Preparación de Drogas'),
(18, 'nuevoUsuario', 'holi', 'dhfa', '1', 'registro bodega', '¿Como se llamaba mi primera mascota?', '1', 'Banco de Drogas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`npedido`);

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`num_registro`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`num_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `npedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `num_registro` int(240) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `num_usuario` int(240) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
