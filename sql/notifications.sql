USE QUIZ_DB;

DROP TABLE IF EXISTS notifications;

CREATE TABLE notifications (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    sender_id INTEGER NOT NULL,
    reciever_id INTEGER NOT NULL,
    date_sent DATE NOT NULL,
    is_seen BOOLEAN
);