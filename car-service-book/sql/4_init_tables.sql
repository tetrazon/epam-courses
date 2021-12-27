INSERT INTO car_service_db.district (id, name) VALUES
                                                   (1, 'Брест');

INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
 VALUES (1, 'admin', 'admin', 0, 'admin', 'admin', 'admin@mail.m', 1, '+375297777777', 0, 0);
INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
 VALUES (2, 'user', 'user', 1, 'user', 'user', 'user@mail.m', 1, '+375299999999', 0, 0);
INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
VALUES (3, 'master', 'master', 2, 'master', 'master', 'master@mail.m', 1, '+375299999999', 0, 0);


