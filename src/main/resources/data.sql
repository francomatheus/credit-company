
INSERT INTO user(email, username, password, fullname) VALUES ('admin@test.com', 'admin', '$2y$12$Uy7nK6Pyt3FavTcbSwfWtOdJWY4efEERjYzU3KTpmNYg/jyXdGZmm', 'administrador');
INSERT INTO role(role) VALUES ('ROLE_ADMIN');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (1,1);

INSERT INTO user(email, username, password, fullname) VALUES ('admin@test.com', 'teste', '$2y$12$eKqLYapD4n9xOT41F1uNa.jkfbSiTRCQVHuTVG5/Fi.vbfbXsZD7O', 'administrador');
INSERT INTO role(role) VALUES ('ROLE_ADMIN');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (2,1);
