CREATE DATABASE car_service_db DEFAULT CHARACTER SET utf8;

create user 'car_service_app'@localhost
        identified by 'car_service';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `car_service_db`.*
TO car_service_app@localhost;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `car_service_db`.*
TO car_service_app@'%';
#IDENTIFIED BY 'password';