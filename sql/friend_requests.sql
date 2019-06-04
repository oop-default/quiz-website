USE QUIZ_DB;

DROP TABLE IF EXISTS friend_requests;

CREATE TABLE friend_requests (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR (55) NOT NULL, /* 'Accepted', 'Pending', 'Denied' not anything else */
    notification_id INTEGER NOT NULL
);


/*
    IT MAY CHANGE 
    status coud be done with another table of statuses
    or ENUM('Accepted', 'Pending', 'Denied') i think using 
    enum would be harder to accociate numbers with statuses 
    and then insert or get data from tables 
*/