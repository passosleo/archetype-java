CREATE TYPE role AS ENUM ('USER', 'ADMIN');

CREATE TABLE users (
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role role NOT NULL DEFAULT 'USER',
    enabled BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT users_pk PRIMARY KEY(id)
);

CREATE INDEX users_email_idx ON users(email);
CREATE INDEX users_role_idx ON users(role);
CREATE INDEX users_enabled_idx ON users(enabled);