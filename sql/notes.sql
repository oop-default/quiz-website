USE QUIZ_DB;

DROP TABLE IF EXISTS notes;

CREATE TABLE notes (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    note VARCHAR (255) NOT NULL,
    notification_id INTEGER NOT NULL
);