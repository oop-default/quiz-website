DROP TABLE IF EXISTS achievings;

CREATE TABLE achievings (
    id INTEGER NOT NULL PRIMARY KEY,
    achievment_id INTEGER,
    account_id INTEGER,
    date_achieved DATE,
    
    FOREIGN KEY (achievment_id) 
        REFERENCES achievements (id)
        ON DELETE CASCADE,
    
    FOREIGN KEY (account_id) 
        REFERENCES accounts (id)
        ON DELETE CASCADE
);