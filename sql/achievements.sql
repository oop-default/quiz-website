USE QUIZ_DB;

DROP TABLE IF EXISTS achievements;

CREATE TABLE achievements (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (255) NOT NULL,
    num_points DOUBLE NOT NULL, /* needed to get it */
    description TEXT,
    image LONGBLOB
    
    UNIQUE KEY (name)
);