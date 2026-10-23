CREATE DATABASE IF NOT EXISTS auth_system;

USE auth_system;

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(100) NOT NULL UNIQUE,
    hash BIGINT NOT NULL,
    salt VARCHAR(100) NOT NULL
);