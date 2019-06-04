USE QUIZ_DB;

DROP TABLE IF EXISTS history;

/* History of past taken quizzes. */

CREATE TABLE history (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INTEGER NOT NULL,
    quiz_id INTEGER NOT NULL,
    num_points DOUBLE,
    time_spent BIGINT,
    date_taken DATETIME 
)