use medicalservice;

/* ------------------------------------- tiggers de appointment -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_appointment
AFTER INSERT ON appointment
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointment', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', start_date: ', NEW.start_date, ', final_date: ', NEW.final_date, ', id_speciality: ', NEW.id_speciality, ', id_appointmentStatus: ', NEW.id_appointmentStatus, ', id_doctor: ', NEW.id_doctor, ', id_patient: ', NEW.id_patient), NOW());
END$$

CREATE TRIGGER after_update_appointment
AFTER UPDATE ON appointment
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointment', 'UPDATE', CONCAT('id: ', OLD.id, ', start_date: ', OLD.start_date, ', final_date: ', OLD.final_date, ', id_speciality: ', OLD.id_speciality, ', id_appointmentStatus: ', OLD.id_appointmentStatus, ', id_doctor: ', OLD.id_doctor, ', id_patient: ', OLD.id_patient), CONCAT('id: ', NEW.id, ', start_date: ', NEW.start_date, ', final_date: ', NEW.final_date, ', id_speciality: ', NEW.id_speciality, ', id_appointmentStatus: ', NEW.id_appointmentStatus, ', id_doctor: ', NEW.id_doctor, ', id_patient: ', NEW.id_patient), NOW());
END$$

CREATE TRIGGER after_delete_appointment
AFTER DELETE ON appointment
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointment', 'DELETE', CONCAT('id: ', OLD.id, ', start_date: ', OLD.start_date, ', final_date: ', OLD.final_date, ', id_speciality: ', OLD.id_speciality, ', id_appointmentStatus: ', OLD.id_appointmentStatus, ', id_doctor: ', OLD.id_doctor, ', id_patient: ', OLD.id_patient), NULL, NOW());
END$$
DELIMITER ;


/* ------------------------------------- tiggers de appointment_comments -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_appointment_comments
AFTER INSERT ON appointment_comments
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointment_comments', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', comment: ', NEW.comment, ', qualification: ', NEW.qualification, ', id_appointment: ', NEW.id_appointment), NOW());
END$$

CREATE TRIGGER after_update_appointment_comments
AFTER UPDATE ON appointment_comments
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointment_comments', 'UPDATE', CONCAT('id: ', OLD.id, ', comment: ', OLD.comment, ', qualification: ', OLD.qualification, ', id_appointment: ', OLD.id_appointment), CONCAT('id: ', NEW.id, ', comment: ', NEW.comment, ', qualification: ', NEW.qualification, ', id_appointment: ', NEW.id_appointment), NOW());
END$$

CREATE TRIGGER after_delete_appointment_comments
AFTER DELETE ON appointment_comments
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointment_comments', 'DELETE', CONCAT('id: ', OLD.id, ', comment: ', OLD.comment, ', qualification: ', OLD.qualification, ', id_appointment: ', OLD.id_appointment), NULL, NOW());
END$$
DELIMITER ;

/* ------------------------------------- tiggers de appointmentstatus -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_appointmentstatus
AFTER INSERT ON appointmentstatus
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointmentstatus', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_update_appointmentstatus
AFTER UPDATE ON appointmentstatus
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointmentstatus', 'UPDATE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_delete_appointmentstatus
AFTER DELETE ON appointmentstatus
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('appointmentstatus', 'DELETE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), NULL, NOW());
END$$
DELIMITER ;

/* ------------------------------------- tiggers de areas -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_area
AFTER INSERT ON area
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('area', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_update_area
AFTER UPDATE ON area
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('area', 'UPDATE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_delete_area
AFTER DELETE ON area
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('area', 'DELETE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), NULL, NOW());
END$$
DELIMITER ;

/* ------------------------------------- tiggers de doctor_speciality -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_doctor_speciality
AFTER INSERT ON doctor_speciality
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('doctor_speciality', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', doctor_id: ', NEW.doctor_id, ', speciality_id: ', NEW.speciality_id), NOW());
END$$

CREATE TRIGGER after_update_doctor_speciality
AFTER UPDATE ON doctor_speciality
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('doctor_speciality', 'UPDATE', CONCAT('id: ', OLD.id, ', doctor_id: ', OLD.doctor_id, ', speciality_id: ', OLD.speciality_id), CONCAT('id: ', NEW.id, ', doctor_id: ', NEW.doctor_id, ', speciality_id: ', NEW.speciality_id), NOW());
END$$

CREATE TRIGGER after_delete_doctor_speciality
AFTER DELETE ON doctor_speciality
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('doctor_speciality', 'DELETE', CONCAT('id: ', OLD.id, ', doctor_id: ', OLD.doctor_id, ', speciality_id: ', OLD.speciality_id), NULL, NOW());
END$$
DELIMITER ;


/* ------------------------------------- tiggers de patient -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_patient
AFTER INSERT ON patient
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('patient', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', nss: ', NEW.nss, ', allergies: ', NEW.allergies, ', account_number: ', NEW.account_number, ', id_user: ', NEW.id_user), NOW());
END$$

CREATE TRIGGER after_update_patient
AFTER UPDATE ON patient
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('patient', 'UPDATE', CONCAT('id: ', OLD.id, ', nss: ', OLD.nss, ', allergies: ', OLD.allergies, ', account_number: ', OLD.account_number, ', id_user: ', OLD.id_user), CONCAT('id: ', NEW.id, ', nss: ', NEW.nss, ', allergies: ', NEW.allergies, ', account_number: ', NEW.account_number, ', id_user: ', NEW.id_user), NOW());
END$$

CREATE TRIGGER after_delete_patient
AFTER DELETE ON patient
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('patient', 'DELETE', CONCAT('id: ', OLD.id, ', nss: ', OLD.nss, ', allergies: ', OLD.allergies, ', account_number: ', OLD.account_number, ', id_user: ', OLD.id_user), NULL, NOW());
END$$
DELIMITER ;


/* ------------------------------------- tiggers de roles -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_roles
AFTER INSERT ON roles
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('roles', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_update_roles
AFTER UPDATE ON roles
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('roles', 'UPDATE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_delete_roles
AFTER DELETE ON roles
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('roles', 'DELETE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), NULL, NOW());
END$$
DELIMITER ;



/* ------------------------------------- tiggers de service -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_service
AFTER INSERT ON service
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('service', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', title: ', NEW.title, ', summary: ', NEW.summary, ', description: ', NEW.description, ', price: ', NEW.price, ', image: ', NEW.image, ', id_area: ', NEW.id_area), NOW());
END$$

CREATE TRIGGER after_update_service
AFTER UPDATE ON service
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('service', 'UPDATE', CONCAT('id: ', OLD.id, ', title: ', OLD.title, ', summary: ', OLD.summary, ', description: ', OLD.description, ', price: ', OLD.price, ', image: ', OLD.image, ', id_area: ', OLD.id_area), CONCAT('id: ', NEW.id, ', title: ', NEW.title, ', summary: ', NEW.summary, ', description: ', NEW.description, ', price: ', NEW.price, ', image: ', NEW.image, ', id_area: ', NEW.id_area), NOW());
END$$

CREATE TRIGGER after_delete_service
AFTER DELETE ON service
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('service', 'DELETE', CONCAT('id: ', OLD.id, ', title: ', OLD.title, ', summary: ', OLD.summary, ', description: ', OLD.description, ', price: ', OLD.price, ', image: ', OLD.image, ', id_area: ', OLD.id_area), NULL, NOW());
END$$
DELIMITER ;

/* ------------------------------------- tiggers de service_purchase_history -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_service_purchase_history
AFTER INSERT ON service_purchase_history
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('service_purchase_history', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', price: ', NEW.price, ', buy_date: ', NEW.buy_date, ', id_service: ', NEW.id_service, ', id_patient: ', NEW.id_patient), NOW());
END$$

CREATE TRIGGER after_update_service_purchase_history
AFTER UPDATE ON service_purchase_history
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('service_purchase_history', 'UPDATE', CONCAT('id: ', OLD.id, ', price: ', OLD.price, ', buy_date: ', OLD.buy_date, ', id_service: ', OLD.id_service, ', id_patient: ', OLD.id_patient), CONCAT('id: ', NEW.id, ', price: ', NEW.price, ', buy_date: ', NEW.buy_date, ', id_service: ', NEW.id_service, ', id_patient: ', NEW.id_patient), NOW());
END$$

CREATE TRIGGER after_delete_service_purchase_history
AFTER DELETE ON service_purchase_history
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('service_purchase_history', 'DELETE', CONCAT('id: ', OLD.id, ', price: ', OLD.price, ', buy_date: ', OLD.buy_date, ', id_service: ', OLD.id_service, ', id_patient: ', OLD.id_patient), NULL, NOW());
END$$
DELIMITER ;


/* ------------------------------------- tiggers de speciality -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_speciality
AFTER INSERT ON speciality
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('speciality', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', name: ', NEW.name, ', id_area: ', NEW.id_area), NOW());
END$$

CREATE TRIGGER after_update_speciality
AFTER UPDATE ON speciality
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('speciality', 'UPDATE', CONCAT('id: ', OLD.id, ', name: ', OLD.name, ', id_area: ', OLD.id_area), CONCAT('id: ', NEW.id, ', name: ', NEW.name, ', id_area: ', NEW.id_area), NOW());
END$$

CREATE TRIGGER after_delete_speciality
AFTER DELETE ON speciality
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('speciality', 'DELETE', CONCAT('id: ', OLD.id, ', name: ', OLD.name, ', id_area: ', OLD.id_area), NULL, NOW());
END$$
DELIMITER ;


/* ------------------------------------- tiggers de users -------------------------------------*/

