CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name TEXT,
                       email varchar UNIQUE,
                       password TEXT
);