DROP TABLE IF EXISTS notifications;

CREATE TABLE notifications (
    id INTEGER NOT NULL PRIMARY KEY,
    sender_id INTEGER,
    reciever_id INTEGER,
    date_sent DATE,
    seen BOOLEAN,
    
    FOREIGN KEY (sender_id) 
        REFERENCES accounts (id)
        ON DELETE CASCADE
);