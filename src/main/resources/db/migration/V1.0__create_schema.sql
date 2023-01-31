CREATE SCHEMA IF NOT EXISTS base;

CREATE EXTENSION unaccent;

CREATE SEQUENCE base.task_seq START 1 INCREMENT 1;
CREATE SEQUENCE base.user_seq START 1 INCREMENT 1;
CREATE SEQUENCE base.role_seq START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS base.users
(
    id       INT8         NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    cpf      VARCHAR(11)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS base.tasks
(
    id          INT8         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS base.roles
(
    id        INT8         NOT NULL,
    role_type VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

ALTER TABLE base.users
    ALTER COLUMN id SET DEFAULT NEXTVAL('base.user_seq');

ALTER TABLE base.tasks
    ALTER COLUMN id SET DEFAULT NEXTVAL('base.task_seq');

ALTER TABLE base.roles
    ALTER COLUMN id SET DEFAULT NEXTVAL('base.role_seq');
