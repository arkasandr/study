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
	price NUMERIC,
	quantity INTEGER
);

INSERT INTO types (name) VALUES ('сыр'), ('молоко'), ('мясо'), ('мороженое');
INSERT INTO products (name, type_id, expired_date, price, quantity) VALUES
('сыр Гауда', 1, '2019-05-24', 650, 5),
('сыр Бри', 1, '2019-06-30', 1300, 1),
('молоко козье', 2, '2019-05-23', 90, 26),
('молоко 3,2%', 2, '2019-05-24', 56, 41),
('молоко овсяное', 2, '2019-07-16', 80, 12),
('мясо свежее (говядина)', 3, '2019-06-27', 560, 4),
('мясо мороженое (утка)', 3, '2019-07-21', 480, 3),
('мороженое ваниль', 4, '2019-08-01', 64, 14),
('мороженое шоколад', 4, '2019-07-23', 72, 8);

--1. Написать запрос получения всех продуктов с типом "СЫР"
SELECT name, price, quantity FROM products WHERE type_id=1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT name, price, quantity FROM products WHERE name LIKE '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT name, price, expired_date FROM products WHERE date_part('month', expired_date) - date_part('month', now()) = 1;

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT name, price FROM products WHERE price = (SELECT MAX(price) FROM products);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT name, price, quantity FROM products WHERE type_id=(SELECT id FROM types WHERE name = 'мясо');
																								  
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT name, price, quantity FROM products WHERE type_id=1 OR type_id=2;
																								  
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT types.name AS type FROM products, types WHERE (type_id=types.id AND quantity < 10) GROUP BY types.name;
																								  
--8. Вывести все продукты и их тип.
SELECT products.name AS name, types.name AS type FROM products, types WHERE type_id=types.id;

