drop table Reservation;
drop table LocationNonAbonne;
drop table LocationAbonne;
drop table ClientNonAbonne;
drop table ClientAbonne;
drop table Bornette;
drop table Velo;
drop table PlageHoraire;
drop table Station;
drop table Modele;

create table Modele(
numModele INTEGER,
TypeVelo VARCHAR(50) not null,
Prix INTEGER not null,
constraint numModele_pk primary key (numModele),
constraint Prix_check check (Prix > 0),
constraint TypeVelo_check check (TypeVelo in ('Velo_de_route','Velo_de_course','Velo_pliant','Velo_couche','Velo_electrique')));

create table Station(
numStation INTEGER ,
adresse VARCHAR(70) not null unique,
constraint numSation_pk primary key(numStation));



create table PlageHoraire(
numPlageHoraire INTEGER,
HeureDebut date not null,
HeureFin date not null,
Type VARCHAR(20),
NumStation INTEGER,
constraint numPlageHoraire_pk primary key (numPlageHoraire),
constraint Type_check check (Type in ('Vmoins','Vplus','Vnull')),
constraint numStation_fk Foreign key (NumStation) references Station(numStation));


create table Velo (
numVelo INTEGER,
numModele INTEGER,
MiseEnService date not null,
EtatVelo VARCHAR(20),
DisponibiliteVelo VARCHAR(20),
constraint numVelo_pk primary key (numVelo),
constraint numModele_fk Foreign key (numModele) references Modele(numModele),
constraint EtatVelo_check check (EtatVelo in ('EnService','HorsService')),
constraint DisponibiliteVelo_check check (DisponibiliteVelo in ('Louer','Reserver','Disponible')));

create table Bornette(
 numBornette INTEGER,
 etatBornette VARCHAR(20) not null,
 disponibilite VARCHAR(20),
 numStation INTEGER,
 numVelo INTEGER,
 constraint numBornette_pk primary key (numBornette),
 constraint numSation_fk Foreign key (numStation) references Station (numStation),
 constraint Disponibilite_check check (disponibilite in ('Libre','Occupe')),
 constraint numVelo_fk  Foreign key (numVelo) references Velo(numVelo),
 constraint etatBornette_check check (etatBornette in ('EnService','HorsService')));

create table ClientAbonne(
numClientAbonne INTEGER,
Nom VARCHAR(20) not null,
Prenom VARCHAR(20) not null,
DateDeNaissance date not null,
Sexe VARCHAR(10) not null,
Adresse VARCHAR(70) not null,
NumCB VARCHAR(20) not null,
CodeSecret VARCHAR(20)not null,
Prime INTEGER default 0,
DebutAbonnement date not null,
FinAbonnement date not null,
constraint numClientAbonne_pk primary key (numClientAbonne),
constraint sexe_check check (sexe in ('H','F')),
constraint prime_check check (prime >= 0));

create table ClientNonAbonne(
numClientNonAbonne INTEGER,
NumCB VARCHAR(20),
constraint numClientNonAbonne_pk primary key (numClientNonAbonne));

create table LocationAbonne(
numLocationAbonne INTEGER,
numClientAbonne INTEGER,
numVelo INTEGER ,
DebutLocation TIMESTAMP not null,
FinLocation TIMESTAMP ,
Duree INTEGER default 0,
StationDepart INTEGER,
StationArrivee INTEGER,
EtatFacture VARCHAR(20),
constraint LocationAbonne_pk primary key (numLocationAbonne),
constraint LocationAbonne_fk Foreign key (numClientAbonne) references ClientAbonne(numClientAbonne),
constraint LocationAbonne_fk2 Foreign key (numVelo) references Velo(numVelo),
constraint LocationAbonne_fk3 Foreign key (StationDepart) references Station(numStation),
constraint EtatFactureA_check check (EtatFacture in ('Facturee','NonFacturee')));

create table LocationNonAbonne(
numLocationNonAbonne INTEGER,
numClientNonAbonne INTEGER,
numVelo INTEGER,
DebutLocation TIMESTAMP not null,
FinLocation TIMESTAMP,
Duree INTEGER default 0,
StationDepart INTEGER,
StationArrivee INTEGER,
CodeSecret VARCHAR(20) not null,
EtatFacture VARCHAR(20),
constraint LocationNonAbonne_pk primary key(numLocationNonAbonne),
constraint LocationNonAbonne_fk Foreign key (numClientNonAbonne) references ClientNonAbonne(numClientNonAbonne),
constraint LocationNonAbonne_fk2 Foreign key (numVelo) references Velo(numVelo),
constraint LocationNonAbonne_fk3 Foreign key (StationDepart) references Station(numStation),
constraint EtatFactureNA_check check (EtatFacture in ('Facturee','NonFacturee')));

create table Reservation(
numReservation INTEGER,
numClientAbonne INTEGER,
numStation INTEGER,
DebutReservation TIMESTAMP not null ,
FinReservation TIMESTAMP not null,
constraint Reservation_pk primary key (numReservation),
constraint Reservation_fk Foreign key (numClientAbonne) references ClientAbonne(numClientAbonne),
constraint Reservation_fk2 Foreign key (numStation) references Station(numStation));
