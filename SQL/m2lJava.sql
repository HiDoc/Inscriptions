use m2lJava;
drop table if exists participer, appartenir, competition, users, candidat;

CREATE TABLE candidat (
    id_ca INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(128)
);

CREATE TABLE users (
    id_us INT PRIMARY KEY,
    niveau INT DEFAULT 0,
    mail VARCHAR(128),
    prenom VARCHAR(128)
);

CREATE TABLE competition (
    id_co INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(128),
    date_d DATETIME,
    duree INT,
    enEquipe BOOLEAN
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
    ca_id INT,
    co_id INT,
    PRIMARY KEY (ca_id , co_id),
    FOREIGN KEY (ca_id)
        REFERENCES candidat (id_ca),
    FOREIGN KEY (co_id)
        REFERENCES competition (id_co)
);

DELIMITER $$
CREATE PROCEDURE insert_user(IN nom varchar(128),in prenom varchar(128), in mail varchar(128)) 
BEGIN 
	declare id int;
	insert into candidat values(default, nom);
    set id = LAST_INSERT_ID();
    insert into users values(id, default, mail, prenom);
END $$
DELIMITER ;