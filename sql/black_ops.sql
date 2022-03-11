-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 02 mars 2022 à 10:01
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
  `pass_Cl` varchar(50) COLLATE utf8_bin NOT NULL,
  `DateNaissance_Cl` date NOT NULL,
  `Statut_Cl` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT 'offline',
  `NewPass_Cl` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_Cl`, `Pseaudo_Cl`, `Photo_Cl`, `mail_Cl`, `pass_Cl`, `DateNaissance_Cl`, `Statut_Cl`, `NewPass_Cl`) VALUES
(1, 'nadhem', 'image1.jpg', 'nadhem.jbeli@esprit.tn', '123456', '1998-06-10', 'online', 0),
(2, 'khalil', 'khemiri', 'ok', 'okk', '2025-06-30', 'barra', 5);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_Commande` int(11) NOT NULL,
  `date_commande` datetime NOT NULL DEFAULT current_timestamp(),
  `Etat_commande` varchar(15) COLLATE utf8_bin NOT NULL,
  `id_cl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_Commande`, `date_commande`, `Etat_commande`, `id_cl`) VALUES
(1, '2022-02-16 00:42:00', 'en cours...', 1),
(3, '2022-02-16 00:45:32', 'en cours...', 2);

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
(1, 'Pro_Lol', 'Bienvenue au tournoi League of Legends organisé par Black Ops. Il s\'agit d\'un tournoi 5 contre 5.\r\n\r\n', 'E:\\Black Ops\\lol.jpg', 100, '2022-02-11 19:56:48', 'League of Legends', 10, '1. Veuillez traiter l\'organisateur du tournoi et ses participants avec respect, sinon vous serez disqualifié pour comportement inapproprié.\r\n2. Veuillez vous inscrire à temps et être là au début du tournoi. Vous (ou votre équipe) avez été disqualifié en raison de votre absence au tournoi.\r\n3. Vous et les membres de votre équipe devez vous inscrire pour vous qualifier pour l\'événement.\r\n4. Vous pouvez participer à ce tournoi si votre nom d\'inscription correspond à votre surnom de jeu. S\'ils diffèrent, vous serez disqualifié.', '500 Dt.\r\nLes réclamations de prix doivent être complétées dans les 24 heures suivant la fin du tournoi, sinon le prix risque d\'être pénalisé. Les réclamations peuvent prendre jusqu\'à 72 heures pour être traitées.');

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
(3, 1, 'url', 3, '9 - 5', 1),
(4, 1, 'url', 3, '9 - 5', 1);

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
(3, 'Blac_ops2', 'url', '2022-02-16 00:00:00', 7),
(4, 'Blac_ops', 'url', '2022-02-16 00:00:00', 5);

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE `image` (
  `Id_Image` int(11) NOT NULL,
  `Url_Image` varchar(255) COLLATE utf8_bin NOT NULL,
  `Id_jeu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `image`
--

INSERT INTO `image` (`Id_Image`, `Url_Image`, `Id_jeu`) VALUES
(3, 'E:/Image/Astra.jpg', 2),
(4, 'E:/Image/Astra.jpg', 2);

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
(2, 'League of Legends', 'League of Legends (abrégé LoL) est un jeu vidéo sorti en 2009 de type arène de bataille en ligne, free-to-play, développé et édité par Riot Games sur Windows et Mac OS. Le mode principal du jeu voit s\'affronter deux équipes de 5 joueurs en temps réel dans des parties d\'une durée d\'environ une demi-heure, chaque équipe occupant et défendant sa propre base sur la carte. Chacun des dix joueurs contrôle un personnage à part entière parmi les plus de 150 qui sont proposés. Ces personnages, connus sous le nom de « champions » dans le jeu, disposent de compétences uniques et d\'un style de jeu qui leur est propre. Ils gagnent en puissance au fil de la partie en amassant des points d\'expérience ainsi qu\'en achetant des objets, dans le but de battre l\'équipe adverse. L\'objectif d\'une partie est de détruire le « Nexus » ennemi, une large structure située au centre de chaque base. D\'autres modes de jeu, généralement moins compétitifs et se basant quasiment toujours sur le mode principal, sont également présents — à l\'exception de Teamfight Tactics, un auto battler sorti en 2019 sans grand rapport avec le mode principal et qui dispose de sa propre communauté.', 'https://signup.euw.leagueoflegends.com/fr/signup/redownload', 3),
(4, 'Valorant', 'est un jeu de tir à la première personne gratuit développé et publié par Riot Games, pour Microsoft Windows. D’abord teasé sous le nom de code Project A en octobre 2019, le jeu a commencé une période de bêta fermée avec un accès limité le 7 avril 2020, suivie d’une sortie officielle le 2 juin 2020. Le développement du jeu a commencé en 2014. Valorant s’inspire de la série de jeux de tir tactiques Counter-Strike, empruntant plusieurs mécanismes tels que le menu d’achat, les motifs de pulvérisation et l’imprécision lors des déplacements.', 'https://playvalorant.com/ar-ae/', 1),
(5, 'Valorant', 'est un jeu de tir à la première personne gratuit développé et publié par Riot Games, pour Microsoft Windows. D’abord teasé sous le nom de code Project A en octobre 2019, le jeu a commencé une période de bêta fermée avec un accès limité le 7 avril 2020, suivie d’une sortie officielle le 2 juin 2020. Le développement du jeu a commencé en 2014. Valorant s’inspire de la série de jeux de tir tactiques Counter-Strike, empruntant plusieurs mécanismes tels que le menu d’achat, les motifs de pulvérisation et l’imprécision lors des déplacements.', 'https://playvalorant.com/ar-ae/', 1),
(6, 'Valorant', 'est un jeu de tir à la première personne gratuit développé et publié par Riot Games, pour Microsoft Windows. D’abord teasé sous le nom de code Project A en octobre 2019, le jeu a commencé une période de bêta fermée avec un accès limité le 7 avril 2020, suivie d’une sortie officielle le 2 juin 2020. Le développement du jeu a commencé en 2014. Valorant s’inspire de la série de jeux de tir tactiques Counter-Strike, empruntant plusieurs mécanismes tels que le menu d’achat, les motifs de pulvérisation et l’imprécision lors des déplacements.', 'https://playvalorant.com/ar-ae/', 1);

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
(2, 'Ahmed', 'Bronze', 'Ahmed009', 1),
(3, 'nadhem', 'golden', 'nadhem1', 3);

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
(1, '2022-02-12 14:28:12', 'bonsoir, je suis un nouveau membre', 1, 1),
(2, '2022-02-12 14:28:12', 'bonjour!! je suis un nouveau membre', 1, 1),
(4, '2022-02-26 13:28:12', 'hello my name is jbeli nadhem', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `replay_stream`
--

CREATE TABLE `replay_stream` (
  `id_Replay` int(11) NOT NULL,
  `nom_Replay` varchar(150) COLLATE utf8_bin NOT NULL,
  `URL_video` varchar(255) COLLATE utf8_bin NOT NULL,
  `Date` datetime NOT NULL DEFAULT current_timestamp(),
  `Description_Replay` longtext COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
(3, 'stratégie', 1);

-- --------------------------------------------------------

--
-- Structure de la table `stream_info`
--

CREATE TABLE `stream_info` (
  `id_Stream` int(11) NOT NULL,
  `image_Stream` varchar(255) COLLATE utf8_bin NOT NULL,
  `description_Stream` longtext COLLATE utf8_bin NOT NULL,
  `id_souscat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
(1, 'un video', '2022-02-12 18:58:53', 'vvv', '4.mp4', 1, 1),
(3, 'video 3', '2022-02-27 01:22:59', 'dsc', '3.mp4', 1, 1),
(4, 'un video', '2022-03-01 00:21:54', 'vvv', '2', 1, 1),
(5, 'aaa', '2022-03-01 00:22:03', 'description', '5.mp4', 1, 1),
(9, 'aa', '2022-03-01 19:08:52', 'description', '', 1, 1);

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
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `id_cl` (`id_cl`),
  ADD KEY `id_souscat` (`id_souscat`);

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
  MODIFY `id_Cl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_Commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `contact`
--
ALTER TABLE `contact`
  MODIFY `id_contact` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `defi`
--
ALTER TABLE `defi`
  MODIFY `id_Defi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `details_defi`
--
ALTER TABLE `details_defi`
  MODIFY `id_Statistique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id_Equipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `image`
--
ALTER TABLE `image`
  MODIFY `Id_Image` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `jeu`
--
ALTER TABLE `jeu`
  MODIFY `Id_Jeu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id_Joueur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  MODIFY `id_LigneCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `replay_stream`
--
ALTER TABLE `replay_stream`
  MODIFY `id_Replay` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `skin`
--
ALTER TABLE `skin`
  MODIFY `Id_skin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  MODIFY `id_SousCat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `stream_info`
--
ALTER TABLE `stream_info`
  MODIFY `id_Stream` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `video_uploade`
--
ALTER TABLE `video_uploade`
  MODIFY `id_Vdeo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
