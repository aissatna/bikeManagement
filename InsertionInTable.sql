--Insertion dans la table Modele------------------------------------------
INSERT INTO  Modele values (001,'Velo_de_route',12);
INSERT INTO  Modele values (002,'Velo_de_course',35);
INSERT INTO  Modele values (003,'Velo_pliant',15);
INSERT INTO  Modele values (004,'Velo_couche',10);
INSERT INTO  Modele values (005,'Velo_electrique',25);


--Insertion dans la table Station ------------------------------------------
INSERT INTO Station VALUES(001,'7 rue boulevard des fauche grenoble 38100');
INSERT INTO Station VALUES(002,'12 rue des Marronniers fontaine 38600');
INSERT INTO Station VALUES(003,'77 avenue Jeanne d Arc SMH 38400');


--Insertion dans la table plage horaire----------------------------------------
INSERT INTO PlageHoraire values (001,TO_DATE('08:00:00','hh24:mi:ss'),TO_DATE('10:00:00','hh24:mi:ss'),'Vplus',001);
INSERT INTO PlageHoraire values (002,TO_DATE('16:00:00','hh24:mi:ss'),TO_DATE('18:00:00','hh24:mi:ss'),'Vplus',001);
INSERT INTO PlageHoraire values (003,TO_DATE('11:00:00','hh24:mi:ss'),TO_DATE('12:00:00','hh24:mi:ss'),'Vmoins',001);
INSERT INTO PlageHoraire values (004,TO_DATE('12:00:00','hh24:mi:ss'),TO_DATE('13:00:00','hh24:mi:ss'),'Vnull',001);
INSERT INTO PlageHoraire values (006,TO_DATE('08:00:00','hh24:mi:ss'),TO_DATE('10:00:00','hh24:mi:ss'),'Vplus',002);
INSERT INTO PlageHoraire values (007,TO_DATE('11:00:00','hh24:mi:ss'),TO_DATE('12:00:00','hh24:mi:ss'),'Vmoins',003);
INSERT INTO PlageHoraire values (008,TO_DATE('15:00:00','hh24:mi:ss'),TO_DATE('18:00:00','hh24:mi:ss'),'Vplus',003);
INSERT INTO PlageHoraire values (009,TO_DATE('12:00:00','hh24:mi:ss'),TO_DATE('13:00:00','hh24:mi:ss'),'Vnull',003);
INSERT INTO PlageHoraire values (010,TO_DATE('08:00:00','hh24:mi:ss'),TO_DATE('10:00:00','hh24:mi:ss'),'Vplus',001);
INSERT INTO PlageHoraire values (011,TO_DATE('10:00:00','hh24:mi:ss'),TO_DATE('11:00:00','hh24:mi:ss'),'Vmoins',002);
INSERT INTO PlageHoraire values (018,TO_DATE('12:00:00','hh24:mi:ss'),TO_DATE('13:00:00','hh24:mi:ss'),'Vnull',002);


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
INSERT INTO Bornette VALUES (100,'EnService','Occupe',001,100);
INSERT INTO Bornette VALUES (101,'EnService','Libre',001,101);
INSERT INTO Bornette VALUES (102,'EnService','Occupe',001,102);
INSERT INTO Bornette VALUES (103,'EnService','Libre',001,103);
INSERT INTO Bornette VALUES (104,'EnService','Occupe',001,104);
INSERT INTO Bornette VALUES (105,'EnService','Occupe',001,105);
INSERT INTO Bornette VALUES (106,'EnService','Occupe',001,106);
INSERT INTO Bornette VALUES (107,'HorsService','Occupe',001,107);
INSERT INTO Bornette VALUES (108,'EnService','Occupe',001,108);
INSERT INTO Bornette VALUES (109,'EnService','Occupe',001,109);

--Station de serie de 2--------------------------------------------------
INSERT INTO Bornette VALUES (200,'HorsService','Libre',002,200);
INSERT INTO Bornette VALUES (201,'EnService','Occupe',002,201);
INSERT INTO Bornette VALUES (202,'EnService','Occupe',002,202);
INSERT INTO Bornette VALUES (203,'EnService','Occupe',002,203);
INSERT INTO Bornette VALUES (204,'EnService','Occupe',002,204);
INSERT INTO Bornette VALUES (205,'HorsService','Occupe',002,205);
INSERT INTO Bornette VALUES (206,'EnService','Occupe',002,206);
INSERT INTO Bornette VALUES (207,'EnService','Occupe',002,207);
INSERT INTO Bornette VALUES (208,'EnService','Occupe',002,208);
INSERT INTO Bornette VALUES (209,'EnService','Libre',002,209);
--Station de serie de 3---------------------------------------------------
INSERT INTO Bornette VALUES (300,'EnService','Occupe',003,300);
INSERT INTO Bornette VALUES (301,'EnService','Occupe',003,301);
INSERT INTO Bornette VALUES (302,'EnService','Occupe',003,302);
INSERT INTO Bornette VALUES (303,'HorsService','Occupe',003,303);
INSERT INTO Bornette VALUES (304,'EnService','Occupe',003,304);
INSERT INTO Bornette VALUES (305,'EnService','Occupe',003,305);
INSERT INTO Bornette VALUES (306,'EnService','Occupe',003,306);
INSERT INTO Bornette VALUES (307,'EnService','Occupe',003,307);
INSERT INTO Bornette VALUES (308,'HorsService','Occupe',003,308);
INSERT INTO Bornette VALUES (309,'EnService','Occupe',003,309);

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



/INSERT INTO Reservation values(001,021,001,TO_TIMESTAMP('2019-03-15 11:00:00.00','yyyy-mm-dd hh24:mi:ss.ff'),TO_TIMESTAMP('2019-03-16 13:00:00.00','yyyy-mm-dd hh24:mi:ss.ff'))
commit;
