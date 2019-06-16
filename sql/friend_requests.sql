USE QUIZ_DB;

DROP TABLE IF EXISTS friend_requests;

CREATE TABLE friend_requests (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    notification_id INTEGER NOT NULL
);


/*
    IT MAY NOT CHANGE 
*/