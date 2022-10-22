CREATE TABLE demo.access_level (
	id SERIAL PRIMARY KEY,
	access_name VARCHAR NOT NULL UNIQUE,
	access_level VARCHAR NOT NULL UNIQUE
) TABLESPACE ts_demo;

INSERT INTO demo.access_level (access_name, access_level) VALUES ('Admin', 'A');
INSERT INTO demo.access_level (access_name, access_level) VALUES ('User', 'U');
INSERT INTO demo.access_level (access_name, access_level) VALUES ('Developer', 'D');

CREATE TABLE demo.users (
	id SERIAL PRIMARY KEY,
	access_level_id INT REFERENCES demo.access_level (id) NOT NULL,
	user_uid VARCHAR NOT NULL UNIQUE,
	username VARCHAR NOT NULL UNIQUE,
	password VARCHAR NOT NULL,
	first_name VARCHAR NOT NULL,
	middle_name VARCHAR NULL,
	last_name VARCHAR NULL,
	is_active INT NOT NULL, /* 1 is yes, 2 is no */
	last_login TIMESTAMPTZ, 
	is_deleted INT NOT NULL, /* 1 is yes, 2 is no */
	UNIQUE (access_level_id, user_uid, username)
) TABLESPACE ts_demo;

INSERT INTO demo.users (access_level_id, user_uid, username, password, first_name, middle_name, last_name, is_active, last_login, is_deleted) 
VALUES (1, 'KdK6pydmdSO2EQf2', 'admin', '$2a$12$xXOImIUwgAAnKWzm/L/.R.IMtj7bUg8JZuUv5Z8FYEN9vbjZBxNaO', 'AdminFN', '', 'AdminLN', 1, now(), 2); 

/* password - admin (bycrypt algorithm) https://bcrypt-generator.com/ */




