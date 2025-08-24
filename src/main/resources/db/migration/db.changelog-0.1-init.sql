--liquibase formatted sql

--changeset alex:1
CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(64) NOT NULL,
    lastname VARCHAR(64) NOT NULL,
    email VARCHAR(64) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    enabled boolean DEFAULT TRUE
);
--ROLLBACK DROP TABLE users

--changeset alex:2
CREATE TABLE IF NOT EXISTS user_roles(
    user_id BIGINT,
    name VARCHAR(64) CHECK (name IN('USER','ADMIN')),
    CONSTRAINT pk_user_id_name PRIMARY KEY (user_id,name),
    CONSTRAINT fk_users_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
--ROLLBACK DROP TABLE user_roles