
INSERT INTO user(email, username, password, fullname) VALUES ('admin@test.com', 'admin', '{bcrypt}$2a$10$94tm/VBKKJ7VMxMw984G4u5AnDpjFzvukqwDIup.2ojLJoJec128S', 'administrador');
INSERT INTO role(role) VALUES ('ROLE_ADMIN');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (1,1);

INSERT INTO user(email, username, password, fullname) VALUES ('admin@test.com', 'teste', '{bcrypt}$2a$10$94tm/VBKKJ7VMxMw984G4u5AnDpjFzvukqwDIup.2ojLJoJec128S', 'administrador');
INSERT INTO role(role) VALUES ('ROLE_ADMIN');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (2,1);
