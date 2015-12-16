DROP TABLE IF EXISTS practicauoc.trip_passenger;
DROP TABLE IF EXISTS practicauoc.trip;
DROP TABLE IF EXISTS practicauoc.car;
DROP VIEW IF EXISTS practicauoc.v_user;
DROP TABLE IF EXISTS practicauoc.driver;
DROP TABLE IF EXISTS practicauoc.passenger;

-- Table: practicauoc.driver
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

 -- Table: practicauoc.passenger
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

  -- View: practicauoc.v_user
CREATE OR REPLACE VIEW practicauoc.v_user AS
SELECT nif, name, surname, phone, password, email, 'driver' as USERTYPE FROM practicauoc.driver 
UNION 
SELECT nif, name, surname, phone, password, email, 'passenger' as USERTYPE FROM practicauoc.passenger;
ALTER TABLE practicauoc.v_user
  OWNER TO "USER";

  -- Table: practicauoc.car
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

-- Table: practicauoc.trip
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
  price double precision,
  toplace character varying(255),
  nif character varying(255),
  CONSTRAINT trip_pkey PRIMARY KEY (id),
  CONSTRAINT fk_k35yjqi8kj1r5gw2q3jwtahos FOREIGN KEY (nif)
      REFERENCES practicauoc.driver (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkcf480021d841a637 FOREIGN KEY (nif)
      REFERENCES practicauoc.driver (nif) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE practicauoc.trip
  OWNER TO "USER";
  
  -- Table: practicauoc.trip_passenger
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


INSERT INTO practicauoc.driver(nif, email, name, password, phone, surname) VALUES ('00000000X','manuel@mail.com', 'Manuel', 'PasswordManuel', '981898989', 'Iglesias');
INSERT INTO practicauoc.driver(nif, email, name, password, phone, surname) VALUES ('00000001X','antonio@mail.com', 'Antonio', 'PasswordAntonio', '981252525', 'Martin');
INSERT INTO practicauoc.driver(nif, email, name, password, phone, surname) VALUES ('00000002X','laura@mail.com', 'Laura', 'PasswordLaura', '981303030', 'Sanchez');

INSERT INTO practicauoc.passenger(nif, email, name, password, phone, surname)VALUES ('00000000Y','pablo@mail.com', 'Pablo', 'PasswordPablo', '981313131', 'Suarez');
INSERT INTO practicauoc.passenger(nif, email, name, password, phone, surname)VALUES ('00000001Y','pedro@mail.com', 'Pedro', 'PasswordPedro', '981333333', 'Cela');
INSERT INTO practicauoc.passenger(nif, email, name, password, phone, surname)VALUES ('00000002Y','jose@mail.com', 'Jose', 'PasswordJose', '981444444', 'Perez');
    
INSERT INTO practicauoc.car(brand, color, model, nif_owner) VALUES ('ALFA-ROMEO', 'ROJO', '145', '00000000X');
INSERT INTO practicauoc.car(brand, color, model, nif_owner) VALUES ('MERCEDES', 'PLATA', 'CLASE-A', '00000001X');
INSERT INTO practicauoc.car(brand, color, model, nif_owner) VALUES ('SEAT', 'ROJO', 'LEON', '00000002X');

INSERT INTO practicauoc.trip(arrivalcity, availableseats, departurecity, departuredate, departuretime, description, fromplace, price, toplace, nif) VALUES ('Oviedo', '3', 'Madrid', '10/12/2015', '01/01/1869 10:15', 'Son 4 horas y media de viaje', 'Atocha', '50.5', 'Catedral', '00000000X');
INSERT INTO practicauoc.trip(arrivalcity, availableseats, departurecity, departuredate, departuretime, description, fromplace, price, toplace, nif) VALUES ('Valladolid', '4', 'Madrid', '12/12/2015', '01/01/1869 11:30', 'Son 2 horas y cuarto de viaje', 'Sol', '12.10', 'Estadio', '00000001X');
INSERT INTO practicauoc.trip(arrivalcity, availableseats, departurecity, departuredate, departuretime, description, fromplace, price, toplace, nif) VALUES ('Segovia', '4', 'Valladolid', '15/12/2015', '01/01/1869 08:30', 'Es una hora de viaje', 'Fuencarral', '10.30', 'Acueducto', '00000002X');

INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (1, '00000000Y');
INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (1, '00000001Y');
INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (1, '00000002Y');
INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (2, '00000001Y');
INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (3, '00000002Y');
INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (3, '00000001Y');
INSERT INTO practicauoc.trip_passenger(id_trip, nif_passenger) VALUES (3, '00000000Y');