use m2lJava;
drop table if exists participer, appartenir, competition, users, candidat;

CREATE TABLE candidat (
    id_ca INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(128)
);

CREATE TABLE users (
    id_ca INT PRIMARY KEY,
    niveau INT DEFAULT 0,
    mail VARCHAR(128),
    prenom VARCHAR(128),
    Foreign key (id_ca) references candidat(id_ca)
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
select * from candidat;

select * from users;
use m2lJava;
grant all privileges on m2lJava.* to 'hibernate'@'localhost' identified by 'root';