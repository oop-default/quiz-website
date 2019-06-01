DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes (
    id INTEGER NOT NULL PRIMARY KEY,
    tittle VARCHAR,
    author_id INTEGER,
    type VARCHAR,
    create_date DATE,
    category_id INTEGER,
    num_points INTEGER,
  
    FOREIGN KEY (author_id) 
        REFERENCES accounts (id)
        ON DELETE CASCADE
);