CREATE DATABASE company;

CREATE TABLE company(
                      id INTEGER NOT NULL,
                      name CHARACTER VARYING,
                      CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
                     id INTEGER NOT NULL,
                     name CHARACTER VARYING,
                     company_id INTEGER,
                     CONSTRAINT person_pkey PRIMARY KEY (id)
);


--  1) Retrieve in a single query:
--  - names of all persons that are NOT in the company with id = 5
--  - company name for each person

SELECT person.name, company.name
FROM person
       INNER JOIN company ON (person.company_id = company.id)
WHERE (company_id != 5);


--2) Select the name of the company with the maximum number of persons + number of persons in this company
SELECT *
FROM (SELECT company.name AS company_name, (COUNT(person.company_id)) AS number_of_persons
       FROM company
              INNER JOIN person ON (person.company_id = company.id)
       GROUP BY company.name ORDER BY number_of_persons DESC LIMIT 1) AS s;
