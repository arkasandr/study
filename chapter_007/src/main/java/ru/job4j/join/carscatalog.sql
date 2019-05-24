CREATE DATABASE cars_catalog;

CREATE TABLE carbody(
id SERIAL PRIMARY KEY,
name_carbody VARCHAR(100)
);

CREATE TABLE engine(
id SERIAL PRIMARY KEY,
name_engine VARCHAR(100)
);

CREATE TABLE transmission(
id SERIAL PRIMARY KEY,
name_transmission VARCHAR(100)
);

CREATE TABLE cars(
id SERIAL PRIMARY KEY,
	name_car VARCHAR(100),
	carbody_id INTEGER REFERENCES carbody(id),
	engine_id INTEGER REFERENCES engine(id),
	transmission_id INTEGER REFERENCES transmission(id)
);

INSERT INTO carbody (name_carbody) VALUES ('pickup'), ('sedan'), ('coupe'), ('wagon'), ('bus');
INSERT INTO engine (name_engine) VALUES ('two_cylinder'), ('four_cylinder'), ('six_cylinder'), ('eight_cylinder');
INSERT INTO transmission (name_transmission) VALUES ('automatic'), ('variable'), ('manual'), ('DSG');

INSERT INTO cars (name_car, carbody_id, engine_id, transmission_id) VALUES
('first', 1, 3, 1),
('second', 2, 2, 3),
('third', 3, 4, 1),
('fourth', 2, 2, 2),
('fifth', 4, 3, 1)
;

--1. Вывести список всех машин и все привязанные к ним детали.

SELECT name_car AS name, name_carbody AS carbody, name_engine AS engine, name_transmission AS transmission FROM cars AS c 
LEFT OUTER JOIN carbody AS cb ON c.carbody_id = cb.id 
LEFT OUTER JOIN engine AS e ON c.engine_id = e.id 
LEFT OUTER JOIN transmission AS tr ON c.transmission_id = tr.id;


--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
--отдельные запросы на каждую деталь
SELECT e.name_engine, c.name_car FROM cars AS c RIGHT OUTER JOIN engine AS e ON e.id = c.engine_id where c.id is null;

SELECT cb.name_carbody, c.name_car FROM cars AS c RIGHT OUTER JOIN carbody AS cb ON cb.id = c.carbody_id where c.id is null;

SELECT tr.name_transmission, c.name_car FROM cars AS c RIGHT OUTER JOIN transmission AS tr ON tr.id = c.transmission_id where c.id is null;

--объединенный запрос на вывод всех деталей
SELECT e.name_engine, c.name_car FROM cars AS c RIGHT OUTER JOIN engine AS e ON e.id = c.engine_id where c.id is null union
SELECT cb.name_carbody, c.name_car FROM cars AS c RIGHT OUTER JOIN carbody AS cb ON cb.id = c.carbody_id where c.id is null union
SELECT tr.name_transmission, c.name_car FROM cars AS c RIGHT OUTER JOIN transmission AS tr ON tr.id = c.transmission_id where c.id is null;