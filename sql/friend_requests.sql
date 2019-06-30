USE QUIZ_DB;

DROP TABLE IF EXISTS friend_requests;

CREATE TABLE friend_requests (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR (55) NOT NULL,
    sender_id INTEGER NOT NULL,
    receiver_id INTEGER NOT NULL,
    date_sent DATETIME NOT NULL,
    is_seen BOOLEAN
);
