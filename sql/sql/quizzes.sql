USE QUIZ_DB;

DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes (
    id INTEGER NOT NULL PRIMARY KEY,
    tittle VARCHAR(255) NOT NULL,
    author_id INTEGER NOT NULL,
    description TEXT,
    create_date DATE NOT NULL,
    category_id INTEGER NOT NULL,
    num_points INTEGER  
);