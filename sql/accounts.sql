USE QUIZ_DB;

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR (255) NOT NULL,
    first_name VARCHAR (255) NOT NULL, 
    last_name VARCHAR (255) NOT NULL,
    mail VARCHAR (255) NOT NULL,
    gender VARCHAR(50) NOT NULL, /* 'Male', 'Female' or 'Other' */
    password VARCHAR (255) NOT NULL,
    image LONGBLOB,
    date_created DATETIME NOT NULL,
    is_deleted BOOLEAN,
    is_banned BOOLEAN,
    num_points DOUBLE,
    is_admin BOOLEAN,

    UNIQUE KEY (mail)
);