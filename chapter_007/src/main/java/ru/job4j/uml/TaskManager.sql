CREATE DATABASE TaskManager;

--creation
CREATE TABLE roles(
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(50)
);
CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(100),
	role_id INTEGER REFERENCES roles(id)
);
CREATE TABLE rules(
	id SERIAL PRIMARY KEY,
	rule_name VARCHAR(50)
);
CREATE TABLE roles_rules(
	role_id INTEGER REFERENCES roles(id),
	rule_id INTEGER REFERENCES rules(id)
);
CREATE TABLE states(
	id SERIAL PRIMARY KEY,
	state_name VARCHAR(50)
);
CREATE TABLE categories(
	id SERIAL PRIMARY KEY,
	category_name VARCHAR(50)
);
CREATE TABLE items(
	id SERIAL PRIMARY KEY,
	item_description VARCHAR(100),
	user_id INTEGER REFERENCES users(id),
	state_id INTEGER REFERENCES states(id),
	category_id INTEGER REFERENCES categories(id)
);
CREATE TABLE attaches(
	id SERIAL PRIMARY KEY,
	attach_description VARCHAR(100),
	attach_size NUMERIC,
	item_id INTEGER REFERENCES items(id)
);
CREATE TABLE comments(
	id SERIAL PRIMARY KEY,
	comment_description VARCHAR(100),
	item_id INTEGER REFERENCES items(id)
);

--insert
INSERT INTO roles(role_name) VALUES ('admin'), 
									('guest'), 
									('user');
INSERT INTO users(user_name, role_id) VALUES ('Tom', 1), 
											 ('Jack',2), 
											 ('Ted', 3), 
											 ('Sam', 3);
INSERT INTO rules(rule_name) VALUES ('have all rights'), 
									('test only'), 
									('make order'), 
									('can use system');
INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 1), 
												 (1, 4), 
												 (2, 2), 
												 (3, 3), 
												 (3, 4);
INSERT INTO states(state_name) VALUES ('processing'), 
									  ('finished'), 
									  ('deleted');
INSERT INTO categories(category_name) VALUES ('premium'), 
											 ('usual');
INSERT INTO items(item_description, user_id, state_id, category_id) VALUES ('fix system problem', 1, 2, 1), 
																		   ('search old order', 4, 1, 2), 
																		   ('smth is happen', 3, 3, 2);
INSERT INTO attaches(attach_description, attach_size, item_id) VALUES ('printscreen', 0.154, 1),
																		('archiv', 56.182, 1),
																		('mail', 0.123, 2);
INSERT INTO comments(comment_description, item_id) VALUES ('help me!', 1),
														  ('if you will freetime', 2),
														  ('there is a mistake', 3),
														  ('delete it', 3);
														  




