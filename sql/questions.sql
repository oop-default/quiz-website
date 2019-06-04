USE QUIZ_DB;

DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(55) NOT NULL, /* type may be multiple choice, etc. */
    question TEXT NOT NULL,
    quiz_id INTEGER NOT NULL
);