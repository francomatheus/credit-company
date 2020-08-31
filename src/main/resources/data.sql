
INSERT INTO user(id,email, username, password) VALUES (100,'admin@test.com', 'admin', '{bcrypt}$2a$10$94tm/VBKKJ7VMxMw984G4u5AnDpjFzvukqwDIup.2ojLJoJec128S');
INSERT INTO role(id,role) VALUES (100,'ROLE_ADMIN');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (100,100);

INSERT INTO user(id,email, username, password) VALUES (101,'admin2@test.com', 'teste', '{bcrypt}$2a$10$94tm/VBKKJ7VMxMw984G4u5AnDpjFzvukqwDIup.2ojLJoJec128S');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (101,100);
