USE QUIZ_DB;

DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_id INTEGER NOT NULL,
    second_id INTEGER NOT NULL
    UNIQUE KEY (first_id, second_id)
);