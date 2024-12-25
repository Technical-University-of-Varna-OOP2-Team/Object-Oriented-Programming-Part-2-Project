
INSERT INTO Role (name) VALUES ('User');
INSERT INTO Role (name) VALUES ('Manager');
INSERT INTO Role (name) VALUES ('Owner');
INSERT INTO Role (name) VALUES ('Receptionist');

INSERT INTO Account (username, password, role_id)
VALUES ('admin', 'admin', (SELECT id FROM Role WHERE name = 'Administrator'));
