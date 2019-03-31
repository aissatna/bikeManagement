--1 Un client ne peut pas avoir plusieurs réservations avec un recouvrement
CREATE OR REPLACE  TRIGGER contrainteReservations
BEFORE INSERT ON Reservation
FOR EACH ROW
DECLARE
nbRes number;
BEGIN
   Select count(*) into nbRes
   from Reservation
   where DebutReservation <:new.DebutReservation
   AND   FinReservation >:new.DebutReservation 
   AND   numClientAbonne=:new.numClientAbonne;
   
   IF nbRes=1
    THEN Raise_application_error('-20101', 'Ce client a une réservations ');
   END IF;
   
END;
/
