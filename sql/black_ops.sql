-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 17 mars 2022 à 21:09
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `black_ops`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `mail_admin` varchar(254) COLLATE utf8_bin NOT NULL,
  `password_admin` varchar(50) COLLATE utf8_bin NOT NULL,
  `grade` int(1) NOT NULL,
  `NewPass` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_cat` int(11) NOT NULL,
  `nom_cat` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

CREATE TABLE `champion` (
  `Id_Champ` int(11) NOT NULL,
  `Nom_Champ` varchar(75) COLLATE utf8_bin NOT NULL,
  `description_Champ` longtext COLLATE utf8_bin NOT NULL,
  `Role_Champ` varchar(75) COLLATE utf8_bin NOT NULL,
  `Difficulte_Champ` varchar(25) COLLATE utf8_bin NOT NULL,
  `Image_Champ` longtext COLLATE utf8_bin NOT NULL,
  `Id_jeu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `champion`
--

INSERT INTO `champion` (`Id_Champ`, `Nom_Champ`, `description_Champ`, `Role_Champ`, `Difficulte_Champ`, `Image_Champ`, `Id_jeu`) VALUES
(1, 'AHRI', 'RENARD À NEUF QUEUES, Dotée d\'un lien inné avec le pouvoir naturel de Runeterra, Ahri est une Vastaya capable de modeler la magie pour en faire des orbes d\'énergie pure. Elle aime plus que tout jouer avec ses proies en manipulant leurs émotions avant de dévorer leur essence vitale. En dépit de sa nature de prédateur, Ahri n\'est pas sans empathie, car chaque âme qu\'elle absorbe l\'emplit de fragments de ses souvenirs.', 'MAGE', 'MODÉRÉE', 'E:\\Black Ops\\Ahri.jpg', 2);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_Cl` int(11) NOT NULL,
  `Pseaudo_Cl` varchar(20) COLLATE utf8_bin NOT NULL,
  `Photo_Cl` longtext COLLATE utf8_bin NOT NULL,
  `mail_Cl` varchar(100) COLLATE utf8_bin NOT NULL,
  `pass_Cl` varchar(300) COLLATE utf8_bin NOT NULL,
  `DateNaissance_Cl` date NOT NULL,
  `Statut_Cl` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT 'offline',
  `NewPass_Cl` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_Cl`, `Pseaudo_Cl`, `Photo_Cl`, `mail_Cl`, `pass_Cl`, `DateNaissance_Cl`, `Statut_Cl`, `NewPass_Cl`) VALUES
