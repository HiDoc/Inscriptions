USE m2lJava;
DROP TABLE IF EXISTS participer, appartenir, competition, users, candidat;
DROP VIEW IF EXISTS equipe;
DROP PROCEDURE IF EXISTS fillTables;

CREATE TABLE candidat (
    id_ca INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(128)
);

CREATE TABLE users (
    id_ca INT PRIMARY KEY,
    niveau INT DEFAULT 0,
    mail VARCHAR(128),
    prenom VARCHAR(128),
    FOREIGN KEY (id_ca) REFERENCES candidat(id_ca)
);

CREATE TABLE competition (
    id_co INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(128),
    date_d DATETIME,
    date_close DATETIME,	
    duree INT,
    enEquipe BOOLEAN default false
);

CREATE TABLE appartenir (
    id_user INT,
    id_equipe INT,
    PRIMARY KEY (id_user , id_equipe),
    FOREIGN KEY (id_user)
        REFERENCES candidat (id_ca),
    FOREIGN KEY (id_equipe)
        REFERENCES candidat (id_ca)
);

CREATE TABLE participer (
    id_ca INT,
    id_co INT,
    PRIMARY KEY (id_ca , id_co),
    FOREIGN KEY (id_ca)
        REFERENCES candidat (id_ca),
    FOREIGN KEY (id_co)
        REFERENCES competition (id_co)
);
CREATE VIEW Equipe AS(
	SELECT * 
	FROM candidat c 
	WHERE NOT EXISTS (	SELECT * 
						FROM users u 	
						WHERE c.id_ca = u.id_ca)
	);

DELIMITER |
CREATE PROCEDURE fillTables(nbr int)      
BEGIN
	DECLARE v_i INT DEFAULT 1;
	DECLARE nom VARCHAR(64) DEFAULT 'nom_';
	DECLARE prenom DATETIME DEFAULT 'prenom_';
    DECLARE duree int default 76000;
    DECLARE mail varchar(64) DEFAULT '';
    DECLARE date_d datetime default '2017-03-03 12:00:00';
    DECLARE date_c datetime default '2017-03-01 12:00:00';
    REPEAT
		insert into candidat values(default,concat(nom, v_i));
		insert into users values(v_i, default, concat(nom, v_i, prenom, v_i, '@mail.com') , concat(prenom, v_i));
		insert into competition values(default,concat(nom, v_i), date_d, date_c,duree, default);
		SET v_i = v_i + 1;
	UNTIL v_i > nbr END REPEAT;
END|
DELIMITER ;
CALL filltables(1);
select * from competition;
grant all privileges on m2lJava.* to 'hibernate'@'localhost' identified by 'root';