DROP TABLE IF EXISTS answers;

CREATE TABLE answers (
    id INTEGER NOT NULL PRIMARY KEY,
    answer VARCHAR,
    is_correct BOOLEAN,
    question_id INTEGER,
   
    FOREIGN KEY (question_id) 
        REFERENCES questions (id)
        ON DELETE CASCADE
);