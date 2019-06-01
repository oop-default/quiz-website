DROP TABLE IF EXISTS challenges;

CREATE TABLE challenges (
    id INTEGER NOT NULL PRIMARY KEY,
    quiz_id INTEGER,
    notification_id INTEGER,

    FOREIGN KEY (quiz_id) 
        REFERENCES quizzes (id)
        ON DELETE CASCADE,

    FOREIGN KEY (notification_id) 
        REFERENCES notifications (id)
        ON DELETE CASCADE
);