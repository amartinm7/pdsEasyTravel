
CREATE OR REPLACE VIEW practicauoc.v_user AS
SELECT nif, name, surname, phone, password, email, 'driver' as USERTYPE FROM practicauoc.driver 
UNION 
SELECT nif, name, surname, phone, password, email, 'passenger' as USERTYPE FROM practicauoc.passenger;
ALTER TABLE practicauoc.v_user
  OWNER TO "USER";

  
  -- Table: practicauoc.car

-- DROP TABLE practicauoc.car;

CREATE TABLE practicauoc.car
(
  id serial NOT NULL, --ojo con SERIAL, es el autoincrement
  brand character varying(255),
  color character varying(255),
  model character varying(255),
  nif_owner character varying(255),
  CONSTRAINT car_pkey PRIMARY KEY (id),
  CONSTRAINT fk_b7k4fvtam51ihxq730039hmt8 FOREIGN KEY (nif_owner)
      REFERENCES practicauoc.driver (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.car
  OWNER TO "USER";

  DROP TABLE practicauoc.trip_passenger;

CREATE TABLE practicauoc.trip_passenger
(
  "id_trip" integer NOT NULL,
  "nif_passenger" character varying(255) NOT NULL,
  CONSTRAINT fk_b5svn5dgqv84we868r44r219v FOREIGN KEY ("nif_passenger")
      REFERENCES practicauoc.passenger (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_fdb395hgb9wsvb8id838n8qtm FOREIGN KEY ("id_trip")
      REFERENCES practicauoc.trip (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.trip_passenger
  OWNER TO "USER";  

  
INSERT INTO practicauoc.car(id, brand, color, model, nif_owner) VALUES ('1', 'ALFA-ROMEO', 'ROJO', '145', '00000000X');

INSERT INTO practicauoc.car(id, brand, color, model, nif_owner) VALUES ('2', 'MERCEDES', 'PLATA', 'CLASE-A', '00000001X');

INSERT INTO practicauoc.car(id, brand, color, model, nif_owner) VALUES ('1', 'SEAT', 'ROJO', 'LEON', '00000002X');



  commit;