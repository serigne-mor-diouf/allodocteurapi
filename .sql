-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 28 nov. 2023 à 15:28
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
-- Base de données : `allodocteurapi3`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

CREATE TABLE `consultation` (
  `id` bigint(20) NOT NULL,
  `allergie` varchar(255) DEFAULT NULL,
  `antecedent` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `diagnostic` varchar(255) DEFAULT NULL,
  `groupe_sanguin` varchar(255) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `poids` double DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `taille` double DEFAULT NULL,
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `specialite` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`specialite`, `id`) VALUES
('Dentiste', 5);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`) VALUES
(2);

-- --------------------------------------------------------

--
-- Structure de la table `planing`
--

CREATE TABLE `planing` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `disponibilite` int(11) DEFAULT 1,
  `medecin_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `prescription`
--

CREATE TABLE `prescription` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `medicament` varchar(255) DEFAULT NULL,
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rappel`
--

CREATE TABLE `rappel` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

CREATE TABLE `rendezvous` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT 'confirmer',
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `token`
--

CREATE TABLE `token` (
  `valeur` varchar(255) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `deconnection` datetime(6) DEFAULT NULL,
  `revoquer` bit(1) DEFAULT NULL,
  `validite` datetime(6) DEFAULT NULL,
  `utilisateur_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `account_non_expired`, `account_non_locked`, `adresse`, `age`, `credentials_non_expired`, `email`, `enabled`, `nom`, `password`, `prenom`, `sexe`, `telephone`) VALUES
(1, b'1', b'1', 'Thies', 22, b'1', 'admin@gmail.com', b'1', 'Diouf', '$2a$10$qWleAQmKdPbMfXV4n8lRP.cfW2tVsAHNwQCEtUNDzbuBiFnpZYv.a', 'Serigne Mor', 'M', '778653628'),
(2, b'1', b'1', 'Thies', 26, b'1', 'patient1@gmail.com', b'1', 'Diouf', '$2a$10$NR4ZyvKBa05QF3n6GkIj8u4aEP.P/bycKKdi/X3qjybKISvWRrWjK', 'babacar', 'm', '123456789009'),
(5, b'1', b'1', 'Thies', 24, b'1', 'medecin@gmail.com', b'1', 'Diouf', '$2a$10$nrX6ZHtX5I4b7NpWLrD0/OSci4KSJDV8aCX4ukexMXMDnT1j84CmK', 'babacr', 'm', '123456789009');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_55pqxvdmn5q8piy3nc1b3lr69` (`email`);

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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

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
-- Index pour la table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`valeur`),
  ADD KEY `FK3042esdc1xafr9ka8um420cnd` (`utilisateur_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rma38wvnqfaf66vvmi57c71lo` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `planing`
--
ALTER TABLE `planing`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FKgodqjbbtwk30kf3s0xuxklkr3` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `FK7us6be9pm4xnpnf70gx1rt39k` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `FK8hcrvaxsjxg8ln7wis56xa1y` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`);

--
-- Contraintes pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD CONSTRAINT `FKsqemgapst01xb3hxu1pv0tijr` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `FKtmrppjm6kh3grii95ximvb8f6` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

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

--
-- Contraintes pour la table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `FK3042esdc1xafr9ka8um420cnd` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
