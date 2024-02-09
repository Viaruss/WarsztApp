--CREATE database Warsztat;
USE Warsztat;

Drop table Zlecenia;
Drop table Dane_pojazdu;
Drop table Dane_Klienta;
Drop table Dane_mechanikow;
Drop table Specjalizacje;
Drop table Dane_Kont;

CREATE TABLE Specjalizacje (
  ID_Specjalizacji INT PRIMARY KEY AUTO_INCREMENT,
  Nazwa VARCHAR(50) NOT NULL
);

CREATE TABLE Zlecenia (
  ID_Zlecenia INT PRIMARY KEY AUTO_INCREMENT,
  ID_Pojazdu INT,
  ID_Klienta INT,
  ID_Mechanika INT,
  Opis_uszkodzen TEXT,
  Zakres_Napraw TEXT,
  Szacowany_Koszt_Naprawy DECIMAL(10,2)
);

CREATE TABLE Dane_pojazdu (
  ID_Pojazdu INT PRIMARY KEY AUTO_INCREMENT,
  NR_Rej VARCHAR(10) NOT NULL,
  NR_VIN VARCHAR(17) NOT NULL,
  Rocznik INT,
  Stan_licznika INT
);

CREATE TABLE Dane_Klienta (
  ID_Klienta INT PRIMARY KEY AUTO_INCREMENT,
  Imie VARCHAR(50) NOT NULL,
  Nazwisko VARCHAR(50) NOT NULL,
  Adres VARCHAR(100),
  NR_Tel VARCHAR(20),
  Zgoda_na_jazde_probna BOOLEAN
);

CREATE TABLE Dane_mechanikow (
  ID_mechanika INT PRIMARY KEY AUTO_INCREMENT,
  ID_Specjalizacji INT,
  ID_Konta INT,
  Imie VARCHAR(50) NOT NULL,
  Nazwisko VARCHAR(50) NOT NULL,
  NR_Tel VARCHAR(20),
  Mail VARCHAR(100)
);
CREATE TABLE Dane_Kont (
  ID_Konta INT PRIMARY KEY AUTO_INCREMENT,
  Login VARCHAR(20),
  Haslo VARCHAR(32)
);

ALTER TABLE Zlecenia ADD FOREIGN KEY (ID_Pojazdu) REFERENCES Dane_pojazdu (ID_Pojazdu);

ALTER TABLE Zlecenia ADD FOREIGN KEY (ID_Klienta) REFERENCES Dane_Klienta (ID_Klienta);

ALTER TABLE Zlecenia ADD FOREIGN KEY (ID_Mechanika) REFERENCES Dane_mechanikow (ID_mechanika);

ALTER TABLE Dane_mechanikow ADD FOREIGN KEY (ID_Specjalizacji) REFERENCES Specjalizacje (ID_Specjalizacji);

ALTER TABLE Dane_mechanikow ADD FOREIGN KEY (ID_Konta) REFERENCES Dane_Kont (ID_Konta);


INSERT INTO Specjalizacje (ID_Specjalizacji, Nazwa) VALUES
(1, 'Elektryka'),
(2, 'Mechanika samochodowa'),
(3, 'Naprawa silnikow');

INSERT INTO Dane_Kont (ID_Konta, Login, Haslo) VALUES
(1, 'asd', '7815696ecbf1c96e6894b779456d330e'),
(2, 'EwaKowalska', '141f27872cd8b0c7ab6dba2b0759a657'),
(3, 'AdamWisniewski', 'a2197abd19400a16a56944385e31d2c3');

INSERT INTO Dane_pojazdu (ID_Pojazdu, NR_Rej, NR_VIN, Rocznik, Stan_licznika) VALUES
(1, 'ABC123', '12345678901234567', 2018, 50000),
(2, 'XYZ789', '98765432109876543', 2020, 35000),
(3, 'DEF456', '45678901234567890', 2019, 42000);

INSERT INTO Dane_Klienta (ID_Klienta, Imie, Nazwisko, Adres, NR_Tel, Zgoda_na_jazde_probna) VALUES
(1, 'Jan', 'Kowalski', 'ul. Kwiatowa 1, 00-001 Warszawa', '123456789', true),
(2, 'Anna', 'Nowak', 'ul. Sloneczna 2, 11-222 Krakow', '987654321', false),
(3, 'Piotr', 'Wisniewski', 'ul. Jesienna 3, 22-333 Gdansk', '555666777', true);

INSERT INTO Dane_mechanikow (ID_mechanika, ID_Specjalizacji, ID_Konta, Imie, Nazwisko, NR_Tel, Mail) VALUES
(1, 1, 1, 'Marek', 'Nowak', '111222333', 'marek.nowak@example.com'),
(2, 2, 2, 'Ewa', 'Kowalska', '444555666', 'ewa.kowalska@example.com'),
(3, 3, 3, 'Adam', 'Wisniewski', '777888999', 'adam.wisniewski@example.com');

INSERT INTO Zlecenia (ID_Zlecenia, ID_Pojazdu, ID_Klienta, ID_Mechanika, Opis_uszkodzen, Zakres_Napraw, Szacowany_Koszt_Naprawy) VALUES
(1, 1, 1, 1, 'Problemy z instalacja elektryczna', 'Naprawa instalacji elektrycznej', 500.00),
(2, 2, 2, 2, 'Awaria silnika', 'Wymiana oleju, naprawa silnika', 1200.00),
(3, 3, 3, 3, 'Problemy z hamulcami', 'Naprawa ukladu hamulcowego', 800.00);

