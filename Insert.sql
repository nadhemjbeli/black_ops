
INSERT INTO `defi` (`id_Defi`, `nom_Defi`, `Description_Defi`, `img_Defi`, `prix_Defi`, `date_Defi`, `jeu_Defi`, `nbr_equipe_Defi`, `Régle_Defi`, `Recompense_Defi`) VALUES
(1, 'CsGo Tournoi', 'Defi Cs go contient 10 equipes  ', 'src\\Images\\Image defi\\CsGo Tournoi.png', 100, '2022-03-09 00:00:00', 'Cs Go', 10, 'pas de tricherie', '10000'),
(2, 'Pro Lol v 2', 'Defi League of Leagends', 'src\\Images\\Image defi\\Pro Lol v 2.png', 120, '2022-03-09 00:00:00', 'League of leagends', 10, 'Pas de noob champions ', '12000'),
(3, 'Valorant E_Gaming', 'Defi Valorant 10 Equipes ', 'src\\Images\\Image defi\\Valorant E_Gaming.png', 450, '2022-03-09 00:00:00', 'Valorant', 10, 'indertid de jouer avec Reyna', '4500'),
(4, 'PES_Esport', 'PES E_Sport 2022', 'src\\Images\\Image defi\\PES_Esport.png', 50, '2022-03-09 00:00:00', 'Pro Soccer Evolution', 10, 'tous est autorise', '1500');

INSERT INTO `equipe` (`id_Equipe`, `nom_Equipe`, `logo_Equipe`, `date`, `nbr_joueur_Equipe`) VALUES
(1, 'PES_FCB', 'src\\Images\\Logo_Equipe\\PES_FCB.png', '2022-03-09 00:00:00', 2),
(2, 'RMA', 'src\\Images\\Logo_Equipe\\RMA.png', '2022-03-09 00:00:00', 2),
(3, 'BayernTn', 'src\\Images\\Logo_Equipe\\BayernTn.png', '2022-03-09 00:00:00', 2),
(4, 'RedsTn', 'src\\Images\\Logo_Equipe\\RedsTn.png', '2022-03-09 00:00:00', 2),
(5, 'C9', 'src\\Images\\Logo_Equipe\\C9.png', '2022-03-09 00:00:00', 5),
(6, 'C_Gaming', 'src\\Images\\Logo_Equipe\\C_Gaming.png', '2022-03-09 00:00:00', 5),
(7, 'Navi', 'src\\Images\\Logo_Equipe\\Navi.png', '2022-03-09 00:00:00', 5),
(8, 'TeamLequid', 'src\\Images\\Logo_Equipe\\TeamLequid.png', '2022-03-09 00:00:00', 5),
(9, 'Fnatic', 'src\\Images\\Logo_Equipe\\Fnatic.png', '2022-03-09 00:00:00', 5),
(10, 'Envy', 'src\\Images\\Logo_Equipe\\Envy.png', '2022-03-09 00:00:00', 5),
(11, 'cent', 'src\\Images\\Logo_Equipe\\cent.png', '2022-03-09 00:00:00', 5),
(12, 'V1', 'src\\Images\\Logo_Equipe\\V1.png', '2022-03-09 00:00:00', 5),
(13, 'Gambit', 'src\\Images\\Logo_Equipe\\Gambit.png', '2022-03-09 00:00:00', 5),
(14, 'Samsung SSG', 'src\\Images\\Logo_Equipe\\Samsung SSG.png', '2022-03-09 00:00:00', 5),
(15, 'Telecome T1', 'src\\Images\\Logo_Equipe\\Telecome T1.png', '2022-03-09 00:00:00', 5),
(16, '100T', 'src\\Images\\Logo_Equipe\\100T.png', '2022-03-09 00:00:00', 5);

INSERT INTO `details_defi` (`id_Statistique`, `EquipeA`, `imgScore`, `EquipeB`, `Score_finale`, `id_defi`) VALUES
(1, 5, 'src\\Images\\Matches\\C9 vs C_Gaming.png', 6, '13-6', 1),
(2, 7, 'src\\Images\\Matches\\Navi vs TeamLequid.png', 8, '1-4', 1),
(3, 1, 'src\\Images\\Matches\\PES_FCB vs RMA.png', 2, '3-2', 4),
(4, 3, 'src\\Images\\Matches\\BayernTn vs RedsTn.png', 4, '3-2', 4),
(5, 16, 'src\\Images\\Matches\\100T vs Gambit.png', 13, '15-31', 2),
(6, 14, 'src\\Images\\Matches\\Samsung SSG vs Telecome T1.png', 15, '39-38', 2),
(7, 12, 'src\\Images\\Matches\\V1 vs cent.png', 11, '13-8', 3),
(8, 10, 'src\\Images\\Matches\\Envy vs Fnatic.png', 9, '10-13', 3);

INSERT INTO `joueur` (`id_Joueur`, `nom_Joueur`, `rang_Joueur`, `Pseaudo_Joueur`, `id_equipe`) VALUES
(1, 'Ali', 'Gold', 'Ali001', 5),
(2, 'ALex', 'Gold', 'ALEXx', 5),
(3, 'Daruin', 'Gold', 'DC9', 5),
(4, 'Chris', 'Gold', 'Crisss', 5),
(5, 'Amanda', 'Radiant', 'A_Manda', 6),
(6, 'Burc', 'Radiant', 'WAyn_bruc', 6),
(7, 'Xavi', 'Radiant', 'XAXVXI', 6),
(8, 'Ineista', 'Radiant', 'XAXVXI', 6),
(9, 'MohamedSalah', 'Pro', 'Mo_Salah', 4),
(10, 'Seido_Mane', 'Pro', 'Seido', 4),
(11, 'Livandowski', 'Pro', 'Liva', 3),
(12, 'Muller', 'Pro', 'Muller_10', 3),
(13, 'Messi', 'Pro', 'LM10', 1),
(14, 'Neymar', 'Pro', 'JR_N', 1),
(15, 'Cristiano', 'Pro', 'CR7', 2),
(16, 'Bale', 'Pro', 'GB11', 2),
(17, 'Faker', 'Challanger', 'FakerT1', 15),
(18, 'bengi', 'Challanger', 'begiT1', 15);