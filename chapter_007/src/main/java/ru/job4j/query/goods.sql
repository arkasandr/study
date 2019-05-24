CREATE DATABASE goods;

CREATE TABLE types(
id SERIAL PRIMARY KEY,
name VARCHAR(100)
);

CREATE TABLE products(
id SERIAL PRIMARY KEY,
	name VARCHAR(100),
	type_id INTEGER REFERENCES types(id),
	expired_date DATE,
	price NUMERIC
);

INSERT INTO types (name) VALUES ('сыр'), ('молоко'), ('мясо'), ('мороженое');
INSERT INTO products (name, type_id, expired_date, price) VALUES
('сыр Гауда', 1, '2019-05-24', 650),
('сыр Бри', 1, '2019-06-30', 1300),
('молоко козье', 2, '2019-05-23', 90),
('молоко 3,2%', 2, '2019-05-24', 56),
('молоко овсяное', 2, '2019-07-16', 80),
('мясо свежее (говядина)', 3, '2019-06-27', 560),
('мясо мороженое (утка)', 3, '2019-07-21', 480),
('мороженое ваниль', 4, '2019-08-01', 64),
('мороженое шоколад', 4, '2019-07-23', 72);

--1. Написать запрос получения всех продуктов с типом "СЫР"
SELECT name, price FROM products WHERE type_id=(SELECT id FROM types WHERE name = 'сыр');

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT name, price FROM products WHERE name LIKE '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT name, price, expired_date FROM products WHERE date_part('month', expired_date) - date_part('month', now()) = 1;

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT name, price FROM products WHERE price = (SELECT MAX(price) FROM products);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(name) FROM products WHERE type_id=(SELECT id FROM types WHERE name = 'молоко');
																								  
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT name, price FROM products WHERE type_id=(SELECT id FROM types WHERE name = 'сыр') OR type_id=(SELECT id FROM types WHERE name = 'молоко');
																								  
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 3 штук ( 3 вместо 10 для наглядности).
SELECT types.name AS type FROM products, types WHERE (SELECT COUNT(*) FROM PRODUCTS WHERE type_id=types.id) < 3 GROUP BY types.name;
																								  
--8. Вывести все продукты и их тип.
SELECT products.name AS name, types.name AS type FROM products, types WHERE type_id=types.id;

