--affichage des station qui possède des bornette libre.
select distinct s.numstation, s.adresse from station s natural join bornette b natural join velo v where b.disponibilite = 'Libre';

--nombre de bornette libre dans la Station
