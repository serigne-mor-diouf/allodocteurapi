-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 19 nov. 2023 à 01:11
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `allodocteurapi`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `age` int(11) NOT NULL,
  `statut` int(11) DEFAULT 1,
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `profil` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`age`, `statut`, `id`, `adresse`, `email`, `nom`, `password`, `prenom`, `profil`, `sexe`, `telephone`) VALUES
(22, 1, 1, 'Thies', 'dioufserigne@gmail.com', 'Diouf', 'sQnzu7wkTrgkQZF+0G1hi5AI3Qmzvv0bXgc5THBqi7mAsdd4Xll27ASbRt9fEyavWi6m0QP9B8lThf+rDKy8hg==', 'Serigne Mor', 'admin', 'M', '778653628');

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

CREATE TABLE `consultation` (
  `poids` double DEFAULT NULL,
  `taille` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `allergie` varchar(255) DEFAULT NULL,
  `antecedent` varchar(255) DEFAULT NULL,
  `diagnostic` varchar(255) DEFAULT NULL,
  `groupe_sanguin` varchar(255) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `consultation`
--

INSERT INTO `consultation` (`poids`, `taille`, `date`, `id`, `medecin_id`, `patient_id`, `allergie`, `antecedent`, `diagnostic`, `groupe_sanguin`, `motif`, `profession`) VALUES
(100, 1.75, '2024-12-22 07:00:22.000000', 2, 1, 1, 'allergie', 'antecedent', 'faible poids', 'A+', 'motif de rv ', 'developpeur'),
(100, 1.9, '2024-12-22 07:00:22.000000', 3, 1, 1, 'geste de ', 'toujous faible', 'manque de sang', 'O-', 'cancert', 'logisticien');

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `age` int(11) NOT NULL,
  `statut` int(11) DEFAULT 1,
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `profil` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`age`, `statut`, `id`, `adresse`, `email`, `nom`, `password`, `prenom`, `profil`, `sexe`, `specialite`, `telephone`) VALUES
(23, 1, 1, 'shdhd', 'diouf@gmail.com', 'diouf', 'diouf', 'Abba', 'medecin', 'm', 'Dentiste', '234567890');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `age` int(11) NOT NULL,
  `statut` int(11) DEFAULT 1,
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `profil` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`age`, `statut`, `id`, `adresse`, `email`, `nom`, `password`, `prenom`, `profil`, `sexe`, `telephone`) VALUES
(36, 1, 1, 'thies', 'sarr@gmail.com', 'Babacar', 'sarr', 'Sarr', 'patient', 'm', '1234567890');

-- --------------------------------------------------------

--
-- Structure de la table `planing`
--

CREATE TABLE `planing` (
  `disponibilite` int(11) DEFAULT 1,
  `date` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `medecin_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `prescription`
--

CREATE TABLE `prescription` (
  `date` date DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `medicament` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `prescription`
--

INSERT INTO `prescription` (`date`, `id`, `medecin_id`, `patient_id`, `description`, `medicament`) VALUES
('2023-11-18', 3, 1, 1, 'DESCRIPTION DU medecament', 'effaralgant'),
('2023-11-18', 9, 1, 1, 'DESCRIPTION DU ciro', 'ciro');

-- --------------------------------------------------------

--
-- Structure de la table `rappel`
--

CREATE TABLE `rappel` (
  `date` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

CREATE TABLE `rendezvous` (
  `date` datetime(6) DEFAULT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT 'confirmer'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_c0r9atamxvbhjjvy5j8da1kam` (`email`);

--
-- Index pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8hcrvaxsjxg8ln7wis56xa1y` (`medecin_id`),
  ADD KEY `FK7us6be9pm4xnpnf70gx1rt39k` (`patient_id`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3wt7cr5hhsrvfeip4e1xwr23a` (`email`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bawli8xm92f30ei6x9p3h8eju` (`email`);

--
-- Index pour la table `planing`
--
ALTER TABLE `planing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpvkjotmrr4s21unf4vfjy6k0v` (`medecin_id`);

--
-- Index pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeer3yabhl4ic35gt51kyn7dug` (`medecin_id`),
  ADD KEY `FKqrlh184tfvdi95erwl65p4xj3` (`patient_id`);

--
-- Index pour la table `rappel`
--
ALTER TABLE `rappel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjpm83qfm95mxcj3vt30d2jb6b` (`patient_id`);

--
-- Index pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK99rj0yecfqe4pm326ns3wggll` (`medecin_id`),
  ADD KEY `FKlpgg8chyu99ff84cjxwj4sea6` (`patient_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `planing`
--
ALTER TABLE `planing`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `FK7us6be9pm4xnpnf70gx1rt39k` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `FK8hcrvaxsjxg8ln7wis56xa1y` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`);

--
-- Contraintes pour la table `planing`
--
ALTER TABLE `planing`
  ADD CONSTRAINT `FKpvkjotmrr4s21unf4vfjy6k0v` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`);

--
-- Contraintes pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `FKeer3yabhl4ic35gt51kyn7dug` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`),
  ADD CONSTRAINT `FKqrlh184tfvdi95erwl65p4xj3` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Contraintes pour la table `rappel`
--
ALTER TABLE `rappel`
  ADD CONSTRAINT `FKjpm83qfm95mxcj3vt30d2jb6b` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Contraintes pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD CONSTRAINT `FK99rj0yecfqe4pm326ns3wggll` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`),
  ADD CONSTRAINT `FKlpgg8chyu99ff84cjxwj4sea6` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
