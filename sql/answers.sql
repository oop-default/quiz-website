USE QUIZ_DB;

DROP TABLE IF EXISTS answers;

CREATE TABLE answers (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    answer TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    question_id INTEGER NOT NULL
);