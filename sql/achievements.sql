DROP TABLE IF EXISTS achievements;

CREATE TABLE achievements (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR,
    points INTEGER,
    description VARCHAR
);