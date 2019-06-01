DROP TABLE IF EXISTS friend_requests;

CREATE TABLE history (
    id INTEGER NOT NULL PRIMARY KEY,
    account_id INTEGER,
    quiz_id INTEGER,
    score DOUBLE,
    time_spent DOUBLE,
    
    FOREIGN KEY (account_id) 
        REFERENCES accounts (id)
        ON DELETE CASCADE,
    
    FOREIGN KEY (quiz_id) 
        REFERENCES quizzes (id)
        ON DELETE CASCADE
);