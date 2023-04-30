create table weather_data (
id INT NOT NULL AUTO_INCREMENT,
city_name VARCHAR(255) NOT NULL,
temperature double NOT NULL,
pressure double NOT NULL,
humidity double NOT NULL,
wind_direction varchar(100) NOT NULL,
speed double NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (city_name) references location (city_name)
);