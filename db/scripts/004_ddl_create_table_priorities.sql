CREATE TABLE IF NOT EXISTS priorities(
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL UNIQUE,
    position int NOT NULL
);