create or replace procedure AjoutLocationAbonne(
       IdClient LocationAbonne.NUMCLIENTABONNE%type,
       numVelo LocationAbonne.NUMVELO%type,
       DebutLocation LocationAbonne.DEBUTLOCATION%type,
       FinLocation LocationAbonne.FINLOCATION%type,
       Duree LocationAbonne.DUREE%type,
       StationDepart LocationAbonne.STATIONDEPART%type,
       StationArrivee LocationAbonne.STATIONARRIVEE%type,
       EtatFacture LocationAbonne.EtatFacture%type
       )
     is
     CountLocationClient int;
     numLocation int;
     begin
     select count (*) into CountLocationClient from LOCATIONABONNE where numClientAbonne = idClient and FinLocation is NULL;  
     select max(NUMLOCATIONABONNE) into numLocation from LOCATIONABONNE;
     if (CountLocationClient > 0) then 
      raise_application_error(-20100,'Une Location est en cours pour ce client... !');
     else 
     insert into LocationAbonne values (numLocation+1,
                                        IdClient,
                                        numVelo,
                                        DebutLocation,
                                        FinLocation,
                                        Duree,
                                        StationDepart,
                                        StationArrivee,
                                        EtatFacture);
     end if;
     commit;
     end;
/
create or replace procedure updateEtatVeloBornetteLocation(
                 idVelo Velo.numVelo%type,
                 idBornette Bornette.numBornette%type,
                 DisponibiliteVeloNew Velo.DisponibiliteVelo%type,
                 disponibiliteNew Bornette.disponibilite%type,
                 idStation Bornette.numStation%type

                )
       is
       begin
       update Velo set DisponibiliteVelo =  DisponibiliteVeloNew where numVelo = idVelo;
       update Bornette set disponibilite =  disponibiliteNew where numBornette = idBornette AND numStation = idStation;
       commit;
       end;
/ 
create or replace function AjoutClientNonAbonne(
                      NumCBIn ClientNonAbonne.NumCB%type  
                      )
       return INTEGER
       is
       idClient int; 
       CountLocationClient int;
       numClientNonAbonne INTEGER;
       Requete number;
       begin
       select max(numClientNonAbonne)+1 into numClientNonAbonne from ClientNonAbonne;
       select numClientNonAbonne into idClient from ClientNonAbonne where NumCB = NumCBIn;
       select count (*) into CountLocationClient from LocationNonAbonne where numClientNonAbonne = idClient    
                                                                   and FinLocation is NULL; 
       if (CountLocationClient > 0) then 
       raise_application_error(-20100,'Une Location est en cours pour ce client avec cette carte ... !');
       else 
       insert into ClientNonAbonne values (numClientNonAbonne,NumCBIn);
       commit;
       return numClientNonAbonne;
       end if;
       Exception
                when NO_DATA_FOUND then
       insert into ClientNonAbonne values (numClientNonAbonne,NumCBIn);
       commit;
       return numClientNonAbonne;
       end;
/
create or replace procedure AjoutLocationNonAbonne(
       IdClient LocationNonAbonne.numClientNonAbonne%type,
       numVelo LocationNonAbonne.numVelo%type,
       DebutLocation LocationNonAbonne.DebutLocation%type,
       FinLocation LocationNonAbonne.FINLOCATION%type,
       Duree LocationNonAbonne.Duree%type,
       StationDepart LocationNonAbonne.StationDepart%type,
       StationArrivee LocationNonAbonne.StationArrivee%type,
       CodeSecret LocationNonAbonne.CodeSecret%type,
       EtatFacture LocationNonAbonne.EtatFacture%type
       )
     is
     numLocation int;
     begin
     select max(NumLocationNonAbonne) into numLocation from LocationNonAbonne;
     insert into LocationNonAbonne values (numLocation+1,IdClient,numVelo,DebutLocation,FinLocation,Duree,StationDepart,
                                   StationArrivee,CodeSecret,EtatFacture);
     
     commit;
     end;
/
create or replace procedure updateEtatVeloBornetteRendu(
                 idVelo Velo.numVelo%type,
                 idBornette Bornette.numBornette%type,
                 DisponibiliteVeloNew Velo.DisponibiliteVelo%type,
                 disponibiliteNew Bornette.disponibilite%type,
                 idStation Bornette.numStation%type,
                 EtatVeloNew Velo.EtatVelo%type
                )
       is
       begin
       update Velo set DisponibiliteVelo =  DisponibiliteVeloNew , EtatVelo = EtatVeloNew where numVelo = idVelo;
       update Bornette set disponibilite =  disponibiliteNew ,numVelo = idVelo where numBornette = idBornette AND numStation = idStation;
       commit;
       end;
/ 
