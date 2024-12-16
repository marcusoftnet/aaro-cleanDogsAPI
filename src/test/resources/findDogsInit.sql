CREATE TABLE dogs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    breed_name VARCHAR(100) NOT NULL,
    tail_length_cm INT NOT NULL
);

INSERT INTO dogs (name, breed_name, tail_length_cm) VALUES
('Bönan Fogelin', 'Mini dachshund', 20),
('JoJo Hammarberg', 'Bulldog / Portuguese water dog', 2),
('Buddy', 'Golden retriever', 50);
