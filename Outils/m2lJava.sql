USE m2lJava;
DROP TABLE IF EXISTS participer, appartenir, competition,equipe, users, candidat;
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
CREATE table equipe (
	id_ca INt PRIMARY KEY,
    id_chef int,
    foreign key (id_chef) references users (id_ca),
    foreign key (id_ca) references candidat (id_ca)
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
    id_ca INT,
    id_eq INT,
    FOREIGN KEY (id_ca)
        REFERENCES users (id_ca),
    FOREIGN KEY (id_eq)
        REFERENCES equipe (id_ca),
    PRIMARY KEY (id_ca , id_eq)
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

DELIMITER |
CREATE PROCEDURE fillTables(nbr int)      
BEGIN
	DECLARE v_i INT DEFAULT 1;
	DECLARE nom VARCHAR(64) DEFAULT 'nom_';
	DECLARE prenom VARCHAR(64) DEFAULT 'prenom_';
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
/*DELIMITER ;
CALL filltables(2);
select * from participer;
select * from candidat;
insert into candidat values(default,"equipe");
insert into equipe values(3,1);
select * from users;
select * from Equipe;
insert into candidat values (default, "salut");
/*grant all privileges on m2lJava.* to 'hibernate'@'localhost' identified by 'root';*/
