SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
USE ripico;
DROP TABLE IF EXISTS `mannschaft`;
CREATE TABLE `mannschaft` (
  `mannschaft_id` int(11) NOT NULL,
  `mannschaft_name` varchar(128) NOT NULL,
  `logopfad` varchar(256) NOT NULL,
  `sportart_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE TABLE `mannschaft`;
INSERT INTO `mannschaft` (`mannschaft_id`, `mannschaft_name`, `logopfad`, `sportart_id`) VALUES
(1, 'Borussia Dortmund', '/resources/imgs/logos/Fußball/bvb.png', 1),
(2, 'Preußen Münster', '/resources/imgs/logos/Fußball/preussenMuenster.png', 1),
(3, '1. FC Köln', '/resources/imgs/logos/Fußball/fcKoeln.png', 1),
(4, 'FC Barcelona', '/resources/imgs/logos/Fußball/barcelona.png', 1),
(5, 'Preußen Werl', '/resources/imgs/logos/Fußball/preussenWerl.png', 1),
(6, 'Manchester United', '/resources/imgs/logos/Fußball/manchesterUnited.png', 1),
(7, 'Hannover 96', '/resources/imgs/logos/Fußball/hannover96.png', 1),
(8, 'Bayern München', '/resources/imgs/logos/Fußball/fcBayernMuenchen.png', 1),
(9, 'Boston Celtics', '/resources/imgs/logos/Basketball/bostonCeltics.png', 2),
(10, 'Los Angeles Lakers', '/resources/imgs/logos/Basketball/laLakers.png', 2),
(11, 'Chicago Bulls', '/resources/imgs/logos/Basketball/chicagoBulls.png', 2),
(12, 'Detroit Pistons', '/resources/imgs/logos/Basketball/detroidPistons.png', 2),
(13, 'Miami Heat', '/resources/imgs/logos/Basketball/miamiHeat.png', 2),
(14, 'Dallas Mavericks', '/resources/imgs/logos/Basketball/mavericks.png', 2),
(15, 'Iserlohn Roosters', '/resources/imgs/logos/Eishockey/iserlohnRoosters.png', 3),
(16, 'Kölner Haie', '/resources/imgs/logos/Eishockey/koelnerHaie.png', 3),
(17, 'Eisbären Berlin', '/resources/imgs/logos/Eishockey/eisbaerenBerlin.png', 3),
(18, 'Düsseldorfer EG', '/resources/imgs/logos/Eishockey/duesseldorferEG.png', 3),
(19, 'EHC Red Bull München', '/resources/imgs/logos/Eishockey/redBullMuenchen.png', 3),
(20, 'Mighty Dogs Schweinfurt', '/resources/imgs/logos/Eishockey/mightyDogsSchweinfurt.png', 3),
(21, 'Grizzly Wolfsburg', '/resources/imgs/logos/Eishockey/grizzlyWolfsburg.png', 3),
(22, 'Fischtown Pinguins Bremerhaven', '/resources/imgs/logos/Eishockey/fischtownPinguins.png', 3);

DROP TABLE IF EXISTS `mitarbeiter`;
CREATE TABLE `mitarbeiter` (
  `benutzername` varchar(25) NOT NULL,
  `passwort` varchar(256) NOT NULL,
  `vorname` varchar(128) NOT NULL,
  `nachname` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE TABLE `mitarbeiter`;
INSERT INTO `mitarbeiter` (`benutzername`, `passwort`, `vorname`, `nachname`) VALUES
('earner', '123', 'Wucas', 'Lerner'),
('knb', 'kenntkeiner', 'Einsacht', 'Sieben'),
('wirf', 'passwort1', 'Werft', 'den Ball');

DROP TABLE IF EXISTS `quote`;
CREATE TABLE `quote` (
  `spiel_id` int(11) NOT NULL,
  `quotenart` enum('HEIM','AUSWAERTS','UNENTSCHIEDEN') NOT NULL,
  `quote` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE TABLE `quote`;
INSERT INTO `quote` (`spiel_id`, `quotenart`, `quote`) VALUES
(1, 'HEIM', 1),
(1, 'AUSWAERTS', 10.1),
(1, 'UNENTSCHIEDEN', 4.3),
(2, 'HEIM', 2),
(2, 'AUSWAERTS', 4.7),
(2, 'UNENTSCHIEDEN', 8.6),
(3, 'HEIM', 1.1),
(3, 'AUSWAERTS', 2.1),
(3, 'UNENTSCHIEDEN', 5.4),
(4, 'HEIM', 3),
(4, 'AUSWAERTS', 2.1),
(4, 'UNENTSCHIEDEN', 3.3),
(5, 'HEIM', 2.5),
(5, 'AUSWAERTS', 4.7),
(5, 'UNENTSCHIEDEN', 1.6),
(6, 'HEIM', 6.8),
(6, 'AUSWAERTS', 2.4),
(6, 'UNENTSCHIEDEN', 3.5),
(7, 'HEIM', 6.6),
(7, 'AUSWAERTS', 9.7),
(7, 'UNENTSCHIEDEN', 2.4),
(8, 'HEIM', 7.5),
(8, 'AUSWAERTS', 8.5),
(8, 'UNENTSCHIEDEN', 2.8),
(9, 'HEIM', 3.9),
(9, 'AUSWAERTS', 2.9),
(9, 'UNENTSCHIEDEN', 4.3),
(10, 'HEIM', 4.1),
(10, 'AUSWAERTS', 8.9),
(10, 'UNENTSCHIEDEN', 8.5),
(11, 'HEIM', 7.7),
(11, 'AUSWAERTS', 1.1),
(11, 'UNENTSCHIEDEN', 5.3),
(12, 'HEIM', 6.1),
(12, 'AUSWAERTS', 1.6),
(12, 'UNENTSCHIEDEN', 7.1),
(13, 'HEIM', 8.1),
(13, 'AUSWAERTS', 5.6),
(13, 'UNENTSCHIEDEN', 6.2),
(14, 'HEIM', 5.9),
(14, 'AUSWAERTS', 5.8),
(14, 'UNENTSCHIEDEN', 2.3),
(15, 'HEIM', 3.1),
(15, 'AUSWAERTS', 1.8),
(15, 'UNENTSCHIEDEN', 9.8),
(16, 'HEIM', 1.7),
(16, 'AUSWAERTS', 5.1),
(16, 'UNENTSCHIEDEN', 8.4),
(17, 'HEIM', 7.3),
(17, 'AUSWAERTS', 2.7),
(17, 'UNENTSCHIEDEN', 8.6),
(18, 'HEIM', 2.5),
(18, 'AUSWAERTS', 4.5),
(18, 'UNENTSCHIEDEN', 5.6),
(19, 'HEIM', 7.5),
(19, 'AUSWAERTS', 2.2),
(19, 'UNENTSCHIEDEN', 8.1),
(20, 'HEIM', 9.4),
(20, 'AUSWAERTS', 9.4),
(20, 'UNENTSCHIEDEN', 4.8),
(21, 'HEIM', 8.4),
(21, 'AUSWAERTS', 9.5),
(21, 'UNENTSCHIEDEN', 7.5),
(22, 'HEIM', 9.4),
(22, 'AUSWAERTS', 4.8),
(22, 'UNENTSCHIEDEN', 7.1),
(23, 'HEIM', 7.4),
(23, 'AUSWAERTS', 9.3),
(23, 'UNENTSCHIEDEN', 2.5),
(24, 'HEIM', 3.5),
(24, 'AUSWAERTS', 9.4),
(24, 'UNENTSCHIEDEN', 9.1),
(25, 'HEIM', 5.4),
(25, 'AUSWAERTS', 6.5),
(25, 'UNENTSCHIEDEN', 1.2),
(26, 'HEIM', 7.7),
(26, 'AUSWAERTS', 2.7),
(26, 'UNENTSCHIEDEN', 1.6);

DROP TABLE IF EXISTS `spiel`;
CREATE TABLE `spiel` (
  `spiel_id` int(11) NOT NULL,
  `sportart_id` int(11) NOT NULL,
  `mannschaft_heim` int(11) NOT NULL,
  `mannschaft_auswaerts` int(11) NOT NULL,
  `datum` datetime NOT NULL,
  `ergebnis` enum('HEIM','AUSWAERTS','UNENTSCHIEDEN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE TABLE `spiel`;
INSERT INTO `spiel` (`spiel_id`, `sportart_id`, `mannschaft_heim`, `mannschaft_auswaerts`, `datum`, `ergebnis`) VALUES
(1, 1, 1, 2, '2019-06-08 15:00:00', NULL),
(2, 1, 2, 5, '2019-06-28 18:00:00', NULL),
(3, 1, 8, 7, '2019-05-11 20:00:00', 'UNENTSCHIEDEN'),
(4, 1, 3, 4, '2019-05-12 15:00:00', 'HEIM'),
(5, 1, 5, 1, '2019-07-05 20:00:00', NULL),
(6, 1, 1, 8, '2019-06-29 18:00:00', NULL),
(7, 2, 14, 10, '2019-02-23 20:14:42', 'AUSWAERTS'),
(8, 2, 9, 13, '2019-01-23 11:49:18', NULL),
(9, 2, 13, 12, '2019-05-23 01:52:06', 'HEIM'),
(10, 2, 14, 12, '2019-04-05 23:27:25', 'AUSWAERTS'),
(11, 2, 11, 12, '2019-02-18 10:06:45', NULL),
(12, 2, 9, 13, '2018-11-16 13:39:03', 'UNENTSCHIEDEN'),
(13, 2, 13, 9, '2018-10-24 15:36:19', 'HEIM'),
(14, 2, 13, 14, '2019-02-19 07:51:17', 'UNENTSCHIEDEN'),
(15, 2, 11, 10, '2018-11-29 16:11:35', NULL),
(16, 2, 14, 9, '2018-07-24 18:31:49', NULL),
(17, 3, 21, 20, '2019-03-20 00:16:15', 'AUSWAERTS'),
(18, 3, 20, 15, '2019-05-08 13:41:35', 'HEIM'),
(19, 3, 15, 16, '2018-08-24 10:05:31', 'HEIM'),
(20, 3, 18, 19, '2018-10-29 08:32:52', 'HEIM'),
(21, 3, 20, 18, '2018-11-19 05:21:51', 'AUSWAERTS'),
(22, 3, 22, 19, '2018-08-15 02:34:08', 'HEIM'),
(23, 3, 22, 16, '2019-03-02 02:01:31', 'UNENTSCHIEDEN'),
(24, 3, 15, 16, '2019-06-16 17:33:42', NULL),
(25, 3, 17, 18, '2019-05-21 21:59:22', 'UNENTSCHIEDEN'),
(26, 3, 20, 16, '2019-05-15 20:42:23', NULL);

DROP TABLE IF EXISTS `sportart`;
CREATE TABLE `sportart` (
  `sportart_id` int(11) NOT NULL,
  `sportart_name` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE TABLE `sportart`;
INSERT INTO `sportart` (`sportart_id`, `sportart_name`) VALUES
(1, 'Fußball'),
(2, 'Basketball'),
(3, 'Eishockey');

DROP TABLE IF EXISTS `wette`;
CREATE TABLE `wette` (
  `wette_id` int(11) NOT NULL,
  `spiel_id` int(11) NOT NULL,
  `wettschein_id` int(11) NOT NULL,
  `gesetzte_wette` enum('HEIM','AUSWAERTS','UNENTSCHIEDEN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE TABLE `wette`;
INSERT INTO `wette` (`wette_id`, `spiel_id`, `wettschein_id`, `gesetzte_wette`) VALUES
(1, 2, 1, 'HEIM'),
(2, 5, 1, 'UNENTSCHIEDEN'),
(3, 4, 2, 'AUSWAERTS'),
(4, 6, 3, 'HEIM'),
(5, 1, 4, 'HEIM'),
(6, 3, 5, 'UNENTSCHIEDEN'),
(7, 1, 5, 'HEIM'),
(8, 2, 5, 'UNENTSCHIEDEN'),
(9, 3, 6, 'AUSWAERTS'),
(10, 5, 6, 'HEIM'),
(11, 6, 10, 'AUSWAERTS'),
(12, 8, 18, 'UNENTSCHIEDEN'),
(13, 17, 2, 'HEIM'),
(14, 17, 5, 'AUSWAERTS'),
(15, 1, 11, 'UNENTSCHIEDEN'),
(16, 10, 18, 'HEIM'),
(17, 22, 16, 'AUSWAERTS'),
(18, 13, 5, 'UNENTSCHIEDEN'),
(19, 9, 11, 'HEIM'),
(20, 22, 1, 'AUSWAERTS'),
(21, 22, 5, 'UNENTSCHIEDEN'),
(22, 5, 6, 'HEIM'),
(23, 1, 8, 'AUSWAERTS'),
(24, 14, 9, 'UNENTSCHIEDEN'),
(25, 10, 9, 'HEIM'),
(26, 7, 13, 'AUSWAERTS'),
(27, 14, 14, 'UNENTSCHIEDEN'),
(28, 25, 20, 'HEIM'),
(29, 7, 5, 'AUSWAERTS'),
(30, 26, 1, 'UNENTSCHIEDEN'),
(31, 20, 11, 'HEIM'),
(32, 18, 10, 'AUSWAERTS'),
(33, 21, 17, 'UNENTSCHIEDEN'),
(34, 11, 5, 'HEIM'),
(35, 9, 16, 'AUSWAERTS'),
(36, 13, 7, 'UNENTSCHIEDEN'),
(37, 6, 1, 'HEIM'),
(38, 14, 4, 'AUSWAERTS'),
(39, 24, 15, 'UNENTSCHIEDEN'),
(40, 6, 19, 'HEIM'),
(41, 11, 9, 'AUSWAERTS'),
(42, 15, 6, 'UNENTSCHIEDEN'),
(43, 22, 6, 'HEIM'),
(44, 25, 14, 'AUSWAERTS'),
(45, 20, 11, 'UNENTSCHIEDEN'),
(46, 6, 11, 'HEIM'),
(47, 6, 19, 'AUSWAERTS'),
(48, 7, 3, 'UNENTSCHIEDEN'),
(49, 2, 20, 'HEIM'),
(50, 8, 20, 'AUSWAERTS');


ALTER TABLE `mannschaft`
  ADD PRIMARY KEY (`mannschaft_id`);

ALTER TABLE `mitarbeiter`
  ADD PRIMARY KEY (`benutzername`);

ALTER TABLE `quote`
  ADD PRIMARY KEY (`spiel_id`,`quotenart`);

ALTER TABLE `spiel`
  ADD PRIMARY KEY (`spiel_id`);

ALTER TABLE `sportart`
  ADD PRIMARY KEY (`sportart_id`);

ALTER TABLE `wette`
  ADD PRIMARY KEY (`wette_id`);


ALTER TABLE `mannschaft`
  MODIFY `mannschaft_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

ALTER TABLE `spiel`
  MODIFY `spiel_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

ALTER TABLE `sportart`
  MODIFY `sportart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `wette`
  MODIFY `wette_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
COMMIT;