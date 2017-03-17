USE m2lJava;
DROP TABLE IF EXISTS participer, appartenir, competition, users, candidat;
DROP VIEW IF EXISTS equipe;

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
    DECLARE niveau int DEFAULT 0;
    DECLARE lieu varchar(64) default 'Paris';
    DECLARE mail int DEFAULT '';
    DECLARE creditFormation int DEFAULT 500;
    DECLARE description VARCHAR(64) DEFAULT 'zhauihzea ndlz abnhjkldb qnhd kjahznjkle bhzau';
    DECLARE prerequis VARCHAR(64) DEFAULT 'zhauihzea ndlz abnhjkldb qnhd kjahznjkle bhzau';
    DECLARE id_presta int DEFAULT 1;
    REPEAT
		insert into prestataire values(default, 'un prestataire', 15, 'rue du champ', 'PARIS', 75020);
		REPEAT
			insert into formation values(default, concat(titre, ' ', v_i) , date_f, duree, creditJour,creditFormation,lieu, description,prerequis, id_presta);
			SET v_i = v_i + 1;    
		UNTIL v_i > nbr END REPEAT;
	UNTIL v_i > nbr END REPEAT;
END|
DELIMIT
DELIMITER ;


/*grant all privileges on m2lJava.* to 'hibernate'@'localhost' identified by 'root';*/