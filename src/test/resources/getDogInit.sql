CREATE TABLE dogs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    breed_name VARCHAR(100) NOT NULL,
    tail_length_cm INT NOT NULL
);

INSERT INTO dogs (id, name, breed_name, tail_length_cm) VALUES
(100, 'Bönan Fogelin', 'Mini dachshund', 20),
(101, 'JoJo Hammarberg', 'Bulldog / Portuguese water dog', 2);
