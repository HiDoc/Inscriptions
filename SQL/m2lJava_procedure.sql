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
	declare v_i int;
    set v_i = 0;
    
END $$
DELIMITER ;

