create table location (
id binary(16) NOT NULL,
latitude double DEFAULT NULL,
longitude double DEFAULT NULL,
city_name varchar(255) DEFAULT NULL,
country_name varchar(255) NOT NULL,
region varchar(255) NOT NULL,
PRIMARY KEY (`id`)
);