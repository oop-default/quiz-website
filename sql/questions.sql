DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
    id INTEGER NOT NULL PRIMARY KEY,
    type VARCHAR,
    question VARCHAR,
    quiz_id INTEGER NOT NULL,
   
    FOREIGN KEY (quiz_id) 
        REFERENCES quizzes (id)
        ON DELETE CASCADE
);