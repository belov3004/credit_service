INSERT INTO ROLES(id, role_name) VALUES (0, 'ROLE_ADMIN');
INSERT INTO ROLES(id, role_name) VALUES (1, 'ROLE_USER');

INSERT INTO USERS (id, email, first_name, last_name, password, user_name) VALUES (0, 'admin@testcreditservice.com', 'Artem', 'Belov', '$2a$10$QbQ1gEYEnSXNfodaZ0RRXeswjQhlY2NX6Tnh4MwotIIpby.Em/Av6', 'admin');

INSERT INTO USER_ROLES (user_id, roles_id) VALUES (0, 0);