DELIMITER $$
CREATE TRIGGER after_insert_users
AFTER INSERT ON users
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('users', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', email: ', NEW.email, ', password: ', NEW.password, ', name: ', NEW.name, ', fisrt_surname: ', NEW.fisrt_surname, ', second_surname: ', NEW.second_surname, ', phone: ', NEW.phone, ', image: ', NEW.image, ', id_rol: ', NEW.id_rol, ', id_status: ', NEW.id_status), NOW());
END$$

CREATE TRIGGER after_update_users
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('users', 'UPDATE', CONCAT('id: ', OLD.id, ', email: ', OLD.email, ', password: ', OLD.password, ', name: ', OLD.name, ', fisrt_surname: ', OLD.fisrt_surname, ', second_surname: ', OLD.second_surname, ', phone: ', OLD.phone, ', image: ', OLD.image, ', id_rol: ', OLD.id_rol, ', id_status: ', OLD.id_status), CONCAT('id: ', NEW.id, ', email: ', NEW.email, ', password: ', NEW.password, ', name: ', NEW.name, ', fisrt_surname: ', NEW.fisrt_surname, ', second_surname: ', NEW.second_surname, ', phone: ', NEW.phone, ', image: ', NEW.image, ', id_rol: ', NEW.id_rol, ', id_status: ', NEW.id_status), NOW());
END$$

