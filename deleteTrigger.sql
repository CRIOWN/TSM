DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    TRIGGER `tms`.`deleteadmin` DELETE
    ON `tms`.`admin`
    FOR EACH ROW BEGIN
    
    DELETE FROM admin_log WHERE userid = OLD.userid
END$
DELIMITER ;