(2, 'nadhem', 'src\\Images\\Imageclient\\nadhem.png', 'nadhem', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855nadhem', '1990-01-01', 'offline', 0),
(3, 'aziz@gmail.com', 'src\\Images\\Imageclient\\aziz@gmail.com.png', 'theghost', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855aziz', '1990-01-01', 'offline', 0),
(4, 'az.tt.93@gmail.com', 'src\\Images\\Imageclient\\aziz@gmail.com.png', 'theghost', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855azizaziz', '1990-01-01', 'offline', 0),
(5, 'jbeli', 'src\\Images\\Imageclient\\jbeli.png', 'jbeli', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855jbeli', '2022-02-28', 'offline', 0),
(6, 'nadhem_jb', 'src\\Images\\Imageclient\\nadhem_jb.png', 'nadhemjbeli4@gmail.com', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855nadhem', '1998-10-06', 'offline', 0),
(7, 'marioumet', 'src\\Images\\Imageclient\\marioumet.png', 'mariem@mariem.com', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855mariem', '1999-06-10', 'offline', 0);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_Commande` int(11) NOT NULL,
  `date_commande` datetime NOT NULL DEFAULT current_timestamp(),
  `Etat_commande` varchar(24) COLLATE utf8_bin NOT NULL,
  `id_cl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_Commande`, `date_commande`, `Etat_commande`, `id_cl`) VALUES
(1, '2022-03-09 10:51:04', 'Non Traite', 2);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_commentaire` int(11) NOT NULL,
  `contenu_commentaire` varchar(250) COLLATE utf8_bin NOT NULL,
  `date_commentaire` datetime NOT NULL DEFAULT current_timestamp(),
  `id_livestream` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_commentaire`, `contenu_commentaire`, `date_commentaire`, `id_livestream`) VALUES
(95, 'hello hello hello', '2022-03-08 01:06:16', 12),
(96, 'cscscsc', '2022-03-08 01:06:18', 12),
(97, 'cscscscs', '2022-03-08 01:06:20', 12),
(98, 'scscscscs', '2022-03-08 01:06:22', 12),
(99, 'hello', '2022-03-08 01:08:26', 12),
(100, 'how are y', '2022-03-08 01:08:31', 12),
(101, 'fdfdfdf', '2022-03-09 01:39:34', 12),
(102, 'dffdfdfdfdf', '2022-03-09 01:39:40', 12),
(103, 'dfdfdfdf', '2022-03-09 01:40:00', 12),
(104, 'dfdsfdsfs', '2022-03-09 01:44:09', 12),
(105, 'fdsfdsfdsf', '2022-03-09 01:44:11', 12),
(106, 'dfdfd', '2022-03-09 01:44:27', 12),
(107, 'hghghgh', '2022-03-09 01:48:28', 12),
(108, 'grgrgrg', '2022-03-09 01:48:32', 12),
(109, 'ghghg', '2022-03-09 03:38:15', 12),
(110, 'fd', '2022-03-09 03:42:20', 12),
(111, 'kjkj', '2022-03-09 10:40:48', 12),
(112, 'fgfgfg', '2022-03-09 10:43:07', 12),
(113, 'ikikik', '2022-03-09 10:49:08', 12),
(114, 'idqmofdj', '2022-03-10 07:09:43', 12),
(115, 'nice video', '2022-03-14 23:13:44', 12);

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE `contact` (
  `id_contact` int(11) NOT NULL,
  `np_contact` varchar(75) COLLATE utf8_bin NOT NULL,
  `mail_contact` varchar(100) COLLATE utf8_bin NOT NULL,
  `message` longtext COLLATE utf8_bin NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `contact`
--

INSERT INTO `contact` (`id_contact`, `np_contact`, `mail_contact`, `message`, `date`) VALUES
(1, 'Mohamed Ali', 'anonyme_anonyme@yahoo.fr', ' mais seules huit d\'entre elles sont considérées comme communes : le Dauphin bleu et blanc, le Dauphin commun à bec court, le Grand Dauphin, le Dauphin de Risso, le Globicéphale noir, la Baleine à bec de Cuvier, le Grand Cachalot et le Rorqual commun. Leur répartition varie beaucoup selon les régions et leur abondance et leur diversité semblent maximales dans le bassin corso-liguro-provençal, où les cétacés sont protégés par le Sanctuaire Pelagos depuis 2002.\r\n\r\nRéputés pour leur intelligence à certains égards proche de celle des humains, les cétacés font l\'objet de mesures de protection d\'autant plus importantes que leur cycle biologique lent les rend vulnérables aux nombreuses menaces qui les affectent en Méditerranée. En effet, la densité de peuplement et de circulation dans le bassin méditerranéen expose les espèces marines, et particulièrement les gros mammifères marins comme les cétacés, à une large gamme de menaces qui nécessitent des mesures de conservation spécifiques.', '2022-02-11 19:03:22');

-- --------------------------------------------------------

--
-- Structure de la table `defi`
--

CREATE TABLE `defi` (
  `id_Defi` int(11) NOT NULL,
  `nom_Defi` varchar(75) COLLATE utf8_bin NOT NULL,
  `Description_Defi` varchar(255) COLLATE utf8_bin NOT NULL,
  `img_Defi` longtext COLLATE utf8_bin NOT NULL,
  `prix_Defi` int(11) NOT NULL,
  `date_Defi` datetime NOT NULL DEFAULT current_timestamp(),
  `jeu_Defi` varchar(100) COLLATE utf8_bin NOT NULL,
  `nbr_equipe_Defi` int(11) NOT NULL,
  `Régle_Defi` varchar(1000) COLLATE utf8_bin NOT NULL,
  `Recompense_Defi` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `defi`
--

INSERT INTO `defi` (`id_Defi`, `nom_Defi`, `Description_Defi`, `img_Defi`, `prix_Defi`, `date_Defi`, `jeu_Defi`, `nbr_equipe_Defi`, `Régle_Defi`, `Recompense_Defi`) VALUES
(1, 'Pro_Lol', 'Bienvenue au tournoi League of Legends organisé par Black Ops. Il s\'agit d\'un tournoi 5 contre 5.\r\n\r\n', 'src/black_ops/MediaStream/fd30e.jpg', 100, '2022-02-11 19:56:48', 'League of Legends', 10, '1. Veuillez traiter l\'organisateur du tournoi et ses participants avec respect, sinon vous serez disqualifié pour comportement inapproprié.\r\n2. Veuillez vous inscrire à temps et être là au début du tournoi. Vous (ou votre équipe) avez été disqualifié en raison de votre absence au tournoi.\r\n3. Vous et les membres de votre équipe devez vous inscrire pour vous qualifier pour l\'événement.\r\n4. Vous pouvez participer à ce tournoi si votre nom d\'inscription correspond à votre surnom de jeu. S\'ils diffèrent, vous serez disqualifié.', '500 Dt.\r\nLes réclamations de prix doivent être complétées dans les 24 heures suivant la fin du tournoi, sinon le prix risque d\'être pénalisé. Les réclamations peuvent prendre jusqu\'à 72 heures pour être traitées.'),
(3, 'Pro_CSGO', 'Bienvenue au tournoi CS GO organisé par Black Ops. Il s\'agit d\'un tournoi 5 contre 5.\r\n\r\n', 'src/black_ops/MediaStream/csgo.jpg', 100, '2022-02-11 19:56:48', 'League of Legends', 10, '1. Veuillez traiter l\'organisateur du tournoi et ses participants avec respect, sinon vous serez disqualifié pour comportement inapproprié.\r\n2. Veuillez vous inscrire à temps et être là au début du tournoi. Vous (ou votre équipe) avez été disqualifié en raison de votre absence au tournoi.\r\n3. Vous et les membres de votre équipe devez vous inscrire pour vous qualifier pour l\'événement.\r\n4. Vous pouvez participer à ce tournoi si votre nom d\'inscription correspond à votre surnom de jeu. S\'ils diffèrent, vous serez disqualifié.', '500 Dt.\r\nLes réclamations de prix doivent être complétées dans les 24 heures suivant la fin du tournoi, sinon le prix risque d\'être pénalisé. Les réclamations peuvent prendre jusqu\'à 72 heures pour être traitées.'),
(4, 'CsGo Tournoi', 'Defi Cs go contient 10 equipes  ', 'src\\Images\\Image defi\\CsGo Tournoi.png', 100, '2022-03-09 00:00:00', 'Cs Go', 10, 'pas de tricherie', '10000'),
(5, 'Pro Lol v 2', 'Defi League of Leagends', 'src\\Images\\Image defi\\Pro Lol v 2.png', 120, '2022-03-09 00:00:00', 'League of leagends', 10, 'Pas de noob champions ', '12000'),
(6, 'Valorant E_Gaming', 'Defi Valorant 10 Equipes ', 'src\\Images\\Image defi\\Valorant E_Gaming.png', 450, '2022-03-09 00:00:00', 'Valorant', 10, 'indertid de jouer avec Reyna', '4500'),
(7, 'PES_Esport', 'PES E_Sport 2022', 'src\\Images\\Image defi\\PES_Esport.png', 50, '2022-03-09 00:00:00', 'Pro Soccer Evolution', 10, 'tous est autorise', '1500');

-- --------------------------------------------------------

--
-- Structure de la table `details_defi`
--

CREATE TABLE `details_defi` (
  `id_Statistique` int(11) NOT NULL,
  `EquipeA` int(11) NOT NULL,
  `imgScore` varchar(255) COLLATE utf8_bin NOT NULL,
  `EquipeB` int(11) NOT NULL,
  `Score_finale` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_defi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `details_defi`
--

INSERT INTO `details_defi` (`id_Statistique`, `EquipeA`, `imgScore`, `EquipeB`, `Score_finale`, `id_defi`) VALUES
(1, 1, '', 2, '', 1);

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `id_Equipe` int(11) NOT NULL,
  `nom_Equipe` varchar(100) COLLATE utf8_bin NOT NULL,
  `logo_Equipe` longtext COLLATE utf8_bin NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `nbr_joueur_Equipe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

CREATE TABLE `image` (
  `Id_Image` int(11) NOT NULL,
  `Url_Image` varchar(255) COLLATE utf8_bin NOT NULL,
  `Id_jeu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `jeu`
--

CREATE TABLE `jeu` (
  `Id_Jeu` int(11) NOT NULL,
  `Nom` varchar(100) COLLATE utf8_bin NOT NULL,
  `description` longtext COLLATE utf8_bin NOT NULL,
  `Url` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `jeu`
--

INSERT INTO `jeu` (`Id_Jeu`, `Nom`, `description`, `Url`, `id_souscat`) VALUES
(2, 'League of Legends', 'League of Legends (abrégé LoL) est un jeu vidéo sorti en 2009 de type arène de bataille en ligne, free-to-play, développé et édité par Riot Games sur Windows et Mac OS. Le mode principal du jeu voit s\'affronter deux équipes de 5 joueurs en temps réel dans des parties d\'une durée d\'environ une demi-heure, chaque équipe occupant et défendant sa propre base sur la carte. Chacun des dix joueurs contrôle un personnage à part entière parmi les plus de 150 qui sont proposés. Ces personnages, connus sous le nom de « champions » dans le jeu, disposent de compétences uniques et d\'un style de jeu qui leur est propre. Ils gagnent en puissance au fil de la partie en amassant des points d\'expérience ainsi qu\'en achetant des objets, dans le but de battre l\'équipe adverse. L\'objectif d\'une partie est de détruire le « Nexus » ennemi, une large structure située au centre de chaque base. D\'autres modes de jeu, généralement moins compétitifs et se basant quasiment toujours sur le mode principal, sont également présents — à l\'exception de Teamfight Tactics, un auto battler sorti en 2019 sans grand rapport avec le mode principal et qui dispose de sa propre communauté.', 'https://signup.euw.leagueoflegends.com/fr/signup/redownload', 3);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `id_Joueur` int(11) NOT NULL,
  `nom_Joueur` varchar(50) COLLATE utf8_bin NOT NULL,
  `rang_Joueur` varchar(50) COLLATE utf8_bin NOT NULL,
  `Pseaudo_Joueur` varchar(25) COLLATE utf8_bin NOT NULL,
  `id_equipe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

CREATE TABLE `lignecommande` (
  `id_LigneCommande` int(11) NOT NULL,
  `quantite_Billet` int(11) NOT NULL,
  `prix_Billet` int(11) NOT NULL,
  `id_defi` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `lignecommande`
--

INSERT INTO `lignecommande` (`id_LigneCommande`, `quantite_Billet`, `prix_Billet`, `id_defi`, `id_commande`) VALUES
(1, 1, 100, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `live_stream`
--

CREATE TABLE `live_stream` (
  `id_LiveStream` int(11) NOT NULL,
  `Nom_LiveStream` varchar(100) COLLATE utf8_bin NOT NULL,
  `Path_LiveStream` varchar(500) COLLATE utf8_bin NOT NULL,
  `Visibilite_LiveStream` varchar(150) COLLATE utf8_bin NOT NULL,
  `id_defi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `live_stream`
--

INSERT INTO `live_stream` (`id_LiveStream`, `Nom_LiveStream`, `Path_LiveStream`, `Visibilite_LiveStream`, `id_defi`) VALUES
(1, 'Fnatic vs Invictus Gaming ', 'xZLFVV2qxQQ', 'Afficher', 1),
(12, 'Cloud 9 vs Team Liquid', 'Weu3oyYb680', 'En cours', 1),
(23, 'Samsung vs SK Telecom', 'MohnyOOxv2I', 'En cours', 3),
(24, 'DAMWON Gaming vs Suning G1', 'MPlGWKm-jdg', 'Masquer', 1);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id_message` int(11) NOT NULL,
  `date_message` timestamp NOT NULL DEFAULT current_timestamp(),
  `Contenu_message` longtext COLLATE utf8_bin NOT NULL,
  `id_cl` int(11) NOT NULL,
  `id_souscat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id_message`, `date_message`, `Contenu_message`, `id_cl`, `id_souscat`) VALUES
(1, '2022-03-09 07:34:52', 'dsdsqdsqdsq', 2, 1),
(2, '2022-03-09 07:34:57', 'dsqdsqdqd', 2, 1),
(3, '2022-03-09 08:08:09', 'qui peut jouer avec nous league of legends ?', 5, 1),
(4, '2022-03-09 09:49:39', 'mesg', 2, 1),
(5, '2022-03-11 00:17:05', 'bonjour', 6, 1),
(6, '2022-03-12 20:04:41', 'come here abd rahmen', 2, 1),
(7, '2022-03-14 22:23:28', 'i love you nadhem', 7, 1);

-- --------------------------------------------------------

--
-- Structure de la table `mode`
--

CREATE TABLE `mode` (
  `id_mode` int(11) NOT NULL,
  `dark_mode` int(11) NOT NULL DEFAULT 0,
  `light_mode` int(11) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `mode`
--

INSERT INTO `mode` (`id_mode`, `dark_mode`, `light_mode`) VALUES
(1, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `replay_stream`
--

CREATE TABLE `replay_stream` (
  `id_Replay` int(11) NOT NULL,
  `nom_Replay` varchar(150) COLLATE utf8_bin NOT NULL,
  `URL_video` varchar(255) COLLATE utf8_bin NOT NULL,
  `Date` datetime NOT NULL DEFAULT current_timestamp(),
  `Description_Replay` varchar(300) COLLATE utf8_bin NOT NULL,
  `vues_Replay` int(11) NOT NULL DEFAULT 0,
  `id_souscat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `replay_stream`
--

INSERT INTO `replay_stream` (`id_Replay`, `nom_Replay`, `URL_video`, `Date`, `Description_Replay`, `vues_Replay`, `id_souscat`) VALUES
(2, 'Fnatic vs Team BDS', 'FNC_vs_BDS.mp4', '2022-02-12 15:51:41', 'Fnatic vs Team BDS Highlights | LEC Spring 2022 Week 8 Day 1 | FNC vs BDS', 11, 2),
(4, 'Fnatic vs Illuminar', 'Fnatic vs Illuminar.mp4', '2022-02-12 16:26:24', 'HIGHLIGHTS - PGL Major Qualifier | CSGO', 3, 2),
(5, 'Team Liquid vs Cloud9', 'Team Liquid vs Cloud9.mp4', '2022-02-28 22:12:36', 'Team Liquid vs Cloud9 Highlights | LCS Spring 2022 Week 2 Day 1 | TL vs C9', 0, 2),
(10, 'Fnatic vs Misfits Gaming', 'Fnatic vs Misfits Gaming.mp4', '2022-03-08 00:41:21', 'Fnatic vs Misfits Gaming Highlights | LEC Spring 2022 Week 8 Day 2 | FNC vs MSF', 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `skin`
--

CREATE TABLE `skin` (
  `Id_skin` int(11) NOT NULL,
  `image_skin` varchar(255) COLLATE utf8_bin NOT NULL,
  `Id_champ` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `sous_categorie`
--

CREATE TABLE `sous_categorie` (
  `id_SousCat` int(11) NOT NULL,
  `nom_SousCat` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_cat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `sous_categorie`
--

INSERT INTO `sous_categorie` (`id_SousCat`, `nom_SousCat`, `id_cat`) VALUES
(1, 'Information', 2),
(2, 'Replay', 2),
(3, 'stratégie', 1),
(4, 'Live', 2);

-- --------------------------------------------------------

--
-- Structure de la table `stream_info`
--

CREATE TABLE `stream_info` (
  `id_Stream` int(11) NOT NULL,
  `nom_Stream` varchar(75) COLLATE utf8_bin NOT NULL,
  `image_Stream` varchar(255) COLLATE utf8_bin NOT NULL,
  `description_Stream` longtext COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `stream_info`
--

INSERT INTO `stream_info` (`id_Stream`, `nom_Stream`, `image_Stream`, `description_Stream`, `id_souscat`) VALUES
(1, 'Twitch studio beta', 'twitch-4aRVhk.png', 'Twitch a été créé afin d\'avoir un site consacré à la branche jeux vidéo de Justin.tv, qui était la partie la plus populaire du service. Le nouveau site va alors surpasser son parent, qui ferme en août 2014, pour que l\'entreprise se concentre essentiellement sur Twitch.', 1),
(30, 'Open Broadcaster Software', '600px-Open_Broadcaster_Software_Logo.png', 'Le logiciel de streaming Open Broadcaster Software est une excellente option pour la diffusion en live. C’est un projet open-source qui allie puissance et flexibilité et surtout, il a été conçu par la communauté du streaming afin d’être en phase avec ses besoins !\n', 1),
(31, 'XSplit Gamecaster', 'xsplit-logo-vector.png', 'Le logiciel XSplit Gamecaster est de très haute qualité. Il s’agit d’une version gratuite du logiciel haut-de-gamme XSplit Broadcaster. Très élégant, il bénéficie de nombreuses de mises à jour, sans être très coûteux.', 1),
(32, 'Lightstream ', 'images.png', 'Lightstream est un logiciel de live streaming par navigateur. Selon leur équipe marketing, ils sont « les Google Docs de la production vidéo en direct ». Le studio Lightstream exploite la puissance du Cloud pour votre streaming.\nCe logiciel (gratuit) offre des tonnes de fonctionnalités intéressantes. Il propose un hébergement pour les guests.', 1),
(33, 'Streamlabs OBS', 'unnamed.png', 'Streamlabs OBS a marié deux services populaires pour créer un outil puissant. Il s’est appuyé sur les outils de streaming Streamlabs et a intégré le logiciel open source OBS (que les gamers utilisaient déjà en masse) pour créer un seul et unique logiciel !', 1),
(34, 'Wirecast', 'wirecast-frPMDM.png', 'Que vous utilisiez leur version Studio ou Pro, le logiciel Wirecast édité par Telestream est une excellente option pour vos besoins en streaming.', 1);

-- --------------------------------------------------------

--
-- Structure de la table `video_uploade`
--

CREATE TABLE `video_uploade` (
  `id_Vdeo` int(11) NOT NULL,
  `nom_Video` varchar(75) COLLATE utf8_bin NOT NULL,
  `date_Video` datetime NOT NULL DEFAULT current_timestamp(),
  `description_Video` longtext COLLATE utf8_bin NOT NULL,
  `url_Video` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL,
  `id_cl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `video_uploade`
--

INSERT INTO `video_uploade` (`id_Vdeo`, `nom_Video`, `date_Video`, `description_Video`, `url_Video`, `id_souscat`, `id_cl`) VALUES
(1, 'hey', '2022-03-09 08:55:40', '', '', 1, 2),
(2, 'hey', '2022-03-09 08:55:52', 'des', '2.mp4', 1, 2),
(3, 'valo', '2022-03-09 09:06:02', 'jeux en ligne 5v5', '3.mp4', 1, 5),
(5, 'video', '2022-03-14 23:11:41', 'descriptionnnn', '', 1, 2),
(6, 'dsqoifj', '2022-03-14 23:16:30', 'osjdqoj', '', 1, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `mail_admin` (`mail_admin`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_cat`);

--
-- Index pour la table `champion`
--
ALTER TABLE `champion`
  ADD PRIMARY KEY (`Id_Champ`),
  ADD KEY `Id_Jeu` (`Id_jeu`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_Cl`),
  ADD UNIQUE KEY `Pseaudo` (`Pseaudo_Cl`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_Commande`),
  ADD KEY `id_cl` (`id_cl`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_commentaire`),
  ADD KEY `id_livestream` (`id_livestream`);

--
-- Index pour la table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id_contact`);

--
-- Index pour la table `defi`
--
ALTER TABLE `defi`
  ADD PRIMARY KEY (`id_Defi`),
  ADD UNIQUE KEY `nom_defi` (`nom_Defi`);

--
-- Index pour la table `details_defi`
--
ALTER TABLE `details_defi`
  ADD PRIMARY KEY (`id_Statistique`),
  ADD KEY `id_defi` (`id_defi`),
  ADD KEY `EquipeA` (`EquipeA`),
  ADD KEY `EquipeB` (`EquipeB`);

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id_Equipe`);

--
-- Index pour la table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`Id_Image`),
  ADD KEY `Id_jeu` (`Id_jeu`);

--
-- Index pour la table `jeu`
--
ALTER TABLE `jeu`
  ADD PRIMARY KEY (`Id_Jeu`),
  ADD KEY `id_souscat` (`id_souscat`);

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id_Joueur`),
  ADD KEY `id_equipe` (`id_equipe`);

--
-- Index pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD PRIMARY KEY (`id_LigneCommande`),
  ADD KEY `id_defi` (`id_defi`),
  ADD KEY `id_commande` (`id_commande`);

--
-- Index pour la table `live_stream`
--
ALTER TABLE `live_stream`
  ADD PRIMARY KEY (`id_LiveStream`),
  ADD KEY `id_defi` (`id_defi`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `id_cl` (`id_cl`),
  ADD KEY `id_souscat` (`id_souscat`);

--
-- Index pour la table `mode`
--
ALTER TABLE `mode`
  ADD PRIMARY KEY (`id_mode`);

--
-- Index pour la table `replay_stream`
--
ALTER TABLE `replay_stream`
  ADD PRIMARY KEY (`id_Replay`),
  ADD KEY `id_souscat` (`id_souscat`);

--
-- Index pour la table `skin`
--
ALTER TABLE `skin`
  ADD PRIMARY KEY (`Id_skin`),
  ADD KEY `Id_champ` (`Id_champ`);

--
-- Index pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  ADD PRIMARY KEY (`id_SousCat`),
  ADD KEY `id_cat` (`id_cat`);

--
-- Index pour la table `stream_info`
--
ALTER TABLE `stream_info`
  ADD PRIMARY KEY (`id_Stream`),
  ADD KEY `id_souscat` (`id_souscat`);

--
-- Index pour la table `video_uploade`
--
ALTER TABLE `video_uploade`
  ADD PRIMARY KEY (`id_Vdeo`),
  ADD KEY `id_souscat` (`id_souscat`),
  ADD KEY `id_cl` (`id_cl`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `champion`
--
ALTER TABLE `champion`
  MODIFY `Id_Champ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_Cl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_Commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_commentaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT pour la table `contact`
--
ALTER TABLE `contact`
  MODIFY `id_contact` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `defi`
--
ALTER TABLE `defi`
  MODIFY `id_Defi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `details_defi`
--
ALTER TABLE `details_defi`
  MODIFY `id_Statistique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id_Equipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `image`
--
ALTER TABLE `image`
  MODIFY `Id_Image` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `jeu`
--
ALTER TABLE `jeu`
  MODIFY `Id_Jeu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id_Joueur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  MODIFY `id_LigneCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `live_stream`
--
ALTER TABLE `live_stream`
  MODIFY `id_LiveStream` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `mode`
--
ALTER TABLE `mode`
  MODIFY `id_mode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `replay_stream`
--
ALTER TABLE `replay_stream`
  MODIFY `id_Replay` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `skin`
--
ALTER TABLE `skin`
  MODIFY `Id_skin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  MODIFY `id_SousCat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `stream_info`
--
ALTER TABLE `stream_info`
  MODIFY `id_Stream` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `video_uploade`
--
ALTER TABLE `video_uploade`
  MODIFY `id_Vdeo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_livestream`) REFERENCES `live_stream` (`id_LiveStream`);

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
-- Contraintes pour la table `live_stream`
--
ALTER TABLE `live_stream`
  ADD CONSTRAINT `live_stream_ibfk_1` FOREIGN KEY (`id_defi`) REFERENCES `defi` (`id_Defi`);

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
