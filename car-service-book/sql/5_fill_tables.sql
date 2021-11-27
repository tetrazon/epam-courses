INSERT INTO car_service_db.district (id, name) VALUES
(2, 'Витебск'),
(3, 'Гомель'),
(4, 'Гродно'),
(5, 'Минск'),
(6, 'Могилев');


    INSERT INTO car_service_db.category (id, name) VALUES
(1, 'Двигательная система'),
(2, 'Система торможения'),
(3, 'Система охлаждения'),
(4, 'Трансмиссия'),
(5, 'Электрическая система'),
(6, 'Система подвески'),
(7, 'Салон'),
(8, 'Кузов'),
(9, 'Система безопасности'),
(10, 'Топливная система'),
(11, 'Система управления'),
(12, 'Другие системы');


INSERT INTO car_service_db.car (id, user_id, model, mileage, year) VALUES (1, 2, 'Tesla', '1000', 2020),
(2, 2, 'Toyota', '2000', 2019);

INSERT INTO car_service_db.car_record (id, car_id, km_interval, months_interval, description, is_periodic, is_tender, date, category_id)
VALUES (1, 1, 5000, 12, 'Замена моторного масла', 1, 0, '2021-09-30', 1),
(2, 2, 20000, 24, 'Замена тормозной жидкости', 1, 1, '2020-05-05', 2);

INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
VALUES (3, 'user1', 'user1', 1, 'user1', 'user1', 'user1@mail.m', 1, '+375299999999', 0, 0);

