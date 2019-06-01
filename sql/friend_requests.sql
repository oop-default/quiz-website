DROP TABLE IF EXISTS friend_requests;

CREATE TABLE friend_requests (
    id INTEGER NOT NULL PRIMARY KEY,
    status VARCHAR,
    notification_id INTEGER,
  
    FOREIGN KEY (notification_id) 
        REFERENCES notifications (id)
        ON DELETE CASCADE
);