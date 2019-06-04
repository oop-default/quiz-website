USE QUIZ_DB;

DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    tittle VARCHAR(255) NOT NULL,
    author_id INTEGER NOT NULL,
    description TEXT,
    date_created DATETIME NOT NULL,
    category_id INTEGER NOT NULL,
    num_points DOUBLE,

    UNIQUE KEY (tittle)
);