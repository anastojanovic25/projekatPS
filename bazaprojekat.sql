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
(25558555,'milics','milic'),
(545526666,'jovana','jovic'),
(2501552155,'ana','popovic');

/*Table structure for table `glumi` */

DROP TABLE IF EXISTS `glumi`;

CREATE TABLE `glumi` (
  `jmbg` bigint(20) NOT NULL,
  `idPredstave` bigint(20) NOT NULL,
  PRIMARY KEY (`jmbg`,`idPredstave`),
  KEY `idPredstave` (`idPredstave`),
  CONSTRAINT `glumi_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `glumci` (`jmbg`),
  CONSTRAINT `glumi_ibfk_2` FOREIGN KEY (`idPredstave`) REFERENCES `predstava` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `glumi` */

insert  into `glumi`(`jmbg`,`idPredstave`) values 
(25558555,1),
(545526666,2),
(545526666,3),
(2501552155,2),
(2501552155,3);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnici` */

insert  into `korisnici`(`id`,`email`,`password`,`ime`,`prezime`,`number`,`gender`) values 
(1,'ana.s@gmail.com','ana','ana','s','+381550505','Zenski'),
(2,'primer1@gmail.com','primer1','andrija','sto','+3816525525','Muski'),
(3,'milica.p@gmail.com','milicap','Milica','Petrovic','+381654754','Zenski'),
(4,'petar@gmail.com','petar','Petar','Petrovic','+38165598745','Muski'),
(5,'milos@gmail.com','milos','milos','milosevic','+38745925255','Muski'),
(6,'sasa.s@gmail.com','sasas','Sasa','Stojanovic','0648167116','Muski');

/*Table structure for table `predstava` */

DROP TABLE IF EXISTS `predstava`;

CREATE TABLE `predstava` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) DEFAULT NULL,
  `reziser` bigint(20) DEFAULT NULL,
  `trajanje` bigint(20) DEFAULT NULL,
  `zanr` varchar(50) DEFAULT NULL,
  `idKorisnika` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idKorisnika` (`idKorisnika`),
  KEY `reziser` (`reziser`),
  CONSTRAINT `predstava_ibfk_1` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnici` (`id`),
  CONSTRAINT `predstava_ibfk_2` FOREIGN KEY (`reziser`) REFERENCES `reziser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `predstava` */

insert  into `predstava`(`id`,`naziv`,`reziser`,`trajanje`,`zanr`,`idKorisnika`) values 
(1,'Pr1',2,120,'KOMEDIJA',1),
(2,'Ana Karenjina',2,100,'BALET',1),
(3,'Ljudi u crnom',1,85,'AKCIJA',6);

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
(2,'2024-12-29','2','16:00'),
(2,'2024-12-30','5','14:25'),
(3,'2024-12-30','10','10:00'),
(3,'2025-11-12','1','11:11'),
(3,'2025-11-20','5','17:00'),
(3,'2025-12-20','5','14:00'),
(3,'2025-12-20','6','17:30');

/*Table structure for table `reziser` */

DROP TABLE IF EXISTS `reziser`;

CREATE TABLE `reziser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `reziser` */

insert  into `reziser`(`id`,`ime`,`prezime`) values 
(1,'Marko','M'),
(2,'Bojan','Bogd');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
