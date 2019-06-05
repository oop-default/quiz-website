USE QUIZ_DB;

DROP TABLE IF EXISTS challenges;

CREATE TABLE challenges (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    quiz_id INTEGER NOT NULL,
    notification_id INTEGER NOT NULL,

    UNIQUE KEY (quiz_id, notification_id)
);