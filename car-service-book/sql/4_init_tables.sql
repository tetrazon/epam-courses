INSERT INTO car_service_db.district (id, name) VALUES
                                                   (1, 'Брест');
#password: admin: admin, user: user, master: master
INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
 VALUES (1, 'admin', '$2a$12$U4BjKrJtIEz0MDTHrP.pv.Z0pCy2aeltHwk2gYQu2eHdc0Cwg6Ml.', 0, 'admin', 'admin', 'admin@mail.m', 1, '+375297777777', 0, 0);
INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
 VALUES (2, 'user', '$2a$12$LQtIdKMLgGMmhD8IokMC2OYwFEQW4nXukevkk5oyFXYdqVRw7b2a2', 1, 'user', 'user', 'user@mail.m', 1, '+375299999999', 0, 0);
INSERT INTO car_service_db.user (id, login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
VALUES (3, 'master', '$2a$12$c54esBLBWj75mcSBJHCaxu06e1Jwk3fd.i.z7AdNY8ek.LTaUU6BO', 2, 'master', 'master', 'master@mail.m', 1, '+375299999999', 0, 0);


