USE `car_service_db`;

CREATE TABLE `district`
(
    id    INTEGER     NOT NULL AUTO_INCREMENT,
    name VARCHAR(7) NOT NULL UNIQUE,
    CONSTRAINT pk_district PRIMARY KEY (`id`)
);

CREATE TABLE `category`
(
    id    INTEGER     NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL UNIQUE,
    CONSTRAINT pk_category PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
                        id INTEGER NOT NULL AUTO_INCREMENT,
                        login VARCHAR(40) NOT NULL UNIQUE,
                        password NCHAR(32) NOT NULL,
                        `role` TINYINT NOT NULL,
                        `name` VARCHAR(40) NOT NULL,
                        `surname` VARCHAR(40) NOT NULL,
                        `email` VARCHAR(255) NOT NULL,
                        `district_id` INTEGER NOT NULL,
                        `mobile_phone` NCHAR(13) NOT NULL,
    /*
     0 - false
     1 - true
     */
                        is_banned TINYINT CHECK (`is_banned` IN (0, 1)),
                        is_archived TINYINT CHECK (is_archived IN (0, 1)),
                        CONSTRAINT pk_user PRIMARY KEY (`id`),
                        CONSTRAINT
                            fk_user_district FOREIGN KEY (`district_id`)
                            REFERENCES `district` (`id`)
                            ON UPDATE CASCADE
                            ON DELETE CASCADE
) ;



CREATE TABLE `master` (
                          id INTEGER NOT NULL AUTO_INCREMENT,

                          `user_id` INTEGER NOT NULL UNIQUE ,
                          `category_id` INTEGER NOT NULL ,
                          CONSTRAINT master_pk PRIMARY KEY (`id`),

                          CONSTRAINT fk_master_user FOREIGN KEY (`user_id`)
                              REFERENCES `user` (`id`)
                              ON UPDATE CASCADE
                              ON DELETE RESTRICT,
                          CONSTRAINT
                              fk_master_category FOREIGN KEY (`category_id`)
                              REFERENCES `category` (`id`)
                              ON UPDATE CASCADE
                              ON DELETE CASCADE
);

CREATE TABLE car (
                     id INTEGER NOT NULL AUTO_INCREMENT,
                     `user_id` INTEGER NOT NULL ,
                     `model` VARCHAR(30) NOT NULL,
                     `mileage` double NOT NULL,
                     `year` INTEGER NOT NULL,
                     CONSTRAINT car_pk PRIMARY KEY (`id`),
                     CONSTRAINT fk_car_user FOREIGN KEY (`user_id`)
                         REFERENCES `user` (`id`)
                         ON UPDATE CASCADE
                         ON DELETE CASCADE
);

CREATE TABLE `car_record` (
                              id INTEGER NOT NULL AUTO_INCREMENT,
                              `car_id` INTEGER NOT NULL,
                              `km_interval` INTEGER,
                              `months_interval` INTEGER default 3,
                              `description` VARCHAR(255) NOT NULL,
                              is_periodic TINYINT NOT NULL ,
                              is_tender TINYINT NOT NULL ,
                              date DATE NOT NULL ,
    /*
0 - Engine
1 - Transmission ...
*/
                              `category_id` INTEGER NOT NULL ,
                              CONSTRAINT
                                  fk_car_record_car FOREIGN KEY (`car_id`)
                                  REFERENCES `car` (`id`)
                                  ON UPDATE CASCADE
                                  ON DELETE CASCADE ,
                              CONSTRAINT
                                  fk_car_record_category FOREIGN KEY (`category_id`)
                                  REFERENCES `category` (`id`)
                                  ON UPDATE CASCADE
                                  ON DELETE RESTRICT ,

                              CONSTRAINT pk_car_record PRIMARY KEY (`id`)

);

CREATE TABLE `car_record_history` (
                                      id INTEGER NOT NULL AUTO_INCREMENT,
                                      `car_id` INTEGER NOT NULL ,
                                      date_performed DATE,
                                      work_price DOUBLE,
                                      `master_id` INTEGER,
                                      `description` VARCHAR(255) NOT NULL,
                                      CONSTRAINT pk_car_record_history PRIMARY KEY (`id`),
                                      CONSTRAINT
                                          fk_car_record_history_car FOREIGN KEY (`car_id`)
                                          REFERENCES `car` (`id`)
                                          ON UPDATE CASCADE
                                          ON DELETE CASCADE ,
                                          CONSTRAINT fk_car_record_history_master FOREIGN KEY (`master_id`)
                                          REFERENCES `master` (`id`)
                                          ON UPDATE CASCADE
                                          ON DELETE SET NULL

);

CREATE TABLE `service_tender` (
                                  `car_record_id` INTEGER NOT NULL,
                                  `master_id` INTEGER,
                                  price DOUBLE,
                                  is_selected TINYINT,
                                  CONSTRAINT
                                      fk_service_tender_car_record FOREIGN KEY (`car_record_id`)
                                      REFERENCES `car_record` (`id`)
                                      ON UPDATE CASCADE
                                      ON DELETE RESTRICT,

                                  CONSTRAINT fk_service_tender_master FOREIGN KEY (`master_id`)
                                      REFERENCES `master` (`id`)
                                      ON UPDATE CASCADE
                                      ON DELETE CASCADE,

                                    CONSTRAINT service_tender_pk PRIMARY KEY(car_record_id, master_id)
);

