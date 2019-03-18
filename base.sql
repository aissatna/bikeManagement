/*-------------table Modele----------------- */
create table Modele (
numModele INTEGER,
Type VARCHAR(30) not null,
Prix INTEGER not null,
constraint numModele_pk primary key (numModele),
constraint Prix_check check (Prix > 0)

);
/*-------------table PlageHoraire----------------- */
create table PlageHoraire(
numPlageHoraire INTEGER,
HeureDebut date not null,
HeureFin date not null,
Type VARCHAR(20),
constraint numPlageHoraire_pk primary key (numPlageHoraire),
constraint Type_check check (Type in ('Vmoins','Vplus','Vnul'))

);

/*-------------table Station----------------- */
create table Station (
numStation INTEGER ,
adresse VARCHAR(70) not null,
numPlageHoraire INTEGER,
constraint numSation_pk primary key(numStation),
constraint numPlageHoraire_fk Foreign key (numPlageHoraire) references PlageHoraire(numPlageHoraire)
 );

/*-------------table Velo----------------- */
create table Velo (
numVelo INTEGER,
numModele INTEGER,
MiseEnService date not null,
EtatVelo VARCHAR(20)
DisponibiliteVelo VARCHAR(20),
constraint numVelo_pk primary key (numVelo),
constraint numModele_fk Foreign key (numModele) references Modele(numModele),
constraint EtatVelo_check check (EtatVelo in ('EnService','HorsService')),
constraint DisponibiliteVelo_check check (DisponibiliteVelo in ('Louer','reserver','disponible'))
);
/*-------------table Bornette----------------- */
create table Bornette(
 numBornette INTEGER,
 etatBornette VARCHAR(20) not null,
 numStation INTEGER,
 numVelo INTEGER,
 constraint numBornette_pk primary key (numBornette),
 constraint numSation_fk Foreign key (numStation) references Station(numStation),
 constraint numVelo_fk  Foreign key (numVelo) references Velo(numVelo),
 constraint etatBornette_check check (etatBornette in ('EnService','HorsService')),
);
/*-------------table ClientAbonne----------------- */
create table ClientAbonne(

	
);
