DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    id INTEGER NOT NULL PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    mail VARCHAR,
    password VARCHAR,
    Image LONGVARBINARY,
    date_created DATE,
    is_active BOOLEAN,
    is_banned BOOLEAN,
    num_points INTEGER,
    is_admin BOOLEAN
);