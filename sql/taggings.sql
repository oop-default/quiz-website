DROP TABLE IF EXISTS taggings;

CREATE TABLE taggings (
    id INTEGER NOT NULL PRIMARY KEY,
    tag_id INTEGER,
    quiz_id INTEGER,
  
    FOREIGN KEY (tag_id) 
        REFERENCES tags (id) 
        ON DELETE CASCADE,
  
    FOREIGN KEY (quiz_id) 
        REFERENCES quizzes (id)
        ON DELETE CASCADE
);