INSERT INTO car_service_db.district (id, name) VALUES (1, 'Брест');
INSERT INTO car_service_db.category (id, name) VALUES (1, 'Двигательная система');
INSERT INTO car_service_db.category (id, name) VALUES (2, 'Система торможения');

INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
 VALUES (1, 'admin', 'admin', 0, 'admin', 'admin', 'admin@mail.m', 1, '+375297777777', 0, 0);
INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
 VALUES (2, 'user', 'user', 1, 'user', 'user', 'user@mail.m', 1, '+375299999999', 0, 0);

 INSERT INTO car_service_db.car (id, user_id, model, mileage, year) VALUES (1, 2, 'Tesla', '1000', 2020);
INSERT INTO car_service_db.car (id, user_id, model, mileage, year) VALUES (2, 2, 'Toyota', '2000', 2019);

INSERT INTO car_service_db.car_record (id, car_id, km_interval, months_interval, description, is_periodic, is_tender, date, category_id)
VALUES (1, 1, 5000, 12, 'Замена моторного масла', 1, 0, '2021-09-30', 1);
INSERT INTO car_service_db.car_record (id, car_id, km_interval, months_interval, description, is_periodic, is_tender, date, category_id)
 VALUES (2, 2, 20000, 24, 'Замена тормозной жидкости', 1, 1, '2020-05-05', 2);

