--liquibase formatted sql

--changeset alex:1
INSERT INTO users(firstname, lastname, email,enabled,password) VALUES
    ('Alexey','Alexeev','alexeev@mail.com',TRUE,'{noop}AdminBook');
--ROLLBACK DROP TABLE users

--changeset alex:2
INSERT INTO user_roles(user_id,name) VALUES
(1,'USER'),
(1,'ADMIN');
--ROLLBACK DROP TABLE user_roles