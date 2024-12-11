CREATE TABLE dogs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    breed_name VARCHAR(100) NOT NULL,
    tail_length_cm INT NOT NULL
);
