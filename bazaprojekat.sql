/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - bazaprojekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bazaprojekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `bazaprojekat`;

/*Table structure for table `glumci` */

DROP TABLE IF EXISTS `glumci`;

CREATE TABLE `glumci` (
  `jmbg` bigint(20) NOT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `glumci` */

insert  into `glumci`(`jmbg`,`ime`,`prezime`) values 
(171099775056,'Jovana','Petrovic'),
(1104001715847,'Ana','Petrovic'),
(1709003715478,'Nina','Novakovic'),
(2010000715068,'Petra','Mikic'),
(2010001710422,'Marko','Markovic'),
(2501998715489,'Мilica','Mitic');

/*Table structure for table `glumi` */

DROP TABLE IF EXISTS `glumi`;

CREATE TABLE `glumi` (
  `jmbg` bigint(20) NOT NULL,
  `uloga` bigint(20) NOT NULL,
  `idGlumi` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`jmbg`,`uloga`,`idGlumi`),
  KEY `uloga` (`uloga`),
  KEY `id` (`idGlumi`),
  CONSTRAINT `glumi_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `glumci` (`jmbg`),
  CONSTRAINT `glumi_ibfk_3` FOREIGN KEY (`uloga`) REFERENCES `uloga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `glumi` */

insert  into `glumi`(`jmbg`,`uloga`,`idGlumi`) values 
(171099775056,41,46),
(171099775056,45,50),
(1104001715847,7,4),
(1104001715847,8,5),
(1709003715478,39,44),
(2010000715068,43,48),
(2010001710422,1,1),
(2010001710422,38,43),
(2010001710422,40,45),
(2010001710422,44,49),
(2501998715489,42,47);

/*Table structure for table `koreograf` */

DROP TABLE IF EXISTS `koreograf`;

CREATE TABLE `koreograf` (
  `jmbg` bigint(20) NOT NULL,
  `imeKor` varchar(50) DEFAULT NULL,
  `prezimeKor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `koreograf` */

insert  into `koreograf`(`jmbg`,`imeKor`,`prezimeKor`) values 
(250105522,'andrija','andic');

/*Table structure for table `korisnici` */

DROP TABLE IF EXISTS `korisnici`;

CREATE TABLE `korisnici` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnici` */

insert  into `korisnici`(`id`,`email`,`password`,`ime`,`prezime`,`number`,`gender`) values 
(1,'ana.s@gmail.com','ana','ana','s','+381550505','Zenski'),
(2,'primer1@gmail.com','primer1','andrija','sto','+3816525525','Muski'),
(3,'milica.p@gmail.com','milicap','Milica','Petrovic','+381654754','Zenski'),
(4,'petar@gmail.com','petar','Petar','Petrovic','+38165598745','Muski'),
(5,'milos@gmail.com','milos','milos','milosevic','+38745925255','Muski'),
(6,'sasa.s@gmail.com','sasas','Sasa','Stojanovic','0648167116','Muski'),
(8,'danijela.sale1000@gmail.com','danijela','Danijela','Stojanovic','0605505068','Zenski'),
(9,'marko.ciric2002@gmail.com','marko2002','marko','ciric','+38165205220','Muski'),
(10,'stojanovicbudimir1947@gmail.com','anaanana','buda','stojanovic','+381605505058','Muski'),
(11,'as20210324@student.fon.bg.ac.rs','anaana','Ana','Stojanovic','+3816545454','Zenski');

/*Table structure for table `kostimograf` */

DROP TABLE IF EXISTS `kostimograf`;

CREATE TABLE `kostimograf` (
  `jmbg` bigint(20) NOT NULL,
  `imeKos` varchar(50) DEFAULT NULL,
  `prezimeKos` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kostimograf` */

insert  into `kostimograf`(`jmbg`,`imeKos`,`prezimeKos`) values 
(751455555,'marko','markovic');

/*Table structure for table `predstava` */

DROP TABLE IF EXISTS `predstava`;

CREATE TABLE `predstava` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) DEFAULT NULL,
  `reziser` bigint(20) DEFAULT NULL,
  `trajanje` bigint(20) DEFAULT NULL,
  `zanr` varchar(50) DEFAULT NULL,
  `idKorisnika` bigint(20) DEFAULT NULL,
  `scenograf` bigint(20) DEFAULT NULL,
  `koreograf` bigint(20) DEFAULT NULL,
  `kostimograf` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idKorisnika` (`idKorisnika`),
  KEY `reziser` (`reziser`),
  KEY `scenograf` (`scenograf`),
  KEY `koreograf` (`koreograf`),
  KEY `kostimograf` (`kostimograf`),
  CONSTRAINT `predstava_ibfk_1` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnici` (`id`),
  CONSTRAINT `predstava_ibfk_2` FOREIGN KEY (`reziser`) REFERENCES `reziser` (`id`),
  CONSTRAINT `predstava_ibfk_3` FOREIGN KEY (`scenograf`) REFERENCES `scenograf` (`jmbg`),
  CONSTRAINT `predstava_ibfk_4` FOREIGN KEY (`koreograf`) REFERENCES `koreograf` (`jmbg`),
  CONSTRAINT `predstava_ibfk_5` FOREIGN KEY (`kostimograf`) REFERENCES `kostimograf` (`jmbg`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `predstava` */

insert  into `predstava`(`id`,`naziv`,`reziser`,`trajanje`,`zanr`,`idKorisnika`,`scenograf`,`koreograf`,`kostimograf`) values 
(1,'Pr1',2,120,'KOMEDIJA',1,40520007589,250105522,751455555),
(2,'Ana Karenjina',2,100,'BALET',1,40520007589,250105522,751455555),
(3,'Ljudi u crnom',2,85,'AKCIJA',6,40520007589,250105522,751455555),
(4,'hahah',1,123,'KOMEDIJA',1,40520007589,250105522,751455555),
(6,'Rat i mir',1,90,'HOROR',1,40520007589,250105522,751455555),
(7,'Juzni vetar',2,120,'AKCIJA',1,40520007589,250105522,751455555),
(10,'Juzni vetar2',2,122,'KOMEDIJA',1,40520007589,250105522,751455555),
(12,'krcko orascic',2,200,'BALET',1,40520007589,250105522,751455555),
(20,'Secer',2,100,'KOMEDIJA',1,40520007589,250105522,751455555),
(21,'provera',1,100,'KOMEDIJA',1,40520007589,250105522,751455555),
(22,'Alisa u zemlji cuda',2,100,'BALET',1,40520007589,250105522,751455555),
(23,'haa',1,120,'KOMEDIJA',1,40520007589,250105522,751455555),
(24,'ddd',2,123,'AKCIJA',1,40520007589,250105522,751455555),
(25,'rr',1,11,'HOROR',1,40520007589,250105522,751455555),
(26,'proba',2,121,'MJUZIKL',1,40520007589,250105522,751455555),
(27,'proba1234',1,123,'KOMEDIJA',1,40520007589,250105522,751455555),
(28,'probanova',1,1234,'KOMEDIJA',1,40520007589,250105522,751455555),
(29,'Ljubavna prica',2,87,'KOMEDIJA',1,40520007589,250105522,751455555),
(30,'trnova ruzica',2,86,'MJUZIKL',1,40520007589,250105522,751455555),
(31,'d',1,2,'KOMEDIJA',1,40520007589,250105522,751455555),
(32,'Ljubav',2,108,'AKCIJA',1,40520007589,250105522,751455555),
(33,'Anabel',2,110,'HOROR',1,40520007589,250105522,751455555);

/*Table structure for table `predstave` */

DROP TABLE IF EXISTS `predstave`;

CREATE TABLE `predstave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) DEFAULT NULL,
  `korisnik` bigint(20) DEFAULT NULL,
  `trajanje` bigint(20) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `zanr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `korisnik` (`korisnik`),
  CONSTRAINT `predstave_ibfk_1` FOREIGN KEY (`korisnik`) REFERENCES `korisnici` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `predstave` */

insert  into `predstave`(`id`,`naziv`,`korisnik`,`trajanje`,`datum`,`zanr`) values 
(4,'p2',2,282,'2024-12-07','BALET'),
(6,'Ana Karenjina',1,125,'2024-11-27','MJUZIKL'),
(9,'kkk',5,222,'2025-02-05','HOROR'),
(10,'predstava',1,122,'2025-06-15','HOROR');

/*Table structure for table `repertoar` */

DROP TABLE IF EXISTS `repertoar`;

CREATE TABLE `repertoar` (
  `idPredstave` bigint(20) NOT NULL,
  `datum` date NOT NULL,
  `sala` varchar(20) NOT NULL,
  `vreme` varchar(10) NOT NULL,
  PRIMARY KEY (`idPredstave`,`datum`,`sala`,`vreme`),
  CONSTRAINT `repertoar_ibfk_1` FOREIGN KEY (`idPredstave`) REFERENCES `predstava` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `repertoar` */

insert  into `repertoar`(`idPredstave`,`datum`,`sala`,`vreme`) values 
(2,'2024-12-30','6','14:25'),
(3,'2024-12-30','10','10:00'),
(3,'2025-05-25','5','10:00'),
(3,'2025-10-10','8','22:00'),
(6,'2025-01-15','6','18:00'),
(10,'2025-05-13','5','12:45'),
(27,'2025-02-12','5','18:00');

/*Table structure for table `reziser` */

DROP TABLE IF EXISTS `reziser`;

CREATE TABLE `reziser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imeR` varchar(30) DEFAULT NULL,
  `prezimeR` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `reziser` */

insert  into `reziser`(`id`,`imeR`,`prezimeR`) values 
(1,'Marko','M'),
(2,'Bojan','Bogd');

/*Table structure for table `scenograf` */

DROP TABLE IF EXISTS `scenograf`;

CREATE TABLE `scenograf` (
  `jmbg` bigint(20) NOT NULL,
  `imeS` varchar(50) DEFAULT NULL,
  `prezimeS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `scenograf` */

insert  into `scenograf`(`jmbg`,`imeS`,`prezimeS`) values 
(40520007589,'luka','lukic');

/*Table structure for table `uloga` */

DROP TABLE IF EXISTS `uloga`;

CREATE TABLE `uloga` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) DEFAULT NULL,
  `idPredstave` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPredstave` (`idPredstave`),
  CONSTRAINT `uloga_ibfk_1` FOREIGN KEY (`idPredstave`) REFERENCES `predstava` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `uloga` */

insert  into `uloga`(`id`,`naziv`,`idPredstave`) values 
(1,'ana',2),
(2,'marica',2),
(4,'maras',10),
(7,'glumac1',12),
(8,'glumac2',12),
(9,'glumac3',12),
(23,'Andrija',20),
(24,'Zena',20),
(25,'Mama',20),
(26,'dete',21),
(27,'alisa',22),
(28,'zec',22),
(29,'sesirdzija',22),
(30,'mama',23),
(31,'cerka',23),
(32,'dd',24),
(33,'sd',25),
(34,'proba',26),
(35,'proba',27),
(36,'proba',28),
(37,'nova',28),
(38,'devojka',29),
(39,'s',30),
(40,'s',31),
(41,'milica',32),
(42,'anabel',33),
(43,'zena',10),
(44,'devojka',29),
(45,'milica',32);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
