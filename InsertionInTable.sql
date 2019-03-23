--Insertion dans la table Modele------------------------------------------
INSERT INTO  Modele values (001,'Velo_de_route',12);
INSERT INTO  Modele values (002,'Velo_de_course',35);
INSERT INTO  Modele values (003,'Velo_pliant',15);
INSERT INTO  Modele values (004,'Velo_couche',10);
INSERT INTO  Modele values (005,'Velo_electrique',25);

--Insertion dans la table plage horaire----------------------------------------
INSERT INTO PlageHoraire values (001,'09-JAN-2018','09-JAN-2018','Vplus');
INSERT INTO PlageHoraire values (002,'09-JAN-2018','09-JAN-2018','Vplus');
INSERT INTO PlageHoraire values (003,'09-JAN-2019','11-JAN-2019','Vmoins');
INSERT INTO PlageHoraire values (004,'10-JAN-2019','12-JAN-2019','Vnull');
INSERT INTO PlageHoraire values (006,'12-JAN-2019','13-JAN-2019','Vplus');
INSERT INTO PlageHoraire values (007,'12-JAN-2019','13-JAN-2019','Vmoins');
INSERT INTO PlageHoraire values (008,'13-JAN-2019','14-JAN-2019','Vnull');
INSERT INTO PlageHoraire values (009,'14-JAN-2019','15-JAN-2019','Vplus');
INSERT INTO PlageHoraire values (010,'15-JAN-2019','16-JAN-2019','Vmoins');

--Insertion dans la table Station ------------------------------------------
INSERT INTO Station VALUES(001,'7 rue boulevard des fauche grenoble 38100',002);
INSERT INTO Station VALUES(002,'12 rue des Marronniers fontaine 38600',003);
INSERT INTO Station VALUES(003,'77 avenue Jeanne d Arc SMH 38400',001);

