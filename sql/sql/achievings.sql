USE QUIZ_DB;

DROP TABLE IF EXISTS achievings;

CREATE TABLE achievings (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    achievment_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    date_achieved DATE NOT NULL
);