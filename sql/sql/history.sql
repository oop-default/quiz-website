USE QUIZ_DB;

DROP TABLE IF EXISTS friend_requests;

/* History of past taken quizzes. */

CREATE TABLE history (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INTEGER NOT NULL,
    quiz_id INTEGER NOT NULL,
    score DOUBLE,
    time_spent TIME
);