CREATE TRIGGER after_delete_users
AFTER DELETE ON users
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('users', 'DELETE', CONCAT('id: ', OLD.id, ', email: ', OLD.email, ', password: ', OLD.password, ', name: ', OLD.name, ', fisrt_surname: ', OLD.fisrt_surname, ', second_surname: ', OLD.second_surname, ', phone: ', OLD.phone, ', image: ', OLD.image, ', id_rol: ', OLD.id_rol, ', id_status: ', OLD.id_status), NULL, NOW());
END$$
DELIMITER ;

/* ------------------------------------- tiggers de user_status -------------------------------------*/
DELIMITER $$
CREATE TRIGGER after_insert_user_status
AFTER INSERT ON user_status
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('user_status', 'INSERT', NULL, CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_update_user_status
AFTER UPDATE ON user_status
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('user_status', 'UPDATE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), CONCAT('id: ', NEW.id, ', name: ', NEW.name), NOW());
END$$

CREATE TRIGGER after_delete_user_status
AFTER DELETE ON user_status
FOR EACH ROW
BEGIN
    INSERT INTO binnacle (table_name, action_name, old_values, new_values, date_action)
    VALUES ('user_status', 'DELETE', CONCAT('id: ', OLD.id, ', name: ', OLD.name), NULL, NOW());
END$$
DELIMITER ;

