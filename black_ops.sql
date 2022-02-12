-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 11 fév. 2022 à 19:12
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `black ops`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `mail_admin` varchar(254) COLLATE utf8_bin NOT NULL,
  `password_admin` varchar(50) COLLATE utf8_bin NOT NULL,
  `grade` int(1) NOT NULL,
  `NewPass` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `mail_admin` (`mail_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cat` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_cat`, `nom_cat`) VALUES
(1, 'Jeux Vidéo'),
(2, 'Stream');

-- --------------------------------------------------------

--
-- Structure de la table `champion`
--

DROP TABLE IF EXISTS `champion`;
CREATE TABLE IF NOT EXISTS `champion` (
  `Id_Champ` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_Champ` varchar(75) COLLATE utf8_bin NOT NULL,
  `description_Champ` longtext COLLATE utf8_bin NOT NULL,
  `Role_Champ` varchar(75) COLLATE utf8_bin NOT NULL,
  `Difficulte_Champ` varchar(25) COLLATE utf8_bin NOT NULL,
  `Image_Champ` longtext COLLATE utf8_bin NOT NULL,
  `Id_jeu` int(11) NOT NULL,
  PRIMARY KEY (`Id_Champ`),
  KEY `Id_Jeu` (`Id_jeu`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `champion`
--

INSERT INTO `champion` (`Id_Champ`, `Nom_Champ`, `description_Champ`, `Role_Champ`, `Difficulte_Champ`, `Image_Champ`, `Id_jeu`) VALUES
(1, 'AHRI', 'RENARD À NEUF QUEUES, Dotée d\'un lien inné avec le pouvoir naturel de Runeterra, Ahri est une Vastaya capable de modeler la magie pour en faire des orbes d\'énergie pure. Elle aime plus que tout jouer avec ses proies en manipulant leurs émotions avant de dévorer leur essence vitale. En dépit de sa nature de prédateur, Ahri n\'est pas sans empathie, car chaque âme qu\'elle absorbe l\'emplit de fragments de ses souvenirs.', 'MAGE', 'MODÉRÉE', 'E:\\Black Ops\\Ahri.jpg', 2);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_Cl` int(11) NOT NULL AUTO_INCREMENT,
  `Pseaudo_Cl` varchar(20) COLLATE utf8_bin NOT NULL,
  `Photo_Cl` longtext COLLATE utf8_bin NOT NULL,
  `mail_Cl` varchar(100) COLLATE utf8_bin NOT NULL,
  `pass_Cl` varchar(50) COLLATE utf8_bin NOT NULL,
  `DateNaissance_Cl` date NOT NULL,
  `Statut_Cl` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT 'offline',
  `NewPass_Cl` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_Cl`),
  UNIQUE KEY `Pseaudo` (`Pseaudo_Cl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_Commande` int(11) NOT NULL AUTO_INCREMENT,
  `date_commande` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Etat_commande` varchar(15) COLLATE utf8_bin NOT NULL,
  `id_cl` int(11) NOT NULL,
  PRIMARY KEY (`id_Commande`),
  KEY `id_cl` (`id_cl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id_contact` int(11) NOT NULL AUTO_INCREMENT,
  `np_contact` varchar(75) COLLATE utf8_bin NOT NULL,
  `mail_contact` varchar(100) COLLATE utf8_bin NOT NULL,
  `message` longtext COLLATE utf8_bin NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_contact`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `contact`
--

INSERT INTO `contact` (`id_contact`, `np_contact`, `mail_contact`, `message`, `date`) VALUES
(1, 'Mohamed Ali', 'anonyme_anonyme@yahoo.fr', ' mais seules huit d\'entre elles sont considérées comme communes : le Dauphin bleu et blanc, le Dauphin commun à bec court, le Grand Dauphin, le Dauphin de Risso, le Globicéphale noir, la Baleine à bec de Cuvier, le Grand Cachalot et le Rorqual commun. Leur répartition varie beaucoup selon les régions et leur abondance et leur diversité semblent maximales dans le bassin corso-liguro-provençal, où les cétacés sont protégés par le Sanctuaire Pelagos depuis 2002.\r\n\r\nRéputés pour leur intelligence à certains égards proche de celle des humains, les cétacés font l\'objet de mesures de protection d\'autant plus importantes que leur cycle biologique lent les rend vulnérables aux nombreuses menaces qui les affectent en Méditerranée. En effet, la densité de peuplement et de circulation dans le bassin méditerranéen expose les espèces marines, et particulièrement les gros mammifères marins comme les cétacés, à une large gamme de menaces qui nécessitent des mesures de conservation spécifiques.', '2022-02-11 19:03:22');

-- --------------------------------------------------------

--
-- Structure de la table `defi`
--

DROP TABLE IF EXISTS `defi`;
CREATE TABLE IF NOT EXISTS `defi` (
  `id_Defi` int(11) NOT NULL AUTO_INCREMENT,
  `nom_Defi` varchar(75) COLLATE utf8_bin NOT NULL,
  `Description_Defi` varchar(255) COLLATE utf8_bin NOT NULL,
  `img_Defi` longtext COLLATE utf8_bin NOT NULL,
  `prix_Defi` int(11) NOT NULL,
  `date_Defi` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `jeu_Defi` varchar(100) COLLATE utf8_bin NOT NULL,
  `nbr_equipe_Defi` int(11) NOT NULL,
  `Régle_Defi` varchar(1000) COLLATE utf8_bin NOT NULL,
  `Recompense_Defi` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_Defi`),
  UNIQUE KEY `nom_defi` (`nom_Defi`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `defi`
--

INSERT INTO `defi` (`id_Defi`, `nom_Defi`, `Description_Defi`, `img_Defi`, `prix_Defi`, `date_Defi`, `jeu_Defi`, `nbr_equipe_Defi`, `Régle_Defi`, `Recompense_Defi`) VALUES
(1, 'Pro_Lol', 'Bienvenue au tournoi League of Legends organisé par Black Ops. Il s\'agit d\'un tournoi 5 contre 5.\r\n\r\n', 'E:\\Black Ops\\lol.jpg', 100, '2022-02-11 19:56:48', 'League of Legends', 10, '1. Veuillez traiter l\'organisateur du tournoi et ses participants avec respect, sinon vous serez disqualifié pour comportement inapproprié.\r\n2. Veuillez vous inscrire à temps et être là au début du tournoi. Vous (ou votre équipe) avez été disqualifié en raison de votre absence au tournoi.\r\n3. Vous et les membres de votre équipe devez vous inscrire pour vous qualifier pour l\'événement.\r\n4. Vous pouvez participer à ce tournoi si votre nom d\'inscription correspond à votre surnom de jeu. S\'ils diffèrent, vous serez disqualifié.', '500 Dt.\r\nLes réclamations de prix doivent être complétées dans les 24 heures suivant la fin du tournoi, sinon le prix risque d\'être pénalisé. Les réclamations peuvent prendre jusqu\'à 72 heures pour être traitées.');

-- --------------------------------------------------------

--
-- Structure de la table `details_defi`
--

DROP TABLE IF EXISTS `details_defi`;
CREATE TABLE IF NOT EXISTS `details_defi` (
  `id_Statistique` int(11) NOT NULL AUTO_INCREMENT,
  `EquipeA` int(11) NOT NULL,
  `imgScore` varchar(255) COLLATE utf8_bin NOT NULL,
  `EquipeB` int(11) NOT NULL,
  `Score_finale` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_defi` int(11) NOT NULL,
  PRIMARY KEY (`id_Statistique`),
  KEY `id_defi` (`id_defi`),
  KEY `EquipeA` (`EquipeA`),
  KEY `EquipeB` (`EquipeB`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `details_defi`
--

INSERT INTO `details_defi` (`id_Statistique`, `EquipeA`, `imgScore`, `EquipeB`, `Score_finale`, `id_defi`) VALUES
(1, 1, '', 2, '', 1);

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE IF NOT EXISTS `equipe` (
  `id_Equipe` int(11) NOT NULL AUTO_INCREMENT,
  `nom_Equipe` varchar(100) COLLATE utf8_bin NOT NULL,
  `logo_Equipe` longtext COLLATE utf8_bin NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nbr_joueur_Equipe` int(11) NOT NULL,
  PRIMARY KEY (`id_Equipe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `equipe`
--

INSERT INTO `equipe` (`id_Equipe`, `nom_Equipe`, `logo_Equipe`, `date`, `nbr_joueur_Equipe`) VALUES
(1, 'Es_Gaming', 'E:\\Black Ops\\esgaming.png', '2022-02-11 19:58:43', 5),
(2, 'Fnatic', 'E:\\Black Ops\\fnatic.png', '2022-02-11 19:59:05', 5);

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `Id_Image` int(11) NOT NULL AUTO_INCREMENT,
  `Url_Image` varchar(255) COLLATE utf8_bin NOT NULL,
  `Id_jeu` int(11) NOT NULL,
  PRIMARY KEY (`Id_Image`),
  KEY `Id_jeu` (`Id_jeu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `jeu`
--

DROP TABLE IF EXISTS `jeu`;
CREATE TABLE IF NOT EXISTS `jeu` (
  `Id_Jeu` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) COLLATE utf8_bin NOT NULL,
  `description` longtext COLLATE utf8_bin NOT NULL,
  `Url` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL,
  PRIMARY KEY (`Id_Jeu`),
  KEY `id_souscat` (`id_souscat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `jeu`
--

INSERT INTO `jeu` (`Id_Jeu`, `Nom`, `description`, `Url`, `id_souscat`) VALUES
(2, 'League of Legends', 'League of Legends (abrégé LoL) est un jeu vidéo sorti en 2009 de type arène de bataille en ligne, free-to-play, développé et édité par Riot Games sur Windows et Mac OS. Le mode principal du jeu voit s\'affronter deux équipes de 5 joueurs en temps réel dans des parties d\'une durée d\'environ une demi-heure, chaque équipe occupant et défendant sa propre base sur la carte. Chacun des dix joueurs contrôle un personnage à part entière parmi les plus de 150 qui sont proposés. Ces personnages, connus sous le nom de « champions » dans le jeu, disposent de compétences uniques et d\'un style de jeu qui leur est propre. Ils gagnent en puissance au fil de la partie en amassant des points d\'expérience ainsi qu\'en achetant des objets, dans le but de battre l\'équipe adverse. L\'objectif d\'une partie est de détruire le « Nexus » ennemi, une large structure située au centre de chaque base. D\'autres modes de jeu, généralement moins compétitifs et se basant quasiment toujours sur le mode principal, sont également présents — à l\'exception de Teamfight Tactics, un auto battler sorti en 2019 sans grand rapport avec le mode principal et qui dispose de sa propre communauté.', 'https://signup.euw.leagueoflegends.com/fr/signup/redownload', 3);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

DROP TABLE IF EXISTS `joueur`;
CREATE TABLE IF NOT EXISTS `joueur` (
  `id_Joueur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_Joueur` varchar(50) COLLATE utf8_bin NOT NULL,
  `rang_Joueur` varchar(50) COLLATE utf8_bin NOT NULL,
  `Pseaudo_Joueur` varchar(25) COLLATE utf8_bin NOT NULL,
  `id_equipe` int(11) NOT NULL,
  PRIMARY KEY (`id_Joueur`),
  KEY `id_equipe` (`id_equipe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`id_Joueur`, `nom_Joueur`, `rang_Joueur`, `Pseaudo_Joueur`, `id_equipe`) VALUES
(1, 'Ali', 'Bronze', 'ali007', 1),
(2, 'Ahmed', 'Bronze', 'Ahmed009', 1);

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `id_LigneCommande` int(11) NOT NULL AUTO_INCREMENT,
  `quantite_Billet` int(11) NOT NULL,
  `prix_Billet` int(11) NOT NULL,
  `id_defi` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL,
  PRIMARY KEY (`id_LigneCommande`),
  KEY `id_defi` (`id_defi`),
  KEY `id_commande` (`id_commande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `date_message` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Contenu_message` longtext COLLATE utf8_bin NOT NULL,
  `id_cl` int(11) NOT NULL,
  `id_souscat` int(11) NOT NULL,
  PRIMARY KEY (`id_message`),
  KEY `id_cl` (`id_cl`),
  KEY `id_souscat` (`id_souscat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `replay_stream`
--

DROP TABLE IF EXISTS `replay_stream`;
CREATE TABLE IF NOT EXISTS `replay_stream` (
  `id_Replay` int(11) NOT NULL AUTO_INCREMENT,
  `nom_Replay` varchar(150) COLLATE utf8_bin NOT NULL,
  `URL_video` varchar(255) COLLATE utf8_bin NOT NULL,
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Description_Replay` longtext COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL,
  PRIMARY KEY (`id_Replay`),
  KEY `id_souscat` (`id_souscat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `skin`
--

DROP TABLE IF EXISTS `skin`;
CREATE TABLE IF NOT EXISTS `skin` (
  `Id_skin` int(11) NOT NULL AUTO_INCREMENT,
  `image_skin` varchar(255) COLLATE utf8_bin NOT NULL,
  `Id_champ` int(11) NOT NULL,
  PRIMARY KEY (`Id_skin`),
  KEY `Id_champ` (`Id_champ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `sous_categorie`
--

DROP TABLE IF EXISTS `sous_categorie`;
CREATE TABLE IF NOT EXISTS `sous_categorie` (
  `id_SousCat` int(11) NOT NULL AUTO_INCREMENT,
  `nom_SousCat` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_cat` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_SousCat`),
  KEY `id_cat` (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `sous_categorie`
--

INSERT INTO `sous_categorie` (`id_SousCat`, `nom_SousCat`, `id_cat`) VALUES
(1, 'Information', 2),
(2, 'Replay', 2),
(3, 'stratégie', 1);

-- --------------------------------------------------------

--
-- Structure de la table `stream_info`
--

DROP TABLE IF EXISTS `stream_info`;
CREATE TABLE IF NOT EXISTS `stream_info` (
  `id_Stream` int(11) NOT NULL AUTO_INCREMENT,
  `image_Stream` varchar(255) COLLATE utf8_bin NOT NULL,
  `description_Stream` longtext COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL,
  PRIMARY KEY (`id_Stream`),
  KEY `id_souscat` (`id_souscat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `video_uploade`
--

DROP TABLE IF EXISTS `video_uploade`;
CREATE TABLE IF NOT EXISTS `video_uploade` (
  `id_Vdeo` int(11) NOT NULL AUTO_INCREMENT,
  `nom_Video` varchar(75) COLLATE utf8_bin NOT NULL,
  `date_Video` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description_Video` longtext COLLATE utf8_bin NOT NULL,
  `url_Video` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL,
  `id_cl` int(11) NOT NULL,
  PRIMARY KEY (`id_Vdeo`),
  KEY `id_souscat` (`id_souscat`),
  KEY `id_cl` (`id_cl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `champion`
--
ALTER TABLE `champion`
  ADD CONSTRAINT `champion_ibfk_1` FOREIGN KEY (`Id_jeu`) REFERENCES `jeu` (`Id_Jeu`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`id_cl`) REFERENCES `client` (`id_Cl`);

--
-- Contraintes pour la table `details_defi`
--
ALTER TABLE `details_defi`
  ADD CONSTRAINT `details_defi_ibfk_3` FOREIGN KEY (`id_defi`) REFERENCES `defi` (`id_Defi`),
  ADD CONSTRAINT `details_defi_ibfk_4` FOREIGN KEY (`EquipeA`) REFERENCES `equipe` (`id_Equipe`),
  ADD CONSTRAINT `details_defi_ibfk_5` FOREIGN KEY (`EquipeB`) REFERENCES `equipe` (`id_Equipe`);

--
-- Contraintes pour la table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`Id_jeu`) REFERENCES `jeu` (`Id_Jeu`);

--
-- Contraintes pour la table `jeu`
--
ALTER TABLE `jeu`
  ADD CONSTRAINT `jeu_ibfk_1` FOREIGN KEY (`id_souscat`) REFERENCES `sous_categorie` (`id_SousCat`);

--
-- Contraintes pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `joueur_ibfk_1` FOREIGN KEY (`id_equipe`) REFERENCES `equipe` (`id_Equipe`);

--
-- Contraintes pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `lignecommande_ibfk_2` FOREIGN KEY (`id_defi`) REFERENCES `defi` (`id_Defi`),
  ADD CONSTRAINT `lignecommande_ibfk_3` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_Commande`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`id_souscat`) REFERENCES `sous_categorie` (`id_SousCat`),
  ADD CONSTRAINT `message_ibfk_3` FOREIGN KEY (`id_cl`) REFERENCES `client` (`id_Cl`);

--
-- Contraintes pour la table `replay_stream`
--
ALTER TABLE `replay_stream`
  ADD CONSTRAINT `replay_stream_ibfk_1` FOREIGN KEY (`id_souscat`) REFERENCES `sous_categorie` (`id_SousCat`);

--
-- Contraintes pour la table `skin`
--
ALTER TABLE `skin`
  ADD CONSTRAINT `skin_ibfk_1` FOREIGN KEY (`Id_champ`) REFERENCES `champion` (`Id_Champ`);

--
-- Contraintes pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  ADD CONSTRAINT `sous_categorie_ibfk_1` FOREIGN KEY (`id_cat`) REFERENCES `categorie` (`id_cat`);

--
-- Contraintes pour la table `stream_info`
--
ALTER TABLE `stream_info`
  ADD CONSTRAINT `stream_info_ibfk_1` FOREIGN KEY (`id_souscat`) REFERENCES `sous_categorie` (`id_SousCat`);

--
-- Contraintes pour la table `video_uploade`
--
ALTER TABLE `video_uploade`
  ADD CONSTRAINT `video_uploade_ibfk_2` FOREIGN KEY (`id_souscat`) REFERENCES `sous_categorie` (`id_SousCat`),
  ADD CONSTRAINT `video_uploade_ibfk_3` FOREIGN KEY (`id_cl`) REFERENCES `client` (`id_Cl`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
