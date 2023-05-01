create table location (
id binary(16) NOT NULL,
latitude double DEFAULT NULL,
longitude double DEFAULT NULL,
city_name varchar(255) DEFAULT NULL,
country_name varchar(255) NOT NULL,
region varchar(255) NOT NULL,
city_name VARCHAR(255) NOT NULL,
temperature double NOT NULL,
pressure double NOT NULL,
humidity double NOT NULL,
wind_direction varchar(100) NOT NULL,
speed double NOT NULL,
PRIMARY KEY (id),
PRIMARY KEY (`id`)
);