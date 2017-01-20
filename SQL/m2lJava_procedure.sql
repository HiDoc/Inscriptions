DELIMITER $$
CREATE PROCEDURE insert_user(IN nom varchar(128),in prenom varchar(128), in mail varchar(128)) 
BEGIN 
	declare id int;
	insert into candidat values(default, nom);
    set id = LAST_INSERT_ID();
    insert into users values(id, default, mail, prenom);
END $$

CREATE PROCEDURE fill_users (in nbr int)
BEGIN 
	DECLARE v1 INT DEFAULT 0;
	WHILE v1 < nbr DO
		CALL insert_user('random','random','random');
		SET v1 = v1 + 1;
	END WHILE;
  
END $$
DELIMITER ;