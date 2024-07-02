# quizManagementSystem
This is a java desktop application which is developed for leaning purpose.

*Need to set sqlite project database path in environment varaible named as `DB_PATH` for connection with database file.*

- Execute Following commands on DB for making basic structure

```
CREATE TABLE User (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    deleted BOOLEAN
);

```

```
CREATE TABLE Subject (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quizInstructions TEXT,
    quizTime INT,
    quizLength INT,
    passingMarks INT,
    deleted BOOLEAN
);
```
```
CREATE TABLE Quiz (
    id VARCHAR(255) PRIMARY KEY,
    userId VARCHAR(255) NOT NULL,
    subjectId VARCHAR(255) NOT NULL,
    questionsIds VARCHAR(255),  -- Assuming questionsIds can be lengthy
    answers VARCHAR(255),       -- Assuming answers can be lengthy
    totalMarks INT,
    obtainedMarks INT,
    deleted BOOLEAN,
    FOREIGN KEY (userId) REFERENCES User(id),
    FOREIGN KEY (subjectId) REFERENCES Subject(id)
);
```

```
CREATE TABLE Question (
    id VARCHAR(255) PRIMARY KEY,
    quizId VARCHAR(255) NOT NULL,
    statement TEXT NOT NULL,
    options TEXT NOT NULL,
    answer TEXT NOT NULL,
    deleted BOOLEAN,
    FOREIGN KEY (quizId) REFERENCES Quiz(id)
);
```