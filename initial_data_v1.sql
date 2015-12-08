--create a user called "USER" with password named "PASSWORD".
--give grants to "USER" to ALL.
--create an scheme called "practicauoc" with "USER".
--execute the this script 



-- Table: practicauoc.car

-- DROP TABLE practicauoc.car;

CREATE TABLE practicauoc.car
(
  id serial NOT NULL,
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
  
  
-- Table: practicauoc.driver

-- DROP TABLE practicauoc.driver;

CREATE TABLE practicauoc.driver
(
  nif character varying(255) NOT NULL,
  email character varying(255),
  name character varying(255),
  password character varying(255),
  phone character varying(255),
  surname character varying(255),
  CONSTRAINT driver_pkey PRIMARY KEY (nif)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.driver
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

  -- Table: practicauoc.passenger

-- DROP TABLE practicauoc.passenger;

CREATE TABLE practicauoc.passenger
(
  nif character varying(255) NOT NULL,
  email character varying(255),
  name character varying(255),
  password character varying(255),
  phone character varying(255),
  surname character varying(255),
  CONSTRAINT passenger_pkey PRIMARY KEY (nif)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.passenger
  OWNER TO "USER";
  
  
-- Table: practicauoc.trip

-- DROP TABLE practicauoc.trip;

CREATE TABLE practicauoc.trip
(
  id serial NOT NULL,
  arrivalcity character varying(255),
  availableseats integer,
  departurecity character varying(255),
  departuredate timestamp without time zone,
  departuretime timestamp without time zone,
  description character varying(255),
  fromplace character varying(255),
  nif character varying(255),
  price double precision,
  toplace character varying(255),
  CONSTRAINT trip_pkey PRIMARY KEY (id),
  CONSTRAINT fk_k35yjqi8kj1r5gw2q3jwtahos FOREIGN KEY (nif)
      REFERENCES practicauoc.driver (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.trip
  OWNER TO "USER";
  
  
  -- Table: practicauoc.trip_passenger

-- DROP TABLE practicauoc.trip_passenger;

CREATE TABLE practicauoc.trip_passenger
(
  id_trip integer NOT NULL,
  nif_passenger character varying(255) NOT NULL,
  CONSTRAINT fk_b5svn5dgqv84we868r44r219v FOREIGN KEY (nif_passenger)
      REFERENCES practicauoc.passenger (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_fdb395hgb9wsvb8id838n8qtm FOREIGN KEY (id_trip)
      REFERENCES practicauoc.trip (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_mv0cvxf2l1b9tehudips8n205 FOREIGN KEY (id_trip)
      REFERENCES practicauoc.trip (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_rxqcuv6ns94hk0r9rjiqkhdvp FOREIGN KEY (nif_passenger)
      REFERENCES practicauoc.passenger (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.trip_passenger
  OWNER TO "USER";

  
-- View: practicauoc.v_user

-- DROP VIEW practicauoc.v_user;

CREATE OR REPLACE VIEW practicauoc.v_user AS 
         SELECT driver.nif, driver.name, driver.surname, driver.phone, 
            driver.password, driver.email, 'driver'::text AS usertype
           FROM practicauoc.driver
UNION 
         SELECT passenger.nif, passenger.name, passenger.surname, 
            passenger.phone, passenger.password, passenger.email, 
            'passenger'::text AS usertype
           FROM practicauoc.passenger;

ALTER TABLE practicauoc.v_user
  OWNER TO "USER";
  
 
1  
  
INSERT INTO practicauoc.car(id, brand, color, model, nif_owner) VALUES ('1', 'ALFA-ROMEO', 'ROJO', '145', '00000000X');

INSERT INTO practicauoc.car(id, brand, color, model, nif_owner) VALUES ('2', 'MERCEDES', 'PLATA', 'CLASE-A', '00000001X');

INSERT INTO practicauoc.car(id, brand, color, model, nif_owner) VALUES ('1', 'SEAT', 'ROJO', 'LEON', '00000002X');





  commit;