--Insertion dans la table Velo ------------------------------------------
--Velo dans la station 1-------------------------------------------------
INSERT INTO Velo VALUES(101,001,'21-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(102,001,'21-MAY-2017','EnService','Reserver');
INSERT INTO Velo VALUES(103,001,'21-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(104,001,'21-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(105,001,'21-MAY-2017','EnService','Louer');
INSERT INTO Velo VALUES(106,002,'10-JUN-2018','EnService','Disponible');
INSERT INTO Velo VALUES(107,002,'20-JUN-2018','EnService','Reserver');
INSERT INTO Velo VALUES(108,002,'20-JUN-2018','HorsService','Reserver');
INSERT INTO Velo VALUES(109,002,'20-JUN-2018','EnService','Reserver');
INSERT INTO Velo VALUES(100,002,'20-JUN-2018','EnService','Reserver');

--Velo dans la station 2---------------------------------------------------------
INSERT INTO Velo VALUES(201,001,'13-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(202,001,'13-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(203,001,'13-MAY-2017','EnService','Louer');
INSERT INTO Velo VALUES(204,001,'13-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(205,001,'13-MAY-2017','HorsService','Disponible');
INSERT INTO Velo VALUES(206,002,'21-JUN-2018','EnService','Disponible');
INSERT INTO Velo VALUES(207,002,'21-JUN-2018','EnService','Louer');
INSERT INTO Velo VALUES(208,002,'21-JUN-2018','HorsService','Disponible');
INSERT INTO Velo VALUES(209,002,'21-JUN-2018','EnService','Disponible');
INSERT INTO Velo VALUES(200,002,'21-JUN-2018','EnService','Louer');

--Velo dans la station 3--------------------------------------------------------------
INSERT INTO Velo VALUES(301,001,'26-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(302,001,'26-MAY-2017','EnService','Louer');
INSERT INTO Velo VALUES(303,001,'26-MAY-2017','EnService','Disponible');
INSERT INTO Velo VALUES(304,001,'26-MAY-2017','EnService','Louer');
INSERT INTO Velo VALUES(305,001,'26-MAY-2017','EnService','Reserver');
INSERT INTO Velo VALUES(306,002,'20-JUN-2018','EnService','Louer');
INSERT INTO Velo VALUES(307,002,'20-JUN-2018','EnService','Louer');
INSERT INTO Velo VALUES(308,002,'20-JUN-2018','EnService','Reserver');
INSERT INTO Velo VALUES(309,002,'20-JUN-2018','EnService','Disponible');
INSERT INTO Velo VALUES(300,002,'20-JUN-2018','EnService','Disponible');


--Insertion dans la table Bornette ----------------------------------------
--Station de serie de 1-------------------------------------------------
INSERT INTO Bornette VALUES (100,'EnService',001,100);
INSERT INTO Bornette VALUES (101,'EnService',001,101);
INSERT INTO Bornette VALUES (102,'EnService',001,102);
INSERT INTO Bornette VALUES (103,'EnService',001,103);
INSERT INTO Bornette VALUES (104,'EnService',001,104);
INSERT INTO Bornette VALUES (105,'EnService',001,105);
INSERT INTO Bornette VALUES (106,'EnService',001,106);
INSERT INTO Bornette VALUES (107,'HorsService',001,107);
INSERT INTO Bornette VALUES (108,'EnService',001,108);
INSERT INTO Bornette VALUES (109,'EnService',001,109);

--Station de serie de 2--------------------------------------------------
INSERT INTO Bornette VALUES (200,'HorsService',002,200);
INSERT INTO Bornette VALUES (201,'EnService',002,201);
INSERT INTO Bornette VALUES (202,'EnService',002,202);
INSERT INTO Bornette VALUES (203,'EnService',002,203);
INSERT INTO Bornette VALUES (204,'EnService',002,204);
INSERT INTO Bornette VALUES (205,'HorsService',002,205);
INSERT INTO Bornette VALUES (206,'EnService',002,206);
INSERT INTO Bornette VALUES (207,'EnService',002,207);
INSERT INTO Bornette VALUES (208,'EnService',002,208);
INSERT INTO Bornette VALUES (209,'EnService',002,209);
--Station de serie de 3---------------------------------------------------
INSERT INTO Bornette VALUES (300,'EnService',003,300);
INSERT INTO Bornette VALUES (301,'EnService',003,301);
INSERT INTO Bornette VALUES (302,'EnService',003,302);
INSERT INTO Bornette VALUES (303,'HorsService',003,303);
INSERT INTO Bornette VALUES (304,'EnService',003,304);
INSERT INTO Bornette VALUES (305,'EnService',003,305);
INSERT INTO Bornette VALUES (306,'EnService',003,306);
INSERT INTO Bornette VALUES (307,'EnService',003,307);
INSERT INTO Bornette VALUES (308,'HorsService',003,308);
INSERT INTO Bornette VALUES (309,'EnService',003,309);

--Insertion dans la table ClientAbonne ------------------------------------------
INSERT INTO ClientAbonne VALUES(021,'Bourgeois','Jules','08-FEB-1991','H','15 rue Saint Bruno 38000','12345764231897','4DRG45',12,'05-FEB-2018','05-FEB-2019');
INSERT INTO ClientAbonne VALUES(032,'Prague','Kevin','11-DEC-1986','H','3 rue des Marronniers 38600','12034875932148','6DF5dF',3,'09-MAY-2019','09-MAY-2020');
INSERT INTO ClientAbonne VALUES(043,'Emile','Famy','29-JUN-2000','F','09 Rue emile SMH 38400','95674213587649','458ERom',1,'21-JUN-2019','21-JUN-2020');
INSERT INTO ClientAbonne VALUES(164,'Calinou','Camelie','21-MAY-1996','F','03 Boulevard Ferrie Grenoble 38100','52348791326458','MP8d45',6,'20-JUN-2018','20-JUN-2019');
INSERT INTO ClientAbonne VALUES(955,'Paroix','Charmelle','31-JAN-1996','F','60 Rue de la chimie SMH 38400','65328974513264','MLe786',0,'30-JUN-2018','30-JUN-2019');
INSERT INTO ClientAbonne VALUES(346,'Leperse','Boudin','21-APR-1955','H','13 avenue de malherbe Grenoble 38000','78451352649875','DFx548',1,'05-JUL-2018','05-JUL-2019');

--Insertion dans la table ClientNonAbonne--------------------------------------------
INSERT INTO ClientNonAbonne VALUES(037,'45213598764512');
INSERT INTO ClientNonAbonne VALUES(065,'13246789546125');
INSERT INTO ClientNonAbonne VALUES(078,'25974581234679');
INSERT INTO ClientNonAbonne VALUES(069,'25874136985641');
INSERT INTO ClientNonAbonne VALUES(032,'79458213654987');
INSERT INTO ClientNonAbonne VALUES(079,'10054786231487');

--Insertion dans la table Location abonne---------------------------------------
INSERT INTO LocationAbonne values (021,200,'12-JAN-2019','13-JAN-2019',2,002,002);
INSERT INTO LocationAbonne values (032,300,'12-JAN-2019','13-JAN-2019',1,003,003);
INSERT INTO LocationAbonne values (955,100,'13-JAN-2019','14-JAN-2019',3,001,001);
INSERT INTO LocationAbonne values (346,106,'13-JAN-2019','14-JAN-2019',2,001,001);
INSERT INTO LocationAbonne values (043,206,'14-JAN-2019','15-JAN-2019',2,002,002);

--Insertion dans la table Location non abonne ---------------------------------------
INSERT INTO LocationNonAbonne values (037,102,'12-JAN-2019','13-JAN-2019','',001,001,'4DRG45');
INSERT INTO LocationNonAbonne values (065,103,'12-JAN-2019','13-JAN-2019','',001,001,'6DF5dF');
INSERT INTO LocationNonAbonne values (078,203,'12-JAN-2019','13-JAN-2019','',002,002,'458ERom');
INSERT INTO LocationNonAbonne values (069,209,'13-JAN-2019','14-JAN-2019','',002,002,'MP8d45');
INSERT INTO LocationNonAbonne values (032,304,'13-JAN-2019','14-JAN-2019','',003,003,'MLe786');
INSERT INTO LocationNonAbonne values (079,308,'14-JAN-2019','15-JAN-2019','',003,003,'DFx548');

--Insertion dans la table Reservation-----------------------------------------
INSERT INTO Reservation(021,103,'03-FEB-2019','03-FEB-2019')



commit;
