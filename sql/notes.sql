DROP TABLE IF EXISTS notes;

CREATE TABLE notes (
    id INTEGER NOT NULL PRIMARY KEY,
    note VARCHAR,
    notification_id INTEGER
);