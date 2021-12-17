DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
	movie_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(100),
	image VARCHAR(300),
	year INT
);


INSERT INTO movies VALUES (1, 'Kontrola absolutna', 'https://fwcdn.pl/fpo/52/53/745253/7749772.6.jpg', 2016);
INSERT INTO movies VALUES (2, 'Who Am I', 'https://fwcdn.pl/fpo/64/30/726430/7768846.6.jpg', 2014);
